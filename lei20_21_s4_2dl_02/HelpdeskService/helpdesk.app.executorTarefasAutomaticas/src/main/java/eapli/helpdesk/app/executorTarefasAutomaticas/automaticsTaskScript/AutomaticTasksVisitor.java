package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;


import eapli.helpdesk.colaborador.domain.EmailHandler;
import eapli.helpdesk.colaborador.domain.PhoneNumber;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.AtributoPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

public class AutomaticTasksVisitor extends AutomaticTaskScriptGrammarBaseVisitor{

    public static void main(String[] args) throws IOException {

        System.out.println("Result with Visitor : ");
        parseWithVisitor("ScriptsTarefasAutomaticas/ScriptServicoMudarTelefone.txt", null);
    }
    public static boolean parseWithVisitor(String script, Ticket ticket) throws IOException {

        AutomaticTaskScriptGrammarLexer lexer = new AutomaticTaskScriptGrammarLexer(CharStreams.fromFileName(script));
        CommonTokenStream token = new CommonTokenStream(lexer);
        AutomaticTaskScriptGrammarParser parser = new AutomaticTaskScriptGrammarParser(token);
        ParseTree parseTree = parser.start(); // parse
        AutomaticTasksVisitor automaticTasksVisitor = new AutomaticTasksVisitor(ticket);
        Object obj = automaticTasksVisitor.visit(parseTree);
        return automaticTasksVisitor.getResult();
    }

    private Ticket ticket;
    private boolean flag = true;

    private final LinkedHashMap<String, String> variaveis = new LinkedHashMap<>();
    private final LinkedHashMap<String, String> form = new LinkedHashMap<>();
    private String FILE_XML;

    public boolean getResult() {
        return this.flag;
    }

    public AutomaticTasksVisitor(Ticket ticket) {
        super();
        this.ticket = ticket;
    }


    @Override
    public Object visitFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx) {
        if (this.flag) {
            try {
                EmailHandler emailHandler = new EmailHandler();
                if (!emailHandler.sendEmail(this.ticket.collaborator().user().email().toString(), ctx.assunto.getText().substring(1, ctx.assunto.getText().length() - 1), ctx.corpo.getText().substring(1, ctx.corpo.getText().length() - 1))) {
                    return this.flag = false;
                }
            } catch (IOException io) {
                return this.flag = false;
            }
        }
        return null;
    }

    @Override
    public Object visitFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoDistritoNoFormulario.getText().substring(1, ctx.campoDoDistritoNoFormulario.getText().length() - 1), 12)) {
                    return this.flag = false;
                }
            } catch (PhoneNumberException io) {
                return this.flag = false;
            }
        }
        return null;
    }

    @Override
    public Object visitFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoTeleNoFormulario.getText().substring(1, ctx.campoDoTeleNoFormulario.getText().length() - 1), 13)) {
                    return this.flag = false;
                }
            } catch (PhoneNumberException io) {
                return this.flag = false;
            }
        }
        return null;
    }

    @Override
    public Object visitOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx) {
        return this.variaveis.put(ctx.resultado.getText().substring(1, ctx.resultado.getText().length() - 1), "0");
    }

    @Override
    public Object visitChamar_funcao(AutomaticTaskScriptGrammarParser.Chamar_funcaoContext ctx) {
        return super.visitChamar_funcao(ctx);
    }

    @Override
    public Object visitFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx) {
        if (this.flag) {
            return this.FILE_XML = ctx.nomeFicheiro.getText();
        }
        return null;
    }

    @Override
    public Object visitPassos(AutomaticTaskScriptGrammarParser.PassosContext ctx) {
        return super.visitPassos(ctx);
    }

    @Override
    public Object visitBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx) {
        if (this.flag) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                Document doc = docBuilder.parse(new File(this.FILE_XML));
                Element htmlTag = doc.getDocumentElement();

                String identificador = verificaDados(ctx.identificador.getText());
                if (identificador == null) {
                    return this.flag = false;
                }

                if (ctx.procurar != null) {

                    String procurar = verificaDados(ctx.procurar.getText());
                    if (procurar == null) {
                        return  this.flag = false;
                    }

                    for (int i = 0; i < htmlTag.getElementsByTagName(ctx.classe.getText()).getLength(); i++) {
                        Element tag = (Element) htmlTag.getElementsByTagName(ctx.classe.getText()).item(i);
                        if (tag.getAttributes().item(0).getNodeValue().equalsIgnoreCase(identificador)) {
                            NodeList list = tag.getElementsByTagName(ctx.classeAtributo.getText().substring(1, ctx.classeAtributo.getText().length() - 1));
                            if (list.getLength() == 0) {
                                return false;
                            }
                            for (int j = 0; j < list.getLength(); j++) {
                                if (list.item(j).getAttributes().item(0).getNodeValue().equalsIgnoreCase(procurar)) {
                                    variaveis.put(ctx.varivelGuardar.getText().substring(1, ctx.varivelGuardar.getText().length() - 1), list.item(j).getTextContent());
                                    return false;
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
                                return false;
                            }
                            variaveis.put(ctx.varivelGuardar.getText().substring(1, ctx.varivelGuardar.getText().length() - 1), list.item(0).getTextContent());
                            break;
                        }
                    }
                }
            } catch (IOException | ParserConfigurationException | SAXException io) {
                return this.flag = false;
            }
        }
        return false;
    }

    @Override
    public Object visitAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx) {
        if (this.flag) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                Document doc = docBuilder.parse(new File(this.FILE_XML));
                Element htmlTag = doc.getDocumentElement();

                String identificador = verificaDados(ctx.identificador.getText());
                if (identificador == null) {
                    return this.flag = false;
                }

                String novoValor = verificaDados(ctx.novoValor.getText());
                if (novoValor == null) {
                    return this.flag = false;
                }

                for (int i = 0; i < htmlTag.getElementsByTagName(ctx.classe.getText()).getLength(); i++) {
                    Element tag = (Element) htmlTag.getElementsByTagName(ctx.classe.getText()).item(i);
                    if (tag.getAttributes().item(0).getNodeValue().equalsIgnoreCase(identificador)) {
                        NodeList list = tag.getElementsByTagName(ctx.classeAtributo.getText().substring(1, ctx.classeAtributo.getText().length() - 1));
                        if (list.getLength() == 0) {
                            return false;
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
                return this.flag = false;
            }
        }
        return false;
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
    public Object visitFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx) {
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
                    return this.flag = false;
                }
            } catch (IOException io) {
                return this.flag = false;
            }

        }
        return false;
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

    @Override
    public Object visitFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx) {
        if (this.flag) {
            try {
                if (!changeDataInDB(this.ticket, ctx.campoDoConcelhoNoFormulario.getText().substring(1, ctx.campoDoConcelhoNoFormulario.getText().length() - 1), 14)) {
                    return this.flag = false;
                }
            } catch (PhoneNumberException io) {
                return this.flag = false;
            }
        }
        return null;
    }


    @Override
    public Object visitOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx) {
        int left = (int) visit((ParseTree) ctx.left);
        int right = (int) visit((ParseTree) ctx.right);
        switch (ctx.op.getText().charAt(0)) {
            case '*' : return left * right;
            case '/' : return left / right;
        }
        return 0;

    }

    @Override
    public Object visitOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx) {
        int left = (int) visit((ParseTree) ctx.left);
        int right = (int) visit((ParseTree) ctx.right);
        switch (ctx.op.getText().charAt(0)) {
            case '+' : return left + right;
            case '-' : return left - right;
        }
        return 0;
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
