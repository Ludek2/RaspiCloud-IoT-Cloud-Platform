FROM cburki/mosquitto-clients
RUN /bin/bash -c "mkdir /opt/mqtt_client_scripts"
COPY client_publish_message.sh   /opt/mqtt_client_scripts
COPY run_clients.sh /opt/mqtt_client_scripts
RUN /bin/bash -c ".//opt/mqtt_client_scripts/run_clients.sh /opt/mqtt_client_scripts/client_publish_message.sh 10"
