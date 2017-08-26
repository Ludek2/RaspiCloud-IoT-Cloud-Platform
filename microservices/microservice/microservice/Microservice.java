package microservice;


public class Microservice {
	
	public static void main(String[] args) {
		Event_handler eh = new Event_handler(new MongoDbClient());
		new Kafka_consumer( eh);
	}
}
