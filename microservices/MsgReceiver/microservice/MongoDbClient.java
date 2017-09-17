package microservice;

import java.sql.Date;
import java.util.Iterator;
import java.util.UUID;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

public class MongoDbClient {
	MongoClient mongoClient;
	MongoDatabase db;
	MongoCollection<Document> collection;
	String collectionName;

	public MongoDbClient(String collectionName) {
		this.collectionName = collectionName;
		mongoClient = new MongoClient("35.177.228.82", 27017);
		db = mongoClient.getDatabase("iot_test_db");

		mongoClient.getReplicaSetStatus();
		// db.createCollection("test_collection");
		collection = db.getCollection(collectionName);

	}

	public void insert(String msg) {
		try {
			JSONObject jsonObj = new JSONObject(msg);
			Document doc = new Document();
			for (Iterator iterator = jsonObj.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String val = jsonObj.get(key).toString();
				doc.append(key, val);
			}
			String time_now = Long.toString(System.currentTimeMillis());
			doc.append("sent_from_microservice_time", time_now);
			//generate unique id
			UUID uuid = UUID.randomUUID();
			//using upsert - this id will be created if not exist
			//threfore effectively setting uuid as id of the message in db 
			Bson filter = Filters.eq("_id", uuid.toString());
			//set upsert mode
			UpdateOptions options = new UpdateOptions().upsert(true);
			//update to be executed, it will replace $currentDate with the current mongo db server time
			Bson update = new Document("$set", doc).append("$currentDate",
					new BasicDBObject("received_by_db_time", true));
			//update db
			collection.updateOne(filter, update, options);
			System.out.println("saved to db");
		} catch (org.json.JSONException ex) {
			System.out.println(ex);
		}
	}
}
