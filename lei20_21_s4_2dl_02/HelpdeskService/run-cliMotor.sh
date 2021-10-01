#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies

export HELPDESK_CP=helpdesk.app.motorDeFluxos/target/app.motorDeFluxos-1.3.0-SNAPSHOT.jar:helpdesk.app.motorDeFluxos/target/dependency/*;

  java -cp $HELPDESK_CP eapli.helpdesk.app.MotorDeFluxos.TcpMotor.TcpCliMotor