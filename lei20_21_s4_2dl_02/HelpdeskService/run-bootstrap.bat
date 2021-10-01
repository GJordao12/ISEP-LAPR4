REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET EHELPDESK_CP=helpdesk.app.bootstrap\target\app.bootstrap-1.3.0-SNAPSHOT.jar;helpdesk.app.bootstrap\target\dependency\*;

REM call the java VM, e.g, 
java -cp %EHELPDESK_CP% eapli.helpdesk.app.bootstrap.HelpdeskBootstrap -bootstrap:demo -smoke:basic
