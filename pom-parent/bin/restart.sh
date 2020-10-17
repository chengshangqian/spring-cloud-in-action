#!/bin/bash
#author:chengshangqian@foxmail.com
#filename:restart.sh
APP_NAME=$1

if [ ! ${APP_NAME} ]; then
  echo "need a arg to find the APP to restart ..."
  exit 0
fi

echo "restarting ${APP_NAME}"

source ./stop.sh ${APP_NAME}
sleep 2s

APP_ARGS=$2
echo "restart arg: ${APP_ARGS}"

source ./start.sh ${APP_NAME} ${APP_ARGS}

echo "${APP_NAME} restarted."
exit 0
