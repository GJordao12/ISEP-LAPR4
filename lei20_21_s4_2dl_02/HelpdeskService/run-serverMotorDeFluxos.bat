REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET HELPDESK_CP=helpdesk.app.MotorDeFluxos\target\app.motorDeFluxos-1.3.0-SNAPSHOT.jar;helpdesk.app.MotorDeFluxos\target\dependency\*;
REM call the java VM, e.g,
java -cp %HELPDESK_CP% eapli.helpdesk.app.MotorDeFluxos.TcpMotor.TcpSrvMotorDeFluxos
