grammar AutomaticTaskScriptGrammar;

start: START CHAVETA_ABRIR script CHAVETA_FECHAR;

script: instrucao_script+;

instrucao_script: condicao | chamar_funcao;

condicao:
    condicao AND condicao
    | condicao OR condicao
    | operacao_matematica;

operacao_matematica: resultado=XML_ATRIBUTO '=' inteiro PONTO_E_VIRGULA;

chamar_funcao: funcaoEnviarEmail | funcaoMudarDistrito | funcaoMudarTelemovel | funcaoMudarConcelho | funcaoBuscarInfoXML;

funcaoEnviarEmail: 'EnviarEmail' PARENTESE_ABRIR assunto=ALFANUM ',' corpo=ALFANUM PARENTESE_FECHAR PONTO_E_VIRGULA;

funcaoMudarDistrito: 'MudarDistrito' PARENTESE_ABRIR campoDoDistritoNoFormulario=FORM_ATRIBUTO PARENTESE_FECHAR PONTO_E_VIRGULA;

funcaoMudarTelemovel: 'MudarTelemovel' PARENTESE_ABRIR campoDoTeleNoFormulario=FORM_ATRIBUTO PARENTESE_FECHAR PONTO_E_VIRGULA;

funcaoMudarConcelho: 'MudarConcelho' PARENTESE_ABRIR campoDoConcelhoNoFormulario=FORM_ATRIBUTO PARENTESE_FECHAR PONTO_E_VIRGULA;

funcaoBuscarInfoXML: 'Ficheiro' nomeFicheiro=FICHEIRO_XML PARENTESE_RETO_ABRIR passos+ PARENTESE_RETO_FECHAR;

passos: buscarElemento | alterarElemento | funcaoEnviarEmailComValor | operacao_matematica;

buscarElemento: 'Elemento' varivelGuardar=XML_ATRIBUTO '=' 'Buscar' classe=CLASSE PARENTESE_ABRIR identificador=(NUM|ALFANUM|FORM_ATRIBUTO) ',' classeAtributo=XML_ATRIBUTO2 (',' procurar=(NUM|ALFANUM|FORM_ATRIBUTO))? PARENTESE_FECHAR PONTO_E_VIRGULA;

alterarElemento: 'Alterar' classe=CLASSE PARENTESE_ABRIR identificador=(NUM|ALFANUM|FORM_ATRIBUTO) ',' classeAtributo=XML_ATRIBUTO2 ',' novoValor=(NUM|ALFANUM|FORM_ATRIBUTO) PARENTESE_FECHAR PONTO_E_VIRGULA;

funcaoEnviarEmailComValor: 'EnviarEmail' PARENTESE_ABRIR assunto=(ALFANUM|ALFANUM2) ',' corpo=(ALFANUM|ALFANUM2) PARENTESE_FECHAR PONTO_E_VIRGULA;

inteiro:
      left=XML_ATRIBUTO op=('*'|'/') right=XML_ATRIBUTO # opExprMulDiv
    | left=XML_ATRIBUTO op=('+'|'-') right=XML_ATRIBUTO # opExprSumDif
    ;

//VARIAVEIS
START: 'START';
PARENTESE_RETO_ABRIR: '[';
PARENTESE_RETO_FECHAR: ']';
CHAVETA_ABRIR: '{';
CHAVETA_FECHAR: '}';
PARENTESE_ABRIR: '(';
PARENTESE_FECHAR: ')';
PONTO_E_VIRGULA: ';';
NUM: '-'?[0-9]+('.'[0-9]+)?;
MULTIPLICACAO: '*';
DIVISAO: '/';
SOMA: '+';
SUBTRACAO: '-';
AND: 'AND';
OR: 'OR';
CLASSE: [a-zA-Z]+;
XML_ATRIBUTO: '#'[a-zA-Z]+'#';
XML_ATRIBUTO2: '<'[a-zA-Z]+'>';
FICHEIRO_XML:[0-9a-zA-Z/]+'.xml';
ALFANUM: '"'[ 0-9,.a-zA-Z\u00C0-\u00FF:]+'"';
FORM_ATRIBUTO: '$'[ _0-9a-zA-Z\u00C0-\u00FF:]+'$';
ALFANUM2: '"'([ 0-9,.a-zA-Z\u00C0-\u00FF:]|XML_ATRIBUTO|FORM_ATRIBUTO)+'"';
WS: [ \t\r\n]+ -> skip;