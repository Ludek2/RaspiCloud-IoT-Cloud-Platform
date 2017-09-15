package microservice;

import java.sql.Date;
import java.util.Iterator;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbClient {
	MongoClient mongoClient;
	MongoDatabase db;
	MongoCollection<Document> collection;
	String collectionName;
	
	public MongoDbClient(String collectionName) {
       this.collectionName=collectionName;
	   mongoClient = new MongoClient( "35.177.228.82" , 27017 );
	   db = mongoClient.getDatabase( "iot_test_db" );
		
	   mongoClient.getReplicaSetStatus();
	   //db.createCollection("test_collection");
	   collection = db.getCollection(collectionName);
	   
	}	
	public void insert( String msg ) {
		try {
		  JSONObject jsonObj = new JSONObject(msg);
		  Document doc = new Document();
		  for(Iterator iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {
		    String key = (String) iterator.next();
		    String val = jsonObj.get(key).toString();
		    doc.append(key, val);
		    
		  }
		  Date now = new Date(System.currentTimeMillis());
		  BasicDBObject timeNow = new BasicDBObject("time", now);
		  doc.append("created_by_microservice", timeNow);
			  
		  String time_now=Long.toString(System.currentTimeMillis());
		  doc.append("sent_from_microservice_time", time_now);
		  //doc.append("$currentDate", new BasicDBObject("saved_to_db_time",true));

		  collection.insertOne(doc);
	      System.out.println("value saved to db");
		}catch(org.json.JSONException ex) {
			System.out.println(ex);
		}
	}
}
