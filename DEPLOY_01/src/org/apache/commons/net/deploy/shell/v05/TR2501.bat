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

set BIN_PATH=%DEPLOY_HOME%\bin

set JOB_HOME=%DEPLOY_HOME%\server

set WAR_PATH=%JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME%

set WAR_FILE=%JOB_HOME%\SASEMARTCMS-1.0.0-%DEPLOY_TIME%.war

set WAS_HOME=%JOB_HOME%\app02\sas_webapps\sas.emartcms.war

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
:: 1. stop WAS : TODO 2016.03.02

:STOP_WAS

:: goto END

:: ----------------------------------------------------------------------------
:: 2. decompress SASEMARTCMS-1.0.0-YYYYMMDD-HHMMSS.war

:DECOMPRESS_WAR

cd %JOB_HOME%

rmdir /S /Q %WAR_PATH%

mkdir %WAR_PATH%

cd %WAR_PATH%

cmd /c jar xvf %WAR_FILE%

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

xcopy /C /E /H /Y /R   %WAR_PATH% .

:: goto END

:: ----------------------------------------------------------------------------
:: 5. delete job folders and files : SASEMARTCMS-1.0.0-YYYYMMDD-HHMMSS/

:DELETE_JOB

cd %JOB_HOME%

rmdir /S /Q    %WAR_PATH%

goto END

:: ----------------------------------------------------------------------------
:: -. start WAS : TODO 2016.03.02

:START_WAS


:: ----------------------------------------------------------------------------
:: finish

:END

echo ########################## ALL SUCCESS [ TR0501.bat ] [ %DEPLOY_TIME% ] ###########################

cd N:\TEMP\deploy\bin

