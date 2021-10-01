# Helpdesk Management

     _    _      _           _           _    
    | |  | |    | |         | |         | |   
    | |__| | ___| |_ __   __| | ___  ___| | __
    |  __  |/ _ \ | '_ \ / _` |/ _ \/ __| |/ /
    | |  | |  __/ | |_) | (_| |  __/\__ \   <
    |_|  |_|\___|_| .__/ \__,_|\___||___/_|\_\
                  | |                         
                  |_|


Laboratório e Projeto IV (LAPR4)

Polythecnic of Porto, School of Engineering

---------------------------------------------

This application is part of the lab project for the course unit LAPR4.

_helpdesk logo created with [kammerl ascii signature](https://www.kammerl.de/ascii/AsciiSignature.php) using font "big"_

## Who do I talk to?

Bruno Gabriel Flores Silva [1190447@isep.ipp.pt](emailto:1190447@isep.ipp.pt) / [bruno10000silva@gmail.com](emailto:bruno10000silva@gmail.com)

Gonçalo Ribeiro Teles Jordão [1190633@isep.ipp.pt](emailto:1190633@isep.ipp.pt) / [grtjordao782001@gmail.com](emailto:grtjordao782001@gmail.com)

Diogo Miguel Monte Domingues [1190523@isep.ipp.pt](emailto:1190523@isep.ipp.pt) / [dimigas11@gmail.com](emailto:dimigas11@gmail.com)

Ricardo Jorge Monteiro Mesquita [1190995@isep.ipp.pt](emailto:1190995@isep.ipp.pt) / [ricardomesquita5@hotmail.com](emailto:ricardomesquita5@hotmail.com)
## License and copyright

Copyright (c) 2020-2021 the original author or authors.

MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Build

Make sure Maven is installed and on the PATH.

The java source is Java 1.8+ so any JDK 1.8 or later will work. However, in order to generate the javadoc and UML diagrams the JDK version must be *strictly 1.8*.

If using an Oracle database, you will need to change your maven settings for 
downloading the Oracle drivers. see <https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc#settings> for more information.

run script 

    rebuild-all.bat

## Running

make sure a JRE is installed and on the PATH

run script 

    run-servicosRH

or 

    run-portal

## Project structure

- helpdesk.app.servicosRH.console

  - presentation using console
  - Main class 
  - application properties in resource folder

- helpdesk.app.bootstrap 
  - bootstrap data. should be ignored on a "real" instalation

- helpdesk.core 
  - use case controllers, model, and persistence
  
- helpdesk.app.executorTarefasAutomaticas
  - server to execute automatic tasks
  - application properties in resource folder
  
- helpdesk.app.MotorDeFluxos
  - server to manage the flow of activities
  - client to comunicate whit Automatic Task Manager

- helpdes.app.portal.console
  - presentation using console and html web page
  - Main class
  - application properties in resource folder
  
## Architecture

The application follows a typical layered approach

    UI -> Controller -+-> Domain
                      |     ^
                      |     |
                      +-> Repositories


### Domain objects with persistence knowledge or not

Two different approaches are possible:

- pure domain objects without any knowledge of the persistence
- domain objects that can save and load thenselves from persistence (thus, an Active Record)

In the first case, the controller is responsible for obtaining the domain objects 
from the repository, asking the domain objects to perform the business logic and 
then pass them back to the repository. in this case, the domain objects can "easily" 
be tested as they do not depend on any other package this gets trickier when we 
need/want to have lazy load of collections...

In the second case, the controller asks the domain object class to load a certain 
instance, asks that object to perform the business operation and then asks the object 
to save itself back to the database

### Passing domain objects to the UI or not

The decision is to use domain objects outside of the controllers boundary. One could 
argue that domain objects should be known only "inside" the application boundary and 
as such other data structures should be returned to outside layers, i.e., DTO (Data Transfer Objects).

### Performing calculations in memory or directly at the persistence layer

Both approaches have advantages and disavantages:

- in memory

  - advantages

    - allows the use of business logic in code
    - disavantages performance may be poor

- at persistence layer

  - advantages

    - use of aggregated SQL functions is straigth forward
    - performance

  - disavantages

    - complicated business logic is hard to implement

//TODO provide one example of each approach.

See also <http://www.martinfowler.com/articles/dblogic.html>

### Factoring out common behaviour

use services at the application or domain layer

### Can controllers call other controllers?

it is best if they call application services

### Should the UI/controller create domain objects directly

Should the rules for the Creator pattern be fully enforced, e.g., the responsibility to 
create a Payment should be of Expense, or can the controller/UI create a Payment and
 pass it to the Expense?

### How to reuse behavior betwen controllers

Factor out common behaviour in an application service.

### When showing movements gruped by type, who performs the sum operation? UI, Controller or Domain object?

the UI might not be smart enough to compute the total sum with enough precision, and 
would carry a burden for the computer running the interface

the Controller might indeed perform such calculation as it has all the data is needs 
for a short period of time, but it is not the controller function to perform mathematical 
operations

the domain object might indeed be the very best resource to calculate the sum for each 
expense type, but it would not make sense to delegate the domain object to the interface.

### When showing movements gruped by type, should types with no values also appear?

this can be another strategy

## References and bibliography

Start by reading the essential material listed in [EAPLI framework](https://bitbucket.org/pag_isep/eapli.framework/src/master/README.md)

### JPA

- [Entities or DTOs in JPA Queries](https://thoughts-on-java.org/entities-dtos-use-projection/)
- [Primary key mapping](https://thoughts-on-java.org/primary-key-mappings-jpa-hibernate/)

### Other useful readings

T.B.D.
