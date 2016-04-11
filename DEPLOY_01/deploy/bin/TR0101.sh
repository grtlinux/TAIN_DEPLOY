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
export JOB_HOME=$DEPLOY_HOME/client

export EXPORT_PATH=$JOB_HOME/SASEMARTCMS

export WAR_FILE=$JOB_HOME/SASEMARTCMS-1.0.0-$DEPLOY_TIME.war

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
# svn export

/bin/rm -f  $WAR_FILE

/bin/rm -rf $EXPORT_PATH
 
svn export svn://matcmsmine01/repo-tasks/SASEMARTCMS $EXPORT_PATH --username fic01524 --password Kang123!

/bin/echo "-------------- svn export OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# pom.xml

export LIB_HOME=$EXPORT_PATH/src/main/webapp/WEB-INF/lib

# mvn install:install-file -DgroupId=kang.tain -DartifactId=ibatis                 -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/ibatis-2.3.4.726b.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=java-json              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/java-json.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=log4j                  -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/log4j.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=modules.tain           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/modules.tain.0.151031.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core               -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.core.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core.nls           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.core.nls.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.rutil              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.rutil.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.security.sspi      -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.security.sspi.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet            -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.servlet.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet.nls        -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.servlet.nls.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection     -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.svc.connection.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection.nls -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.svc.connection.nls.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=sastpj.rutil           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sastpj.rutil.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=scsl                   -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/scsl.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=tdgssconfig            -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/tdgssconfig.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=terajdbc4              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/terajdbc4.jar

# mvn install:install-file -DgroupId=kang.tain -DartifactId=json                   -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/json-20151123.jar
# mvn install:install-file -DgroupId=kang.tain -DartifactId=gson                   -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/gson-2.6.2.jar

cp $DEPLOY_HOME/conf/pom.xml $EXPORT_PATH

echo "$DEPLOY_TIME" > $EXPORT_PATH/src/main/webapp/$DEPLOY_TIME

/bin/echo "-------------- pom.xml OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# clean build

mvn -file $EXPORT_PATH  clean install

/bin/echo "-------------- clean build OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# finish

# mv $EXPORT_PATH/target/SASEMARTCMS-1.0.0.war $WAR_FILE

cd $EXPORT_PATH/target/SASEMARTCMS-1.0.0

jar cvf $WAR_FILE *


/bin/echo "-------------- FINISH  -------------------"







