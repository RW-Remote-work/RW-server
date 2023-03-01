#!/bin/bash
APP_NAME=rwws-admin.jar
APP_HOME=/opt/rwws
JVM_OPTS="-Dname=$APP_NAME  -Duser.timezone=Asia/Shanghai -Xms256m -Xmx512m -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=256m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps  -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"

PID=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`

if [ x"$PID" != x"" ]; then
    echo "$APP_NAME is running, don't need to start..."
else
  nohup /usr/bin/java $JVM_OPTS -jar $APP_HOME/$APP_NAME > $APP_HOME/start.log 2>&1 &
  echo "Start $APP_NAME success..."
fi
