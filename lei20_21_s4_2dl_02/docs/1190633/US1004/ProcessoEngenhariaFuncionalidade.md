# US1004 - Linguagem/Gramática de suporte geral ao sistema para expressar, entre outras coisas, validações de Atividades Automáticas
============================================================

# 1. Requisitos

**US1004:**

* Como **Gestor de Projeto**, eu pretendo que seja desenvolvida uma **linguagem/gramática** de suporte geral ao sistema para expressar, entre outras coisas, validações de **atividades automáticas**.  

# 2. Explicação da nossa Linguagem/Gramática

## 2.1. Estrutura

* START { ... } (toda a informação do script está dentro das {})

### 2.2. Representação de Dados

* **$...$** -> variáveis para ir buscar uma **resposta a um campo do formulário** (o nome do campo está entre $$).
* **#...#** -> variáveis para guardar **valores de operações matemáticas**, de **valores do ficheiro XML**, etc... (o nome da variável está entre ##).
* **<...>** -> variáveis para acedar a **tags no ficheiro XML** (o nome da tag está entre <>).
* **"..."** -> para escrever **texto ou palavras** para usar nos **emails**, etc...

### 2.3. Funções Pré-definidas

* EnviarEmail("assunto","corpo");
* MudarDistrito($nome do campo do formulario onde o utilizador vai escrever o novo distrito$);
* MudarTelemovel($nome do campo do formulario onde o utilizador vai escrever o novo número de telemóvel$);
* MudarConcelho($nome do campo do formulario onde o utilizador vai escrever o novo concelho$);

### 2.4. Ficheiros XML

#### 2.4.1 Estrutura

* Ficheiro [ ... ] (toda a informação do ficheiro está dentro dos [])

#### 2.4.2 Busca de Informação de Ficheiros XML

* Elemento #...# = Buscar tagPrincipal(id,subTag,idSubTag);
  > ***tagPrincipal*** -> **nome da tag principal** (<colaborador numeroMeca="11904"\> neste caso seria **colaborador**) **[obrigatório]**.
  >
  > ***id*** -> **identificador da tag principal** (<colaborador numeroMeca="11904"\> neste caso seria **11904** ou **"11904"** ou uma referência para ir buscar resposta a um campo do formulário, por exemplo **$Número Mecanográfico$**) **[obrigatório]**.
  >
  > ***subTag*** -> **nome da sub tag** (<mes nome="janeiro"\>1020</mes\> neste caso seria **<mes\>**) **[obrigatório]**.
  >
  > ***idSubTag*** -> **identificador da sub tag** (<mes nome="janeiro"\>1020</mes\> neste caso seria **janeiro** ou **"janeiro"** ou uma referência para ir buscar a resposta a um campo do formulário, por exemplo **$Mês$**) **[opcional e apenas é suportada uma sub tag]**.
  >


* ***Exemplos:***
  > Elemento #bonus# = Buscar colaborador($Número Mecanográfico$,<mes\>,$Mês$);
  >
  > Elemento #preco# = Buscar produto(3,<preco\>);

#### 2.4.3 Alteração de Informação de Ficheiros XML

* Alterar tagPrincipal(id,subTag,novoValor);
  > ***tagPrincipal*** -> **nome da tag principal** (<colaborador numeroMeca="11904"\> neste caso seria **colaborador**) **[obrigatório]**.
  >
  > ***id*** -> **identificador da tag principal** (<colaborador numeroMeca="11904"\> neste caso seria **11904** ou **"11904"** ou uma referência para ir buscar resposta a um campo do formulário, por exemplo **$Número Mecanográfico$**) **[obrigatório]**.
  >
  > ***subTag*** -> **nome da sub tag** (<mes nome="janeiro"\>1020</mes\> neste caso seria **<mes\>**) **[obrigatório]**.
  >
  > ***novoValor*** -> **novo valor da sub tag** (pode ser por exemplo **11** ou **"onze"** ou uma referência para ir buscar a resposta a um campo do formulário, por exemplo **$Desconto$**) **[obrigatório]**.


* ***Exemplos:***
  > Alterar produto($ID$,<desconto\>,$Desconto$);
  >
  > Alterar produto(2,<desconto\>,10);

#### 2.4.4. Operações com dados dos Ficheiros

