package mqtt_kafka_adapter;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class Mqtt_client implements MqttCallback{

		MqttClient client;

		public Mqtt_client() {
			try {
				client = new MqttClient("tcp://35.177.223.189:1883", "Sending");
				client.connect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void start_consumer() {
		    try {	  
		        client.setCallback(this);
		        client.subscribe("topic/test");
		    } catch (MqttException e) {
		        e.printStackTrace();
		    }
		}

		public void publish(String msg) {
	        try {
	        		MqttMessage message = new MqttMessage();
		        message.setPayload(msg.getBytes());
	        	    client.publish("topic/test", message);
			} catch (MqttPersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void connectionLost(Throwable cause) {
		    // TODO Auto-generated method stub

		}

		@Override
		public void messageArrived(String topic, MqttMessage message)
		        throws Exception {
		 System.out.println(message);   
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken token) {
		    // TODO Auto-generated method stub

		}

}

