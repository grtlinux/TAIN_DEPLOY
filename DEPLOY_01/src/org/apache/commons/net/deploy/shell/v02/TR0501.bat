@echo on

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set M2_HOME=N:\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set JOB_HOME=N:\TEMP\deploy\server

set DATE1=%date:-=%
set TIME1=%time::=%

set DATE2=%DATE1:~2,6%
set TIME2=%TIME1:~0,4%

set NOW=%DATE1%%TIME2%

echo %DATE1%
echo %DATE2%
echo %TIME1%
echo %TIME2%
echo %NOW%

:: goto END
:: goto MOVE_FILES

:: ----------------------------------------------------------------------------
:: version check

cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause
goto END

:: ----------------------------------------------------------------------------
:: ready

M:

cd %JOB_HOME%
cd

:: ----------------------------------------------------------------------------
:: deploy folder unzip

mkdir emart.sas.web
dir

cd emart.sas.web
cd

jar xvf ../SASEMARTCMS-1.0.0.war

cd ..

:: ----------------------------------------------------------------------------
:: backup

rmdir /S /Q  %JOB_HOME%\emart.web.%NOW%

mkdir %JOB_HOME%\emart.web.%NOW%
xcopy /C /E /H /Y /R %JOB_HOME%\emart.web  %JOB_HOME%\emart.web.%NOW%


:: ----------------------------------------------------------------------------
:: erase old articles

cd emart.web

dir

rmdir /S /Q  css html images js menu META-INF WEB-INF

del /Q *.*

dir

cd ..

:: ----------------------------------------------------------------------------
:: move new articles

:MOVE_FILES

cd emart.sas.web

dir

@FOR    %%A IN (*) DO MOVE /Y %%A ..\emart.web

@FOR /D %%A IN (*) DO MOVE /Y %%A ..\emart.web

:: move /Y *.*       ..\emart.web
:: move /Y css       ..\emart.web
:: move /Y html      ..\emart.web
:: move /Y images    ..\emart.web
:: move /Y js        ..\emart.web
:: move /Y menu      ..\emart.web
:: move /Y META-INF  ..\emart.web
:: move /Y WEB-INF   ..\emart.web

dir

cd ..

rmdir /S /Q emart.sas.web


:: ----------------------------------------------------------------------------
:: finish

:END

echo "########################## ALL SUCCESS [ TR0501.bat ] ###########################"

:: @for /L %A in (1,1,100) do echo %A

:: @FOR    %A IN (*) DO MOVE /Y %A ..\emart.web

:: @FOR /D %A IN (*) DO MOVE /Y %A ..\emart.web

:: @FOR /R %A IN (.) DO RM %A

