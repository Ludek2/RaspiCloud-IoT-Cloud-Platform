package microservice;


public class Microservice {
	
	public static void main(String[] args) {
		Event_handler eh = new Event_handler(new MongoDbClient("test_collection"));
		new Kafka_consumer("test", eh);
	}
}
