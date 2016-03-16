#!/bin/sh

#--------------------------------------------------------------------------------------------
# DEPLOY_TIME

if [ "$1" = "" ]; then
	export DEPLOY_TIME=00000000-000001
	/bin/echo "USAGE : need to add a argument of DEPLOY_TIME"
	exit
else
	export DEPLOY_TIME=$1
fi

echo "DEPLOY_TIME = $DEPLOY_TIME"

#-------------------------------------------------------------------------------------------
# set environment

export JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.45.x86_64
export M2_HOME=/sas/sasuser/sas/.jenkins/apache-maven-3.3.3
export SVN_HOME=/usr/bin
export SVN_EDITOR=
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$SVN_HOME:$PATH

export DEPLOY_HOME=/sas/sasuser/sas/deploy

export BIN_PATH=$DEPLOY_HOME/bin

export JOB_HOME=$DEPLOY_HOME/server

export WAR_PATH=$JOB_HOME/SASEMARTCMS-1.0.0-$DEPLOY_TIME
export WAR_FILE=$JOB_HOME/SASEMARTCMS-1.0.0-$DEPLOY_TIME.war

# TODO 2016.03.16 : change the below in server app01
export WAS_HOME=$JOB_HOME/app01/sas_webapps/sas.emartcms.war


#--------------------------------------------------------------------------------------------
# version check

svn --version
/bin/echo "-------------- svn version -------------------"

java -version
/bin/echo "-------------- java version -------------------"

mvn --version
/bin/echo "-------------- maven version -------------------"

# exit

#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
#--------------------------------------------------------------------------------------------
# 1. stop WAS

# exit

#--------------------------------------------------------------------------------------------
# 2. decompress

cd $JOB_HOME

rm -rf $WAR_PATH

mkdir $WAR_PATH

cd $WAR_PATH

jar xvf $WAR_FILE

# exit

#--------------------------------------------------------------------------------------------
# 3. delete files and folders

cd $WAS_HOME

for ITEM in `ls`
do
	if [ "$ITEM" != "FILES" ]; then
		rm -rf $ITEM
		echo deleted $ITEM 
	fi
done

#--------------------------------------------------------------------------------------------
# 4. move files and folders

cd $WAS_HOME

cp -r $WAR_PATH/* .

#--------------------------------------------------------------------------------------------
# 5. delete job folders and files

cd $JOB_HOME

rm -rf $WAR_PATH


#--------------------------------------------------------------------------------------------
# 6. start WAS



#--------------------------------------------------------------------------------------------
# finish

/bin/echo "-------------- FINISH  -------------------"

cd $BIN_PATH

