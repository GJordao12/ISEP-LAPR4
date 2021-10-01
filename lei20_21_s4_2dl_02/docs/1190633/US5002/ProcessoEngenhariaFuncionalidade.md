# US5002 - Comunicações realizadas como aplicação cliente através do protocolo SDP2021 estejam protegidas (Executor Tarefas Automáticas)
=====================================================================

# 1. Requisitos

**US5002:**

* Como **Gestor de Projeto**, eu pretendo que as **comunicações** realizadas através do protocolo SDP2021 estejam **protegidas**.

# 2. Dados do Servidor do Executor de Tarefas Automáticas

* Os dados relativos ao **servidor**, tanto o **IP**, a **Porta**, **Trusted Store** e **Keys Store Pass** a ser utilizada, encontram-se presentes no ficheiro ***application.properties*** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/resources/application.properties) da aplicação ***helpdesk.app.executorTarefasAutomaticas*** (HelpdeskService/helpdesk.app.executorTarefasAutomaticas).

     | Server IP  | Server Port | Trusted Store     | Keys Store Pass |
     |:---------- |:----------- |:----------------- |:--------------- |
     |10.9.21.109 |2020         |serverExecutor.jks |123abc456def     |

# 3. Certificados

* Todos os **certificados**, tanto do **servidor** como do **cliente**, para a comunicação com o **Servidor da Execução das Trefas Automáticas** são gerados a partir do script do ficheiro ***make_certs_executor_tarefas_automaticas.sh*** (HelpdeskService/make_certs_executor_tarefas_automaticas.sh).

## 3.1. Certificados Utilizados

* Para o servidor: ***serverExecutor.jks*** (HelpdeskService/serverExecutor.jks)
* Para o cliente: ***cliente1Executor.jks*** (HelpdeskService/cliente1Executor.jks)

# 4. Implementação

* Foi utilizado o **Protocolo de Comunicação SDP2021**.
* Ficheiro de Configurações: **application.properties**.
* Foram utilizados **certificados** para que exista uma **comunicação segura** entre o servidor e o cliente.

# 5. Integração/Demonstração

* Esta **US** está relacionada com a **US4001 (Motor de Fluxos de Atividades)**, ou seja quando o Motor de Fluxos de Atividades encontrar uma **Tarefa Automática** vai a reencaminhar, utilizando o **Protocolo SDP2021** e uma **comunicação segura** através dos **certificados**, para o **servidor do Executor de Tarefas Automáticas** para que este a possa **realizar**.

# 6. Observações

* -
