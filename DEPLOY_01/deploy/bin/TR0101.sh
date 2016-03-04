#!/bin/sh

#--------------------------------------------------------------------------------------------
# set environment

export JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.45.x86_64
export M2_HOME=/sas/sasuser/sas/.jenkins/apache-maven-3.3.3
export SVN_HOME=/usr/bin
export SVN_EDITOR=
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$SVN_HOME:$PATH

export JOB_HOME=/sas/sasuser/sas/deploy/client

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

/bin/rm -f  $JOB_HOME/SASEMARTCMS-1.0.0.war

/bin/rm -rf $JOB_HOME/SASEMARTCMS
 
svn export svn://matcmsmine01/repo-tasks/SASEMARTCMS $JOB_HOME/SASEMARTCMS --username fic01524 --password Kang123!

/bin/echo "-------------- svn export OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# pom.xml

export LIB_HOME=$JOB_HOME/SASEMARTCMS/src/main/webapp/WEB-INF/lib

mvn install:install-file -DgroupId=kang.tain -DartifactId=ibatis                 -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/ibatis-2.3.4.726b.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=java-json              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/java-json.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=log4j                  -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/log4j.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=modules.tain           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/modules.tain.0.151031.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core               -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.core.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.core.nls           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.core.nls.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.rutil              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.rutil.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.security.sspi      -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.security.sspi.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet            -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.servlet.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.servlet.nls        -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.servlet.nls.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection     -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.svc.connection.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sas.svc.connection.nls -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sas.svc.connection.nls.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=sastpj.rutil           -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/sastpj.rutil.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=scsl                   -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/scsl.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=tdgssconfig            -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/tdgssconfig.jar
mvn install:install-file -DgroupId=kang.tain -DartifactId=terajdbc4              -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB_HOME/terajdbc4.jar

cp $JOB_HOME/pom.xml $JOB_HOME/SASEMARTCMS

/bin/echo "-------------- pom.xml OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# clean build

mvn -file $JOB_HOME/SASEMARTCMS  clean install

/bin/echo "-------------- clean build OK !!  -------------------"

#--------------------------------------------------------------------------------------------
# finish

mv $JOB_HOME/SASEMARTCMS/target/SASEMARTCMS-1.0.0.war $JOB_HOME

/bin/echo "-------------- FINISH  -------------------"







