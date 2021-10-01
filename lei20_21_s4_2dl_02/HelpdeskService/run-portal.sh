#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies

export HELPDESK_CP=helpdesk.app.portal.console/target/app.portal.console-1.3.0-SNAPSHOT.jar:helpdesk.app.portal.console/target/dependency/*;

  java -cp $HELPDESK_CP eapli.helpdesk.app.portal.console.HelpDeskPortal