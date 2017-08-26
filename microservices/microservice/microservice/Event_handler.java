package microservice;

public class Event_handler {
	
	MongoDbClient mongo;
	
	public Event_handler(MongoDbClient mongo) {
		this.mongo = mongo;
	}
	
	public void msg_received(Object msg) {
		//kafka_prod.send("test",msg);
		mongo.insert(msg);
	}
}
