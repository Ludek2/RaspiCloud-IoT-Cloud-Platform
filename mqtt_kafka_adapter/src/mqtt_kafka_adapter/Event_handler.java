package mqtt_kafka_adapter;

public class Event_handler {
	
	kafka_producer kafka_prod;
	
	public Event_handler(kafka_producer kafka_prod) {
		this.kafka_prod = kafka_prod;
	}
	
	public void mqtt_msg_received(String msg) {
		kafka_prod.send("test",msg);
	}
}
