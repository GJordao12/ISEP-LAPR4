#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export HELPDESK_CP=helpdesk.app.servicosRH.console/target/app.servicosRH.console-1.3.0-SNAPSHOT.jar:helpdesk.app.servicosRH.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $HELPDESK_CP eapli.helpdesk.app.servicosRH.console.HelpDeskServicosRH
