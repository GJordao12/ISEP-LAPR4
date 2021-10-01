# US5001 - Executor de Tarefas Automáticas
==================================================================

# 1. Requisitos

**US5001:**

* Como **Gestor de Projeto**, eu pretendo que seja desenvolvido o **Executor de Tarefas Automáticas**.

# 2. Dados do Servidor do Executor de Tarefas Automáticas

* Os dados relativos ao **servidor**, tanto o **IP** como a **Porta** a ser utilizada, encontram-se presentes no ficheiro ***application.properties*** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/resources/application.properties) da aplicação ***helpdesk.app.executorTarefasAutomaticas*** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas).

     | Server IP  | Server Port |
     |:---------- |:----------- |
     |10.9.21.109 |2020         |

# 3. Fluxo de Troca de Mensagens entre o Servidor e o Cliente

1. **Espera** pela mensagem do **Cliente** com o **Código de Teste (0)**.
2. **Manda** ao **Cliente** o **Código de Entendido (2)**.
3. **Espera** pela mensagem do **Cliente** com a **Atividade Automática** que tem de ser **resolvida**.
4. **Guarda a data e horas de inicio** da resolução da **Atividade Automática**.
5. **Simula a execução** da Atividade Automática (utilizado um ***sleep*** de **10 segundos**).
6. **Guarda a data e horas de fim** da resolução da **Atividade Automática**.
7. **Envia a Atividade Automática** para o **Cliente**.
8. **Espera** pela mensagem do **Cliente** com o **Código de Fim (1)**.
9. **Manda** ao **Cliente** o **Código de Entendido (2)**.
10. **Fecha** o Socket.

* (**NOTA**: Caso exista algum problema durante a troca de mensagens o **socket é fechado**)

# 4. Implementação

* Foi utilizado o **Protocolo de Comunicação SDP2021**.
* Ficheiro de Configurações: **application.properties**.
* Todos os **tipos de erros** durante a **troca de mensagens**, que possam surgir, são completamente **verificados**.

# 5. Integração/Demonstração

* Esta **US** está relacionada com a **US4001 (Motor de Fluxos de Atividades)**, ou seja quando o Motor de Fluxos de Atividades encontrar uma **Tarefa Automática** vai a reencaminhar, utilizando o **Protocolo SDP2021**, para o **servidor do Executor de Tarefas Automáticas** para que este a possa **realizar**.

# 6. Observações

* -
