package microservice;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbClient {
	MongoClient mongoClient;
	MongoDatabase db;
	MongoCollection<Document> collection;
	
	public MongoDbClient() {
       mongoClient = new MongoClient( "35.177.228.82" , 27017 );
	   db = mongoClient.getDatabase( "iot_test_db" );
		
	   mongoClient.getReplicaSetStatus();
	   //db.createCollection("test_collection");
	   collection = db.getCollection("test_collection");
	   
	}
	
	public void insert( Object msg ) {
		Document doc = new Document("receivedValue", msg);
	    collection.insertOne(doc);
	    System.out.println("value saved to db");
	}
}
