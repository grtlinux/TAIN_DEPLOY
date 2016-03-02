@echo on

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set M2_HOME=N:\PROG\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set JOB_HOME=N:\TEMP\deploy\client

cd %JOB_HOME%

:: ----------------------------------------------------------------------------
:: KEY_TIME

if "%1" == "" (
	:: set KEY_TIME=NO_KEY
	set KEY_TIME=00000000-000000
) else (
	set KEY_TIME=%1
)

echo KEY_TIME = %KEY_TIME%

:: ----------------------------------------------------------------------------
:: version check

cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause
:: goto END

:: ----------------------------------------------------------------------------
:: export

del %JOB_HOME%\SASEMARTCMS-1.0.0-%KEY_TIME%.war

rmdir /S /Q   %JOB_HOME%\SASEMARTCMS

cmd /c svn export svn://localhost/REPO_02/SASEMARTCMS   %JOB_HOME%\SASEMARTCMS

echo ########################## EXPORT SUCCESS ###########################

:: pause

:: ----------------------------------------------------------------------------
:: build

set LIB_HOME=%JOB_HOME%\SASEMARTCMS\src\main\webapp\WEB-INF\lib

cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=ibatis                 -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\ibatis-2.3.4.726b.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=java-json              -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\java-json.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=log4j                  -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\log4j.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=modules.tain           -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\modules.tain.0.151031.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core               -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.core.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core.nls           -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.core.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.rutil              -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.rutil.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.security.sspi      -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.security.sspi.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet            -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.servlet.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet.nls        -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.servlet.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection     -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.svc.connection.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection.nls -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sas.svc.connection.nls.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=sastpj.rutil           -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\sastpj.rutil.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=scsl                   -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\scsl.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=tdgssconfig            -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\tdgssconfig.jar
cmd /c mvn install:install-file -DgroupId=kang.tain -DartifactId=terajdbc4              -Dversion=1.0 -Dpackaging=jar -Dfile=%LIB_HOME%\terajdbc4.jar


xcopy /Y ..\conf\pom.xml SASEMARTCMS

echo %KEY_TIME% > SASEMARTCMS\%KEY_TIME%

cmd /c mvn -file  %JOB_HOME%\SASEMARTCMS       clean install

echo ########################## MAVEN COMPILE SUCCESS ###########################


:: ----------------------------------------------------------------------------
:: finish

move %JOB_HOME%\SASEMARTCMS\target\SASEMARTCMS-1.0.0.war %JOB_HOME%\SASEMARTCMS-1.0.0-%KEY_TIME%.war

echo ########################## MOVE SUCCESS ###########################

:: ----------------------------------------------------------------------------
:: finish

:END

echo ########################## ALL SUCCESS [ TR0101.bat ] [ %KEY_TIME% ] ###########################

