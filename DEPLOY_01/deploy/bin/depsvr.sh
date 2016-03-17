#!/bin/ksh

#------------------------------------------------------

if [ `ps -ef | grep DeployServer | grep -v grep | grep -v tail | wc -l` -ne 0 ]; then
	echo '################################################'
	echo '# DeployServer Process is already running.     #'
	echo '################################################'
	exit 0
else
	echo '# DeployServer is starting.......'
fi


#------------------------------------------------------

MAIN_CLASS=org.apache.commons.net.deploy.server.TainServerMain

#------------------------------------------------------

JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.45.x86_64
JRE_HOME=$JAVA_HOME

JAVA_EXE=${JAVA_HOME}/bin/java

#------------------------------------------------------

DEPLOY_HOME=/sas/sasuser/sas/deploy
LIB_HOME=${DEPLOY_HOME}/lib

#------------------------------------------------------

CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/rt.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/resources.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/jsse.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/jce.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/charsets.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/dnsns.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/localedata.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunjce_provider.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunmscapi.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunpkcs11.jar

CLASSPATH=${CLASSPATH}:${LIB_HOME}/ant.jar
CLASSPATH=${CLASSPATH}:${LIB_HOME}/commons-logging-1.2.jar
CLASSPATH=${CLASSPATH}:${LIB_HOME}/commons-logging-adapters-1.1.jar
CLASSPATH=${CLASSPATH}:${LIB_HOME}/commons-net-3.3.0.jar
CLASSPATH=${CLASSPATH}:${LIB_HOME}/jnlp.jar
CLASSPATH=${CLASSPATH}:${LIB_HOME}/log4j-1.2.17.jar

#------------------------------------------------------

# OPTION="-Xss256K"
OPTION="-Xms512m -Xmx1024m"
OPTION="${OPTION} -Dname=DeployServer"
OPTION="${OPTION} -Ddev.serial=KK28RWYXBC1067AS"
OPTION="${OPTION} -Ddev.author=Kang_Seok"
OPTION="${OPTION} -Ddev.version=jdk1.7.0_79"

OPTION="${OPTION} -Dtain.job.seq.range=1-1"
# OPTION="${OPTION} -Dtain.job.seq.range=1-6"
OPTION="${OPTION} -Dtain.date.format=yyyyMMdd-HHmmss"
OPTION="${OPTION} -Dtain.client.host=127.0.0.1"
OPTION="${OPTION} -Dtain.client.port=2025"
OPTION="${OPTION} -Dtain.client.deploy.file.name=${DEPLOY_HOME}/client/SASEMARTCMS-1.0.0-YYYYMMDDHHMMSS.war"
OPTION="${OPTION} -Dtain.client.exec.cmd=${DEPLOY_HOME}/bin/TR0101.sh"
OPTION="${OPTION} -Dtain.client.exec.log=${DEPLOY_HOME}/log/TR0101-YYYYMMDDHHMMSS.log"
OPTION="${OPTION} -Dtain.server.host=127.0.0.1"
OPTION="${OPTION} -Dtain.server.port=2025"
OPTION="${OPTION} -Dtain.server.app01.host=127.0.0.1"
OPTION="${OPTION} -Dtain.server.app01.port=2025"
OPTION="${OPTION} -Dtain.server.app02.host=127.0.0.1"
OPTION="${OPTION} -Dtain.server.app02.port=2025"
OPTION="${OPTION} -Dtain.server.deploy.file.name=${DEPLOY_HOME}/server/SASEMARTCMS-1.0.0-YYYYMMDDHHMMSS.war"
OPTION="${OPTION} -Dtain.server.app01.exec.cmd=${DEPLOY_HOME}/bin/TR1501.sh"
OPTION="${OPTION} -Dtain.server.app01.exec.log=${DEPLOY_HOME}/log/TR1501-YYYYMMDDHHMMSS.log"
OPTION="${OPTION} -Dtain.server.app02.exec.cmd=${DEPLOY_HOME}/bin/TR2501.sh"
OPTION="${OPTION} -Dtain.server.app02.exec.log=${DEPLOY_HOME}/log/TR2501-YYYYMMDDHHMMSS.log"

#------------------------------------------------------

# nohup ${JAVA_EXE} -cp ${CLASSPATH} ${OPTION} ${MAIN_CLASS} >> depsvr.log    2>&1 &

${JAVA_EXE} -cp ${CLASSPATH} ${OPTION} ${MAIN_CLASS}

#------------------------------------------------------

