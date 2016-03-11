@echo on
:: ------------------------------------------------------------------

set DEPLOY_HOME=N:\TEMP\deploy

:: ------------------------------------------------------------------

cd %DEPLOY_HOME%\client

:: delete files
@FOR %%A IN (*) DO (
	del /q %%A
)

:: remove folders
@for /d %%a in (*) do (
	rmdir /S /Q  %%a
)

::goto END

:: ------------------------------------------------------------------

cd %DEPLOY_HOME%\log

:: delete files
@FOR %%A IN (*) DO (
	del /q %%A
)

:: ------------------------------------------------------------------

cd %DEPLOY_HOME%\server

:: delete files
@FOR %%A IN (*) DO (
	del /q %%A
)

:: ------------------------------------------------------------------

cd %DEPLOY_HOME%\/server/app01/sas_webapps/sas.emartcms.war

:: remove folders
@for /d %%a in (WEB-INF css html images js menu) do (
	rmdir /S /Q  %%a
)

:: ------------------------------------------------------------------

cd %DEPLOY_HOME%\/server/app02/sas_webapps/sas.emartcms.war

:: remove folders
@for /d %%a in (WEB-INF css html images js menu) do (
	rmdir /S /Q  %%a
)

:: ------------------------------------------------------------------

:END

cd %DEPLOY_HOME%\bin

