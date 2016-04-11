@echo on

:: ----------------------------------------------------------------------------
:: DEPLOY_TIME

if "%1" == "" (
	:: set DEPLOY_TIME=NO_KEY
	set DEPLOY_TIME=00000000-000001
) else (
	set DEPLOY_TIME=%1
)

echo DEPLOY_TIME = %DEPLOY_TIME%

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set M2_HOME=N:\PROG\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set DEPLOY_HOME=N:\TEMP\deploy
set JOB_HOME=%DEPLOY_HOME%\client

set EXPORT_PATH=%JOB_HOME%\SASEMARTCMS

set WAR_FILE=%JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME%.war

:: ----------------------------------------------------------------------------
:: version check


cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause
:: goto END

:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: 1. export

cd %JOB_HOME%

del %WAR_FILE%

rmdir /S /Q   %EXPORT_PATH%

cmd /c svn export svn://localhost/REPO_02/SASEMARTCMS   %EXPORT_PATH%
::cmd /c svn export svn://matcmsmine01/repo-tasks/SASEMARTCMS %EXPORT_PATH% --username fic01524 --password Kang123!

:: goto END

:: ----------------------------------------------------------------------------
:: 2. set environment

set LIB_HOME=%EXPORT_PATH%\src\main\webapp\WEB-INF\lib

::cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=commons-logging-adapters     -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\commons-logging-adapters-1.1.jar
::cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=commons-logging              -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\commons-logging-1.2.jar

cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=commons-logging-adapters     -Dversion=1.0 -Dpackaging=jar -Dfile=%DEPLOY_HOME%\lib\commons-logging-adapters-1.1.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=commons-logging              -Dversion=1.0 -Dpackaging=jar -Dfile=%DEPLOY_HOME%\lib\commons-logging-1.2.jar

cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=ibatis                       -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\ibatis-2.3.4.726b.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=java-json                    -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\java-json.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=log4j                        -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\log4j.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=modules.tain                 -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\modules.tain.0.151031.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core                     -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.core.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core.nls                 -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.core.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.rutil                    -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.rutil.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.security.sspi            -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.security.sspi.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet                  -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.servlet.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet.nls              -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.servlet.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection           -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.svc.connection.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection.nls       -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.svc.connection.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sastpj.rutil                 -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sastpj.rutil.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=scsl                         -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\scsl.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=tdgssconfig                  -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\tdgssconfig.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=terajdbc4                    -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\terajdbc4.jar

:: added by Seok Kang at 2016.04.08
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=json                         -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\json-20151123.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=gson                         -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\gson-2.6.2.jar

xcopy /Y ..\conf\pom.xml %EXPORT_PATH%

:: ----------------------------------------------------------------------------
:: 3. set version DEPLOY_TIME

echo %DEPLOY_TIME% > %EXPORT_PATH%\src\main\webapp\%DEPLOY_TIME%

:: ----------------------------------------------------------------------------
:: 4. install

cmd /c mvn -file  %EXPORT_PATH%       clean install

echo ########################## MAVEN COMPILE SUCCESS ###########################


:: ----------------------------------------------------------------------------
:: 5. move SASEMARTCMS-XXX.war

:: move %EXPORT_PATH%\target\SASEMARTCMS-1.0.0.war %JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME%.war

cd %EXPORT_PATH%\target\SASEMARTCMS-1.0.0

%JAVA_HOME%\bin\jar cvf %JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME%.war *

echo ########################## MOVE SUCCESS ###########################

:: ----------------------------------------------------------------------------
:: finish

:END

echo ########################## ALL SUCCESS [ TR0101.bat ] [ %DEPLOY_TIME% ] ###########################

cd N:\TEMP\deploy\bin
