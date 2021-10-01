REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET EHELPDESK_CP=helpdesk.app.portal.console\target\app.portal.console-1.3.0-SNAPSHOT.jar;helpdesk.app.portal.console\target\dependency\*;

REM call the java VM, e.g,
java -cp %EHELPDESK_CP% eapli.helpdesk.app.portal.console.HelpDeskPortal
