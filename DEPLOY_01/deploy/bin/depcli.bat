@echo off
:: ------------------------------------------------------------------

set MAIN_CLASS=org.apache.commons.net.deploy.client.TainClientMain

:: ------------------------------------------------------------------

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set JRE_HOME=%JAVA_HOME%

:: set JAVA_EXE=%JAVA_HOME%\bin\javaw.exe
set JAVA_EXE=%JAVA_HOME%\bin\java.exe

:: ------------------------------------------------------------------

set DEPLOY_HOME=N:\TEMP\deploy

set LIB_HOME=%DEPLOY_HOME%\lib

:: ------------------------------------------------------------------

set CP=
set CP=%CP%;%JAVA_HOME%\lib\tools.jar
set CP=%CP%;%JRE_HOME%\lib\rt.jar
set CP=%CP%;%JRE_HOME%\lib\resources.jar
set CP=%CP%;%JRE_HOME%\lib\jsse.jar
set CP=%CP%;%JRE_HOME%\lib\jce.jar
set CP=%CP%;%JRE_HOME%\lib\charsets.jar
set CP=%CP%;%JRE_HOME%\lib\ext\dnsns.jar
set CP=%CP%;%JRE_HOME%\lib\ext\localedata.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunjce_provider.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunmscapi.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunpkcs11.jar

set CP=%CP%;%LIB_HOME%\ant.jar
set CP=%CP%;%LIB_HOME%\commons-logging-1.2.jar
set CP=%CP%;%LIB_HOME%\commons-logging-adapters-1.1.jar
set CP=%CP%;%LIB_HOME%\commons-net-3.3.0.jar
set CP=%CP%;%LIB_HOME%\jnlp.jar
set CP=%CP%;%LIB_HOME%\log4j-1.2.17.jar
::set CP=%CP%;%LIB_HOME%\tain-deploy-1.0.jar

:: ------------------------------------------------------------------

set OPTION=-Xms512m -Xmx1024m
set OPTION=%OPTION% -cp %CP%
set OPTION=%OPTION% -Ddev.serial=KK28RWYXBC1067AS
set OPTION=%OPTION% -Ddev.author=Kang_Seok
set OPTION=%OPTION% -Ddev.version=jdk1.7.0_79

::set OPTION=%OPTION% -Dtain.job.seq.range=1-6
set OPTION=%OPTION% -Dtain.job.seq.range=1-6
set OPTION=%OPTION% -Dtain.date.format=yyyyMMdd-HHmmss
set OPTION=%OPTION% -Dtain.client.host=127.0.0.1
set OPTION=%OPTION% -Dtain.client.port=2025
set OPTION=%OPTION% -Dtain.client.deploy.file.name=%DEPLOY_HOME%/client/SASEMARTCMS-1.0.0-YYYYMMDDHHMMSS.war
set OPTION=%OPTION% -Dtain.client.exec.cmd=%DEPLOY_HOME%/bin/TR0101.bat
set OPTION=%OPTION% -Dtain.client.exec.log=%DEPLOY_HOME%/log/TR0101-YYYYMMDDHHMMSS.log
set OPTION=%OPTION% -Dtain.server.host=127.0.0.1
set OPTION=%OPTION% -Dtain.server.port=2025
set OPTION=%OPTION% -Dtain.server.app01.host=127.0.0.1
set OPTION=%OPTION% -Dtain.server.app01.port=2025
set OPTION=%OPTION% -Dtain.server.app02.host=127.0.0.1
set OPTION=%OPTION% -Dtain.server.app02.port=2025
set OPTION=%OPTION% -Dtain.server.deploy.file.name=%DEPLOY_HOME%/server/SASEMARTCMS-1.0.0-YYYYMMDDHHMMSS.war
set OPTION=%OPTION% -Dtain.server.app01.exec.cmd=%DEPLOY_HOME%/bin/TR1501.bat
set OPTION=%OPTION% -Dtain.server.app01.exec.log=%DEPLOY_HOME%/log/TR1501-YYYYMMDDHHMMSS.log
set OPTION=%OPTION% -Dtain.server.app02.exec.cmd=%DEPLOY_HOME%/bin/TR2501.bat
set OPTION=%OPTION% -Dtain.server.app02.exec.log=%DEPLOY_HOME%/log/TR2501-YYYYMMDDHHMMSS.log

:: ------------------------------------------------------------------

@echo on

%JAVA_EXE% %OPTION%  %MAIN_CLASS%

::pause
