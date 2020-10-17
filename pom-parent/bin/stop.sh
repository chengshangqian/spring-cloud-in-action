#!/bin/bash
#author:chengshangqian@foxmail.com
#filename:stop.sh
APP_NAME=$1

if [ ! ${APP_NAME} ]; then
  echo "need a arg to find the APP to stop ..."
  exit 0
fi


APP_PID=`jps -l | grep ${APP_NAME}*.jar | awk '{print $1}'`

echo "app id: ${APP_PID}"

if [ ! ${APP_PID} ]; then
  echo "${APP_NAME} not found or not running..."
  exit 0
fi

echo "stoping: ${APP_NAME}"

kill -9 ${APP_PID}

echo "${APP_NAME} stopped..."
