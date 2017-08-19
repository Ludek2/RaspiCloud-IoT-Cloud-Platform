package mqtt_kafka_adapter;

public class main {

	public static void main(String[] args) {
		
		kafka_producer kp = new kafka_producer();
		Event_handler event_handl= new Event_handler(kp);
		
		Mqtt_client mqttCl = new Mqtt_client(event_handl);
		mqttCl.start_consumer(); 
		mqttCl.publish("here we go");
		
		
	/*
	    String topic        = "MQTT Examples";
	    String content      = "Message from MqttPublishSample";
	    int qos             = 2;
	    String broker       = "tcp://35.177.223.189:1883";
	    String clientId     = "JavaSample";
	    MemoryPersistence persistence = new MemoryPersistence();
	
	    try {
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        System.out.println("Connecting to broker: "+broker);
	        sampleClient.connect(connOpts);
	        System.out.println("Connected");
	        System.out.println("Publishing message: "+content);
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        //sampleClient.publish(topic, message);    
	        System.out.println("Message published");
	        sampleClient.disconnect();
	        System.out.println("Disconnected");
	        System.exit(0);
	    } catch(MqttException me) {
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        me.printStackTrace();
	    }*/
	}
}