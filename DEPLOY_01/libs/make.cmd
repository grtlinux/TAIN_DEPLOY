@echo on

::-------------------------------------------
:: environment

set JAVA_HOME=N:\PROG\jdk1.7.0_79

set PATH=%JAVA_HOME%\bin;%PATH%

::-------------------------------------------
:: mkdir

mkdir JOB
cd JOB

::-------------------------------------------
:: jar xvf

jar xvf ..\commons-net-3.3.jar

jar xvf ..\tain-deploy-1.0.jar

jar cvf ..\commons-net-3.3.0.jar META-INF org log4j.properties


::-------------------------------------------
:: finish
:END

pause

