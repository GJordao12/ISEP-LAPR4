# US4002  - Motor De Fluxos De Atividade
==================================================================

# 1. Requisitos

**US 4002:**

* Como Gestor de Projeto, eu pretendo que o Motor de Fluxo de Atividades disponibilize, a pedido, os dados necessários às aplicações "Serviços e RH" e "Portal dos Utilizadores".

# 2. Dados Servidor

* Nesta US o Motor de Fluxos atua como servidor .

     | Server IP  | Server Port |
     |:---------- |:----------- |
     |10.9.21.88  |2021         |

# 3. Fluxo de Troca de Mensagens

1. **Espera** pela mensagem do **Cliente** com o **Código de Teste (0)**.
2. **Manda** ao **Cliente** o **Código de Entendido (2)**.
3. **Espera** pela mensagem do **Cliente** com o **Colaborador** que está **logado**.
4. **Manda** informação de acordo com a opção :
    -se a opção for 4 então são enviadas para o cliente todas as tarefas associadas ao colaborador logado.
    -se a opção for 3 então são enviadas para o cliente todas as tarefas que o colaborador logado pode realizar.
    -se a opção for 5 a tarefa é assignada ao colaborador logado.
8. **Espera** pela mensagem do **Cliente** com o **Código de Fim (1)**.
9. **Manda** ao **Cliente** o **Código de Entendido (2)**.
10. **Fecha** o Socket.

* (**NOTA**: Caso exista algum problema durante a troca de mensagens o **socket é fechado**)


# 4. Implementação

* Todos os **tipos de erros** durante a **troca de mensagens**, que possam surgir, são completamente **verificados**.

# 5. Integração/Demonstração

* Esta US está relacionada com a US3011,US3021,US3022, na medida em que todas estas precisam de ir buscar as tarefas à base de dados através do motor de fluxos.

# 6. Observações

* -
