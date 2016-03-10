@echo on

:: ----------------------------------------------------------------------------
:: set environment

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set M2_HOME=N:\PROG\apache-maven-3.3.3
set PATH=%PATH%;%JAVA_HOME%\bin;%M2_HOME%\bin;

set JOB_HOME=N:\TEMP\deploy\server

set WAS_HOME=N:\TEMP\deploy\server\app01\sas_webapps\sas.emartcms.war

:: ----------------------------------------------------------------------------
:: DEPLOY_TIME

if "%1" == "" (
	:: set DEPLOY_TIME=NO_KEY
	set DEPLOY_TIME=00000000-000000
) else (
	set DEPLOY_TIME=%1
)

echo DEPLOY_TIME = %DEPLOY_TIME%

:: ----------------------------------------------------------------------------
:: version check

cmd /c svnserve --version

cmd /c java -version

cmd /c mvn --version

:: pause
:: goto END

:: ----------------------------------------------------------------------------
:: variables of DATE, and TIME

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

:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: ----------------------------------------------------------------------------
:: 1. stop WAS : TODO 2016.03.02

:STOP_WAS

:: goto END

:: ----------------------------------------------------------------------------
:: 2. decompress SASEMARTCMS-1.0.0-YYYYMMDD-HHMMSS.war

:DECOMPRESS_WAR

cd %JOB_HOME%

rmdir /S /Q SASEMARTCMS-1.0.0-%DEPLOY_TIME%

mkdir SASEMARTCMS-1.0.0-%DEPLOY_TIME%

cd SASEMARTCMS-1.0.0-%DEPLOY_TIME%

cmd /c jar xvf ..\SASEMARTCMS-1.0.0-%DEPLOY_TIME%.war

:: goto END

:: ----------------------------------------------------------------------------
:: 3. delete files of old version of sas_webapps/sas.emartcms.war/*, except FILES folder
::    TODO 2016.03.02 : do java programming

:DELETE_WAS

cd %WAS_HOME%

:: delete files
FOR %%A IN (*) DO (
	ECHO %%A
	del /q %%A
)

:: remove folders
@for /d %%a in (*) do (

	if not "%%a" == "FILES" (
		echo %%a
		rmdir /S /Q  %%a
	)
)

:: goto END

:: ----------------------------------------------------------------------------
:: 4. move files from SASEMARTCMS-1.0.0-YYYYMMDD-HHMMSS/* to sas_webapps/sas.emartcms.war/

:MOVE_FILES

cd %WAS_HOME%

xcopy /C /E /H /Y /R %JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME% .

:: goto END

:: ----------------------------------------------------------------------------
:: 5. delete job folders and files : SASEMARTCMS-1.0.0-YYYYMMDD-HHMMSS/

:DELETE_JOB

cd %JOB_HOME%

rmdir /S /Q SASEMARTCMS-1.0.0-%DEPLOY_TIME%

goto END

:: ----------------------------------------------------------------------------
:: -. start WAS : TODO 2016.03.02

:START_WAS


:: ----------------------------------------------------------------------------
:: finish

:END

echo ########################## ALL SUCCESS [ TR0501.bat ] [ %DEPLOY_TIME% ] ###########################

cd N:\TEMP\deploy\bin

