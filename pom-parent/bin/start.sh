#!/bin/bash
#author:chengshangqian@foxmail.com
#filename:start.sh
APP_NAME=$1

if [ ! ${APP_NAME} ]; then
 echo "app name required..."
 exit 0
fi

APP_ARGS=$2

IP_ADDRESS=`ifconfig eth0 | grep 'inet ' | awk '{print $2}'`
APP_JAR_NAME=`ls -t | grep ${APP_NAME}*.jar`

if [ ! ${APP_JAR_NAME} ]; then
 echo "${APP_NAME} not found..."
 exit 0
fi

echo "starting: ${APP_JAR_NAME}"
echo "app args: ${APP_ARGS}"
echo "binding ip: ${IP_ADDRESS}"

nohup java -jar ${APP_JAR_NAME} --server.address=${IP_ADDRESS} ${APP_ARGS} >logs/${APP_NAME}.log 2>&1 &

echo "${APP_NAME} started..."
