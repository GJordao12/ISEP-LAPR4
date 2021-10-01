#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export HELPDESK_CP=ecafeteria.app.bootstrap/target/app.bootstrap-1.3.0-SNAPSHOT.jar:ecafeteria.app.bootstrap/target/dependency/*;

#REM call the java VM, e.g,
java -cp $HELPDESK_CP eapli.ecafeteria.app.bootstrap.HelpdeskBootstrap -bootstrap:demo -smoke:basic
