package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;

import java.io.*;
import java.util.LinkedHashMap;

import eapli.helpdesk.colaborador.domain.EmailHandler;
import eapli.helpdesk.colaborador.domain.PhoneNumber;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.AtributoPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AutomaticTaskScriptGrammar {

    public static boolean parseWithListener(String script, Ticket ticket) {
        try {
            AutomaticTaskScriptGrammarLexer lexer = new AutomaticTaskScriptGrammarLexer(CharStreams.fromFileName(script));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AutomaticTaskScriptGrammarParser parser = new AutomaticTaskScriptGrammarParser(tokens);
            ParseTree tree = parser.start();
            ParseTreeWalker walker = new ParseTreeWalker();
            EvalListener eListener = new EvalListener(ticket);
            walker.walk(eListener, tree);
            return eListener.getResult();
        } catch (IOException io) {
            return false;
        }
    }
}

class EvalListener extends AutomaticTaskScriptGrammarBaseListener {

    private final LinkedHashMap<String, String> variaveis = new LinkedHashMap<>();
    private final LinkedHashMap<String, String> form = new LinkedHashMap<>();
    private boolean flag = true;
    private final Ticket ticket;
    private String FILE_XML;

    public EvalListener(Ticket ticket) {
        super();
        this.ticket = ticket;
    }

    public boolean getResult() {
        return this.flag;
    }

