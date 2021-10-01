# US4051  - Proteger comunicações realizadas como aplicação servidora (Motor de fluxos)
==================================================================

# 1. Requisitos

**US 4051:**

* Como Gestor de Projeto, eu pretendo que as comunicações realizadas como aplicação servidora através do protocolo SDP2021 estejam protegidas.

# 2. Dados do Servidor do Motor de fluxos

     | Server IP  | Server Port | Trusted Store     | Keys Store Pass |
     |:---------- |:----------- |:----------------- |:--------------- |
     |10.9.21.88 |2021         |serverMotorFluxos.jks |forgotten    |

# 3. Certificados

* Todos os **certificados**, tanto do **servidor** como do **cliente**, para a comunicação com o **Servidor da Execução do Motor de Fluxos** são gerados a partir do script do ficheiro ***make_certs.sh*** (HelpdeskService/make_certs.sh).

## 3.1. Certificados Utilizados

* Para o servidor: ***serverMotorFluxos.jks*** (HelpdeskService/serverMotorFluxos.jks)
* Para o cliente: ***cliente[1-15]Motor.jks*** (HelpdeskService/cliente[1-15]Motor.jks)

# 4. Implementação

* Foi utilizado o **Protocolo de Comunicação SDP2021**.
* Ficheiro de Configurações: **application.properties**.
* Foram utilizados **certificados** para que exista uma **comunicação segura** entre o servidor e o cliente.

# 5. Integração/Demonstração

* Esta **US** está relacionada com a **US4001 (Motor de Fluxos de Atividades)**, ou seja quando o Motor de Fluxos de Atividades.
