@echo on

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=M:\PROG\jdk1.7.0_79
set M2_HOME=P:\maven\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set JOB_HOME=N:\TEMP\deploy\client

:: ----------------------------------------------------------------------------
:: version check

cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause

:: ----------------------------------------------------------------------------
:: export

del %JOB_HOME%\SASEMARTCMS-1.0.0.war

rmdir /S /Q   %JOB_HOME%\SASEMARTCMS

cmd /c svn export svn://localhost/REPO_02/SASEMARTCMS   %JOB_HOME%\SASEMARTCMS

echo "########################## EXPORT SUCCESS ###########################"

:: pause

:: ----------------------------------------------------------------------------
:: build

cmd /c mvn -file  %JOB_HOME%\SASEMARTCMS       clean install

echo "########################## MAVEN COMPILE SUCCESS ###########################"


:: ----------------------------------------------------------------------------
:: finish

move %JOB_HOME%\SASEMARTCMS\target\SASEMARTCMS-1.0.0.war %JOB_HOME%

echo "########################## ALL SUCCESS ###########################"

