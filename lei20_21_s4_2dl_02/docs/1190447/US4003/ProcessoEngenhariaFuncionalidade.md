# US4003  - Aumentar a robustez do Motor De Fluxos De Atividade
==================================================================

# 1. Requisitos

**US 4003:**

* Como Gestor de Projeto, eu pretendo que a equipa conclua o desenvolvimento do motor de fluxos de atividades tornando-o bastante robusto.

# 2. Dados Servidor

* Nesta US o Motor de Fluxos atua como servidor .

     | Server IP  | Server Port |
     |:---------- |:----------- |
     |10.9.21.88  |2021         |

# 3. Fluxo de Troca de Mensagens

1. **Espera** pela mensagem do **Cliente** com o **Código de Teste (0)**.
2. **Manda** ao **Cliente** o **Código de Entendido (2)**.
3. **Recebe** a opção e guarda-a.
4. De acordo com a opção recebida é feito determinado código.
8. **Espera** pela mensagem do **Cliente** com o **Código de Fim (1)**.
9. **Manda** ao **Cliente** o **Código de Entendido (2)**.
10. **Fecha** o Socket.

* (**NOTA**: Caso exista algum problema durante a troca de mensagens o **socket é fechado**)


# 4. Implementação

* Todos os **tipos de erros** durante a **troca de mensagens**, que possam surgir, são completamente **verificados**.

# 5. Integração/Demonstração

* Esta US é a continuação das USs 4001 e 4002 .

* Esta US está relacionada com a US3011,US3021,US3022, na medida em que todas estas precisam de ir buscar as tarefas à base de dados através do motor de fluxos.

# 6. Observações

* -
