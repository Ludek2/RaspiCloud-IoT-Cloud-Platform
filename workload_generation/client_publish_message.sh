#!/bin/bash
echo "client executed with id $1"
mosquitto_pub -d -h 172.31.15.244 -t "topic/test" -m "$1" -i "thing001" -q 1
