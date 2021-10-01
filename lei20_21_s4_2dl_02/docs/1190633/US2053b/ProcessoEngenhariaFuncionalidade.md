# US2053b - Remover Colaborador a Equipa
=================================================================

# 1. Requisitos

**US2053b:**

* Como **Responsável de Recursos Humanos (RRH)** pretendo **remover** um **colaborador**, existente no sistema, de uma **equipa**, existente no sistema.

* A interpretação feita deste requisito foi no sentido de as **equipas corresponderem a um conjunto de vários colaboradores**, que num dado contexto pretende-se que
sejam vistos/considerados/mencionados como um **grupo**.

# 2. Análise

## Excerto do Modelo de Domínio

![MD_US2053b.png](MD_US2053b.png)

# 3. Design

## 3.1. Realização da Funcionalidade

![SD_US2053b.svg](SD_US2053b.svg)

## 3.2. Diagrama de Classes

(não é necessário)

## 3.3. Padrões Aplicados

- Controller

- Creator

- Repository

- Factory

- Persistence Context

- DTO

# 4. Implementação

- Foi utilizado o **Padrão DTO**.
- Para os **Value Objects** que estão submetidos a regras de negócio, foram criadas classes específicas para eles, de modo a estas **regras serem validadas**.

# 5. Integração/Demonstração

* Esta **US** vai proporionar o **Team Management**, ou seja, **remover** colaboradores de equipas, que foram anteriormente adicionados.

# 6. Observações

* -
