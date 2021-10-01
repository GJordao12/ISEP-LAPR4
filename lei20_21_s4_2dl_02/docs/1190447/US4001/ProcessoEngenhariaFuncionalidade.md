# US4001 Motor de fluxo de atividade
==================================================================

# 1. Requisitos

**US 4001:**

* Como Gestor de Projeto, eu pretendo que seja desenvolvido no Motor de Fluxo de Atividade o mecanismo de gestão/controlo/avanço do fluxo de atividades de um dado pedido.

# 2. Dados Servidor

* Nesta US o Motor de Fluxos atua como cliente enviando a tarefa automática ao executor de tarefas automáticas.

     | Server IP  | Server Port |
     |:---------- |:----------- |
     |10.9.21.109  |2020         |

# 3. Fluxo de Troca de Mensagens

1. **Manda** ao **Servidor** o **Código de Teste (0)**.
2. **Espera** pela mensagem do **Servidor** com o **Código de Entendido (2)**.
3. **Manda** ao **Servidor** a **Tarefa Automática**.
4. **Espera** pela mensagem do **Servidor** com o **Código de Entendido (2)**.
7. **Manda** ao **Servidor** o **Código de Fim (1)**.
8. **Espera** pela mensagem do **Servidor** com o **Código de Entendido (2)**.
9. **Fecha** o Socket.

* (**NOTA**: Caso exista algum problema durante a troca de mensagens o **socket é fechado**)


# 4. Implementação

* Todos os **tipos de erros** durante a **troca de mensagens**, que possam surgir, são completamente **verificados**.

# 5. Integração/Demonstração

* Esta US está relacionada com a **US5001 (Executor De Tarefas Automáticas)**, ou seja quando o Motor De Fluxos encontrar uma tarefa automática este irá reencaminha la, utilizando o **Protocolo SDP2021**, para o **servidor do Executor de Tarefas Automáticas** para que este a possa **realizar**.

# 6. Observações

* -
