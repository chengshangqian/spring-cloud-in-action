#!/bin/bash
#author:chengshangqian@foxmail.com
#filename:ip.sh
current_ip=`ifconfig eth0 | grep -oP "inet \K([0-9]{1,3}[.]){3}[0-9]{1,3}"`
echo ${current_ip}
