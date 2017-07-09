#! /bin/bash
# run this script as ./run_clients [string path to client] [int number of executions]
echo "run_clients script executed"
client_exec_file_path=$1
number_of_executions=$2

for i in `seq 1 $number_of_executions`;
    do
        echo "execution number $i"
        $client_exec_file_path $i
    done

