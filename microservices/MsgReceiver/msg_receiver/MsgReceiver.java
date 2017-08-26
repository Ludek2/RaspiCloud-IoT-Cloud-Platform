package msg_receiver;

import microservice.Event_handler;
import microservice.Kafka_consumer;
import microservice.MongoDbClient;

public class MsgReceiver {
	public static void main(String[] args) {
		Event_handler eh = new Event_handler(new MongoDbClient("test_collection"));
		new Kafka_consumer("test", eh);
	}
}
