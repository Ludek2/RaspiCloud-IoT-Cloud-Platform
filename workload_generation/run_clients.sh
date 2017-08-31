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

    while IFS=, read col1 col2 col3 col4 col5 col6 col7 col8 col9 col10 col11
    do
        $client_exec_file_path '{"stationId": "station1","date": "'$col1'","time": "'$col2'","maximum_wind_speed": "'$col3'","minimum_wind_speed": "'$col4'","mean_wind_speed": "'$col5'","wind_direction": "'$col6'","battery_voltage": "'$col7'","relative_humidity": "'$col8'","air_temperature": "'$col9'","net_solar_radiation": "'$col10'","rain_fall": "'$col11'"}'
       sleep 0.05
    done < dataSet.csv

  done
fi
