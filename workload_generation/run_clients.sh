#! /bin/bash
# run this script as ./run_clients [string path to client] [int number of executions]
echo "run_clients script executed"
client_exec_file_path=$1
number_of_executions=$2

if [ $number_of_executions -ne "-1" ]
then
  for i in `seq 1 $number_of_executions`;
      do
          echo "execution number $i"
          $client_exec_file_path $i
      done
else  
  COUNTER=0
  while [ 1 ]; do
    $client_exec_file_path $COUNTER
    let COUNTER=COUNTER+1 
    sleep 0.2
  done
fi