    @Override
    public void exitOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx) {
        if (this.flag) {
            double result;
            String left = (ctx.left.getText().substring(1, ctx.left.getText().length() - 1));
            String right = (ctx.right.getText().substring(1, ctx.right.getText().length() - 1));
            String key = getLastKey(variaveis);
            if (ctx.op.getText().charAt(0) == '*') {
                result = Double.parseDouble(variaveis.get(key)) + (Double.parseDouble(variaveis.get(left)) * Double.parseDouble(variaveis.get(right)));
            } else {
                result = Double.parseDouble(variaveis.get(key)) + (Double.parseDouble(variaveis.get(left)) / Double.parseDouble(variaveis.get(right)));
            }
            variaveis.put(key, "" + result);
        }
    }

    @Override
    public void exitOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx) {
        if (this.flag) {
            double result;
            String left;
            String right;
            left = (ctx.left.getText().substring(1, ctx.left.getText().length() - 1));
            right = (ctx.right.getText().substring(1, ctx.right.getText().length() - 1));
            String key = getLastKey(variaveis);
            if (ctx.op.getText().charAt(0) == '+') {
                result = Double.parseDouble(variaveis.get(key)) + (Double.parseDouble(variaveis.get(left)) + Double.parseDouble(variaveis.get(right)));
            } else {
                result = Double.parseDouble(variaveis.get(key)) + (Double.parseDouble(variaveis.get(left)) - Double.parseDouble(variaveis.get(right)));
            }
            variaveis.put(key, "" + result);
        }
    }

    private String getLastKey(LinkedHashMap<String, String> variaveis) {
        String lastKey = null;
        for (String s : variaveis.keySet()) {
            lastKey = s;
        }
        return lastKey;
    }

    @Override
    public void exitFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx) {
        if (this.flag) {
            try {
                EmailHandler emailHandler = new EmailHandler();
                if (!emailHandler.sendEmail(this.ticket.collaborator().user().email().toString(), ctx.assunto.getText().substring(1, ctx.assunto.getText().length() - 1), ctx.corpo.getText().substring(1, ctx.corpo.getText().length() - 1))) {
                    this.flag = false;
                }
            } catch (IOException io) {
                this.flag = false;
            }
        }
    }

    @Override
    public void exitFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoDistritoNoFormulario.getText().substring(1, ctx.campoDoDistritoNoFormulario.getText().length() - 1), 12)) {
                    this.flag = false;
                }
            } catch (PhoneNumberException io) {
                this.flag = false;
            }
        }
    }

    @Override
    public void exitFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoTeleNoFormulario.getText().substring(1, ctx.campoDoTeleNoFormulario.getText().length() - 1), 13)) {
                    this.flag = false;
                }
            } catch (PhoneNumberException io) {
                this.flag = false;
            }
        }
    }

    @Override
    public void exitFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoConcelhoNoFormulario.getText().substring(1, ctx.campoDoConcelhoNoFormulario.getText().length() - 1), 14)) {
                    this.flag = false;
                }
            } catch (PhoneNumberException io) {
                this.flag = false;
            }
        }
    }

    @Override
    public void enterFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx) {
        if (this.flag) {
            this.FILE_XML = ctx.nomeFicheiro.getText();
        }
    }

    @Override
    public void exitBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx) {
        if (this.flag) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                Document doc = docBuilder.parse(new File(this.FILE_XML));
                Element htmlTag = doc.getDocumentElement();

                String identificador = verificaDados(ctx.identificador.getText());
                if (identificador == null) {
                    this.flag = false;
                    return;
                }

                if (ctx.procurar != null) {

                    String procurar = verificaDados(ctx.procurar.getText());
                    if (procurar == null) {
                        this.flag = false;
                        return;
                    }

                    for (int i = 0; i < htmlTag.getElementsByTagName(ctx.classe.getText()).getLength(); i++) {
                        Element tag = (Element) htmlTag.getElementsByTagName(ctx.classe.getText()).item(i);
                        if (tag.getAttributes().item(0).getNodeValue().equalsIgnoreCase(identificador)) {
                            NodeList list = tag.getElementsByTagName(ctx.classeAtributo.getText().substring(1, ctx.classeAtributo.getText().length() - 1));
                            if (list.getLength() == 0) {
                                return;
                            }
                            for (int j = 0; j < list.getLength(); j++) {
                                if (list.item(j).getAttributes().item(0).getNodeValue().equalsIgnoreCase(procurar)) {
                                    variaveis.put(ctx.varivelGuardar.getText().substring(1, ctx.varivelGuardar.getText().length() - 1), list.item(j).getTextContent());
                                    return;
                                }
                            }
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < htmlTag.getElementsByTagName(ctx.classe.getText()).getLength(); i++) {
                        Element tag = (Element) htmlTag.getElementsByTagName(ctx.classe.getText()).item(i);
                        if (tag.getAttributes().item(0).getNodeValue().equalsIgnoreCase(identificador)) {
                            NodeList list = tag.getElementsByTagName(ctx.classeAtributo.getText().substring(1, ctx.classeAtributo.getText().length() - 1));
                            if (list.getLength() == 0) {
                                return;
                            }
                            variaveis.put(ctx.varivelGuardar.getText().substring(1, ctx.varivelGuardar.getText().length() - 1), list.item(0).getTextContent());
                            break;
                        }
                    }
                }
            } catch (IOException | ParserConfigurationException | SAXException io) {
                this.flag = false;
            }
        }
    }

    @Override
    public void exitAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx) {
        if (this.flag) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                Document doc = docBuilder.parse(new File(this.FILE_XML));
                Element htmlTag = doc.getDocumentElement();

                String identificador = verificaDados(ctx.identificador.getText());
                if (identificador == null) {
                    this.flag = false;
                    return;
                }

                String novoValor = verificaDados(ctx.novoValor.getText());
                if (novoValor == null) {
                    this.flag = false;
                    return;
                }

                for (int i = 0; i < htmlTag.getElementsByTagName(ctx.classe.getText()).getLength(); i++) {
                    Element tag = (Element) htmlTag.getElementsByTagName(ctx.classe.getText()).item(i);
                    if (tag.getAttributes().item(0).getNodeValue().equalsIgnoreCase(identificador)) {
                        NodeList list = tag.getElementsByTagName(ctx.classeAtributo.getText().substring(1, ctx.classeAtributo.getText().length() - 1));
                        if (list.getLength() == 0) {
                            return;
                        }
                        list.item(0).setTextContent(novoValor);
                        //save file
                        TransformerFactory trf = TransformerFactory.newInstance();
                        Transformer transformer = trf.newTransformer();
                        StreamResult result = new StreamResult(new FileOutputStream(this.FILE_XML));
                        DOMSource source = new DOMSource(doc);
                        transformer.transform(source, result);
                        break;
                    }
                }
            } catch (IOException | TransformerException | ParserConfigurationException | SAXException io) {
                this.flag = false;
            }
        }
    }

    private String verificaDados(String dado) {
        String aux;
        if (dado.startsWith("$") && dado.endsWith("$")) {
            aux = buscarInfoAoForm(dado.substring(1, dado.length() - 1));
            return aux;
        } else {
            if (dado.startsWith("\"") && dado.endsWith("\"")) {
                return dado.substring(1, dado.length() - 1);
            } else {
                return dado;
            }
        }
    }

    private String buscarInfoAoForm(String identificador) {
        for (AtributoPreenchido atributoPreenchido : ticket.formularioPreenchido().atributoPreenchidoList()) {
            if (atributoPreenchido.atributoFormulario().nome().toString().equalsIgnoreCase(identificador)) {
                if (atributoPreenchido.nome() != null) {
                    form.put(identificador, atributoPreenchido.nome());
                    return atributoPreenchido.nome();
                }
                if (atributoPreenchido.valor() != null) {
                    form.put(identificador, atributoPreenchido.valor().toString());
                    return atributoPreenchido.valor().toString();
                }
            }
        }
        return null;
    }

    @Override
    public void enterOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx) {
        this.variaveis.put(ctx.resultado.getText().substring(1, ctx.resultado.getText().length() - 1), "0");
    }

    @Override
    public void exitFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx) {
        if (this.flag) {
            try {
                String assunto = ctx.assunto.getText().substring(1, ctx.assunto.getText().length() - 1);
                String corpo = ctx.corpo.getText().substring(1, ctx.corpo.getText().length() - 1);

                if (assunto.contains("#") || assunto.contains("$")) {
                    assunto = buildNewString(assunto);
                }

                if (corpo.contains("#") || corpo.contains("$")) {
                    corpo = buildNewString(corpo);
                }

                EmailHandler emailHandler = new EmailHandler();
                if (!emailHandler.sendEmail(this.ticket.collaborator().user().email().toString(), assunto, corpo)) {
                    this.flag = false;
                }
            } catch (IOException io) {
                this.flag = false;
            }

        }
    }

    public String buildNewString(String texto) {
        String[] array = texto.split(" ");
        StringBuilder newString = new StringBuilder();
        for (String s : array) {
            if (s.startsWith("#") && s.endsWith("#")) {
                String subString = s.substring(1, s.length() - 1);
                newString.append(variaveis.getOrDefault(subString, "[sem informação]")).append(" ");
            } else {
                if (s.startsWith("$") && s.endsWith("$")) {
                    String subString = s.substring(1, s.length() - 1);
                    newString.append(form.getOrDefault(subString, "[sem informação]")).append(" ");
                } else {
                    newString.append(s).append(" ");
                }
            }
        }
        return newString.toString();
    }

    public boolean changeDataInDB(Ticket ticket, String campo, Integer option) throws PhoneNumberException {
        boolean flag = false;
        CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
        for (AtributoPreenchido atributoPreenchido : ticket.formularioPreenchido().atributoPreenchidoList()) {
            if (atributoPreenchido.atributoFormulario().nome().toString().equalsIgnoreCase(campo)) {
                if (option == 13) {
                    String valor = "" + atributoPreenchido.valor();
                    ticket.collaborator().newPhoneNumber(new PhoneNumber(valor));
                }
                if (option == 12) {
                    String distrito = atributoPreenchido.nome();
                    ticket.collaborator().address().newDistrito(distrito);
                }
                if (option == 14) {
                    String concelho = atributoPreenchido.nome();
                    ticket.collaborator().address().newConcelho(concelho);
                }
                collaboratorRepository.save(ticket.collaborator());
                flag = true;
                break;
            }
        }
        return flag;
    }
}
