# US5003 - Desenvolvimento do Executor de Tarefas Automáticas (robusto)
================================================

# 1. Requisitos

**US5003:**

* Como **Gestor de Projeto**, eu pretendo que a equipa conclua o **desenvolvimento do Executor de Tarefas Automáticas** tornando-o bastante **robusto**.

# 2. Análise

* No **Sprint C** apenas estava a ser feito um **sleep de 10 segundos**, mas agora neste sprint esse **mecanismo de simulação** foi substituído pela **execução do script** recorrendo à **gramática**. Para isso foram desenvolvidos os **Listeners** e os **Visitors** da gramática ***AutomaticTaskScriptGrammar*** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/AutomaticTaskScriptGrammar.g4).
* Apesar de termos desenvolvido os **dois procedimentos**, referidos anteriormente, decidimos **usar os Listeners** para a execução de Tarefas Automáticas, na **nossa aplicação**.

# 3. Implementação

* O **código** encontra-se na Classe **TcpSrvAutomaticTask** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/TcpSrvAutomaticTask.java)
* O **código** dos **Listeners** da gramática ***AutomaticTaskScriptGrammar*** encontram-se na Classe **AutomaticTaskScriptGrammar** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript/AutomaticTaskScriptGrammar.java)

# 4. Integração/Demonstração

* Esta US está relacionada com a **US1007 (O sistema seja capaz de executar/interpretar os scripts)**, pois vão ser utilizados os Listeners desenvolvidos nessa US para a **execução dos scripts** das Tarefas Automáticas.

# 5. Observações

* Alguns dos **commits** desta US forma dados com a **US1007**.
