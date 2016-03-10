#!/bin/ksh

DEPLOY_PATH=/sas/sasuser/sas/deploy

cd $DEPLOY_PATH/client
rm -rf *

cd $DEPLOY_PATH/log
rm -rf *

cd $DEPLOY_PATH/server
rm -rf *.war

cd $DEPLOY_PATH/server/app01/sas_webapps/sas.emartcms.war
rm -rf WEB-INF css html images js menu

cd $DEPLOY_PATH/server/app02/sas_webapps/sas.emartcms.war
rm -rf WEB-INF css html images js menu