* #...# = #...# op #...#;
  > **Operações suportadas**: **+**, **-**, **\*** e **/**.
  >
  > Apenas são suportadas operações com **dois valores**, por isso caso se deseje realizar mais operações terá de se **repetir a estrutura** em várias linhas com **novos valores**.


* ***Exemplo:***
  > Elemento #preco# = Buscar produto($ID$,<preco\>);
  >
  > Elemento #unidades# = Buscar produto($ID$,<unidades\>);
  >
  > #total# = #preco# * #unidades#;

#### 2.4.5. Envio de Emails

* EnviarEmail("assunto ... $...$ ... #...# ...", "corpo ... $...$ ... #...# ...");
  > Em comparação com a função de email pré-definida esta função aceita **valores de váriaveis (#...#)** e **respostas a campos dos formulários ($...$)**.
  >
  > Caso seja feita referência a variáveis que **não existam** ou que **não tenham valores** esse campo vai ser **preenchido** por **"[sem informação]"**.


* ***Exemplos:***
  > EnviarEmail("Quantidade de dinheiro com a venda total do stock do Produto com ID: $ID$", "O dinheiro que consegue fazer com a venda do stock completo, #unidades# unidades a #preco# euros cada, do produto com ID: $ID$ é #total# euros.");
  >
  > EnviarEmail("Bónus do Mês $Mês$", "No mês $Mês$ você recebeu #bonus# euros de bónus.");

#### 2.4.6. Exemplos de ficheiros XML

* bonus (HelpdeskService/FicheirosXML/bonus.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<colaboradores>
  <colaborador numeroMeca="11904">
      <mes nome="janeiro">1020</mes>
      <mes nome="fevereiro">1120</mes>
      <mes nome="março">1020</mes>
      <mes nome="abril">900</mes>
      <mes nome="maio">2500</mes>
      <mes nome="junho">1020</mes>
      <mes nome="julho">0</mes>
      <mes nome="agosto">0</mes>
      <mes nome="setembro">1020</mes>
      <mes nome="outubro">1020</mes>
      <mes nome="novembro">3000</mes>
      <mes nome="dezembro">0</mes>
  </colaborador>
  <colaborador numeroMeca="11809">
      <mes nome="janeiro">600</mes>
      <mes nome="fevereiro">600</mes>
      <mes nome="março">600</mes>
      <mes nome="abril">0</mes>
      <mes nome="maio">0</mes>
      <mes nome="junho">1000</mes>
      <mes nome="julho">1000</mes>
      <mes nome="agosto">1000</mes>
      <mes nome="setembro">600</mes>
      <mes nome="outubro">600</mes>
      <mes nome="novembro">750</mes>
      <mes nome="dezembro">0</mes>
  </colaborador>
</colaboradores>
```

* produtos (HelpdeskService/FicheirosXML/produtos.xml)

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><produtos>
    <produto id="1">
        <nome>Bola</nome>
        <preco>5</preco>
        <unidades>10</unidades>
        <desconto>30</desconto>
    </produto>
    <produto id="2">
        <nome>Toalha</nome>
        <preco>7</preco>
        <unidades>25</unidades>
        <desconto>2</desconto>
    </produto>
    <produto id="3">
        <nome>Guarda-Sol</nome>
        <preco>15</preco>
        <unidades>5</unidades>
        <desconto>13</desconto>
    </produto>
    <produto id="4">
        <nome>Protetor</nome>
        <preco>25</preco>
        <unidades>35</unidades>
        <desconto>75</desconto>
    </produto>
</produtos>
```

### 2.5 Exemplos de Scripts

* ScriptServicoMudarTelefone (HelpdeskService/ScriptsTarefasAutomaticas/ScriptServicoMudarTelefone.txt)

```txt
START{
MudarTelemovel($Numero de Telefone$);
EnviarEmail("Número de Telefone","O seu Número de Telefone foi alterado com sucesso.");
}
```

* ScriptServicoSaberMaximoDinheiroComProduto (HelpdeskService/ScriptsTarefasAutomaticas/ScriptServicoSaberMaximoDinheiroComProduto.txt)

```txt
START{
Ficheiro FicheirosXML/produtos.xml [
Elemento #preco# = Buscar produto($ID$,<preco>);
Elemento #unidades# = Buscar produto($ID$,<unidades>);
#total# = #preco# * #unidades#;
EnviarEmail("Quantidade de dinheiro com a venda total do stock do Produto com ID: $ID$",
"O dinheiro que consegue fazer com a venda do stock completo, #unidades# unidades
a #preco# euros cada, do produto com ID: $ID$ é #total# euros.");
]
}
```

* ScriptServicoAlterarDescontoProduto (HelpdeskService/ScriptsTarefasAutomaticas/ScriptServicoAlterarDescontoProduto.txt)

```txt
START{
Ficheiro FicheirosXML/produtos.xml [
Alterar produto($ID$,<desconto>,$Desconto$);
EnviarEmail("Alteração do Desconto do Produto com ID: $ID$",
"O desconto do produto com ID: $ID$ foi alterado para $Desconto$ porcento.");
]
}
```

* ScriptServicoConsultarBonus (HelpdeskService/ScriptsTarefasAutomaticas/ScriptServicoConsultarBonus.txt)

```txt
START{
Ficheiro FicheirosXML/bonus.xml [
Elemento #bonus# = Buscar colaborador($Número Mecanográfico$,<mes>,$Mês$);
EnviarEmail("Bónus do Mês $Mês$", "No mês $Mês$ você recebeu #bonus# euros de bónus.");
]
}
```
# 3. Implementação

* A gramática encontra-se na Classe **AutomaticTaskScriptGrammar.g4** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/AutomaticTaskScriptGrammar.g4)
* O **código** de **validação dos scripts** da gramática ***AutomaticTaskScriptGrammar*** encontram-se na Classe **ValidateScriptAutomaticTask** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/ValidateScriptAutomaticTask.java)
* O **código** dos **Listeners** da gramática ***AutomaticTaskScriptGrammar*** encontram-se na Classe **AutomaticTaskScriptGrammar** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/AutomaticTaskScriptGrammar.java)
* O **código** dos **Visitors** da gramática ***AutomaticTaskScriptGrammar*** encontram-se na Classe **AutomaticTasksVisitor** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/AutomaticTasksVisitor.java)

# 4. Integração/Demonstração

* Esta US está relacionada com a **US2003 (Continuar/completar a especificação em curso de um serviço)**, pois através da **gramática** vai ser possível **validar o script** inserido pelo utilizador, se ele pretender adicionar uma **Tarefa Automática** no fluxo aquando a especificação do serviço.

# 5. Observações

* Maior parte dos **commits** foram dados através da **US1007** e **US5003**.
