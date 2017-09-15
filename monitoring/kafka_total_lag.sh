#!/bin/bash

while true
do
  kafka_metrics=$(curl 172.31.11.149:8000/v2/kafka/iot_cluster/consumer/my-group/lag)
  echo $kafka_metrics
  totalLag=$(echo "$kafka_metrics" | jq '.status.totallag' --raw-output)
  echo "totalLag is: ${totalLag}"

  send_to_aws="aws cloudwatch put-metric-data --metric-name totalLag --namespace Kafka-Cluster --value ${totalLag}"

  echo "executing: ${send_to_aws}"
  eval $send_to_aws
  sleep 10
done
