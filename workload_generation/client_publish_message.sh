#!/bin/bash
echo "client executed with id $1"
mosquitto_pub -d -h 172.31.7.159 -t "topic/test" -m "msg number $1" -i "thing001" -q 1


