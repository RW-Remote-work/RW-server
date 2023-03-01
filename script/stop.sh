#!/bin/bash
APP_NAME=rwws-admin.jar
PID=`ps -ef |grep java|grep $APP_NAME|grep -v grep|awk '{print $2}'`

if [ -z "${PID}" ]; then
   echo "$APP_NAME is not running"
else
    echo "kill thread...$PID"
    kill -9 $PID
fi