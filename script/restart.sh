#!/bin/bash
APP_NAME=rwws-admin.jar
APP_HOME=/opt/rwws
PID=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`
JVM_OPTS="-Dname=$APP_NAME  -Duser.timezone=Asia/Shanghai -Xms256m -Xmx512m -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=256m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps  -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"


if [ -z "${PID}" ]; then
   echo "$APP_NAME is not running"
else
    echo "kill thread...$PID"
    kill -9 $PID
fi

nohup java /usr/bin/$JVM_OPTS -jar $APP_HOME/$APP_NAME > $APP_HOME/start.log 2>&1 &
echo "Start $APP_NAME success..."