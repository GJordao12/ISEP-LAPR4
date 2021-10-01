grammar FormValidationGrammar;

start:
      | start ifCondition
      | start specs;

ifCondition: 'if' condicoes 'then' dados PONTO_E_VIRGULA;

statement :
            ifCondition
            | verify
            | specs
            ;

dados:
        dados condicoesDados
        | dados specs
        | dados ifCondition
        | ifCondition
        | specs
        | condicoesDados
        ;

specs:
        Inteiro 'tamanho: ' Inteiro PONTO_E_VIRGULA   #tamanhoValor
        | Inteiro 'esta entre ' intervalo             #valorEntre
        | Inteiro '-> ' Obrigatoriedade                #valorObrigatorio
        ;

intervalo: Inteiro 'e' Inteiro;

condicoesDados:
                 condicoes PONTO_E_VIRGULA
                 ;
condicoes:
            condicao
            | condicoes OperadorCondicional condicao PONTO_E_VIRGULA
            ;

verify:
          OperadorLogico
          | Igual_Diferente
          ;

condicao:
            Inteiro (OperadorLogico | Igual_Diferente) Inteiro   #valoresIguais
            | Inteiro ':' EstadoString                           #estadoValor
            | Inteiro Igual_Diferente condicaoString             #valorString
            | condicaoString Igual_Diferente Inteiro             #valorString1
            | Inteiro Igual_Diferente ValorBooleano              #valorBooleano
            | ValorBooleano Igual_Diferente Inteiro              #valorBooleano1
            ;

condicaoString:
                 stringEntreAspas
                 | valString
                 ;

stringEntreAspas:
                   '"'valString'"';

valString:
              String
              | String valString
              ;

valInt:   Inteiro
          | valInt Operador Inteiro
          ;


//------------------- VARIAVEIS -------------------

PONTO_E_VIRGULA: ';' ;

ValorBooleano: 'true' | 'false' ;
Tipo: 'Integer' | 'Real'| 'String' ;
EstadoString: 'preenchido' | 'vazio' ;
Obrigatoriedade: 'obrigatorio' | 'nao obrigatorio' ;

OperadorLogico: '<' | '>' | '<=' | '>=' ;
Igual_Diferente: '==' | '!=' ;
OperadorCondicional: '&&' | '||' ;
Operador: '+' | '-' | '*' | '/' ;

String: [A-Za-z]+ ;
Inteiro: [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;