package pullService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Mongo_iot_db {
	MongoClient mongoClient;
	MongoDatabase db;
	MongoCollection<Document> collection;

	public Mongo_iot_db() {
		mongoClient = new MongoClient("35.177.228.82", 27017);
		db = mongoClient.getDatabase("iot_test_db");
		collection = db.getCollection("test_collection");
	}

	public Set<mongo_iot_message> getAllIotMessages() {
		Set<mongo_iot_message> msgs = new HashSet<mongo_iot_message>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String id = doc.getObjectId("_id").toString();
			//System.out.println(id);
			Long sent_from_microservice_time = Long.parseLong(doc.getString("sent_from_microservice_time"));
			Long sent_from_iot_device_time = Long.parseLong(doc.getString("sent_from_iot_device_time"));
			msgs.add(new mongo_iot_message(id, sent_from_microservice_time, sent_from_iot_device_time));
		}
	    getJsonFromMsgSet(msgs);
		return msgs;
	}

	public String getJsonFromMsgSet(Set<mongo_iot_message> msgs) {
		JSONObject messagesObj = new JSONObject();
		JSONArray msgsList = new JSONArray();
		for(mongo_iot_message msg : msgs){
			msgsList.add(msg.toJson());
		}
		//json += jsonList.toString() + "}";
		messagesObj.put("messages", msgsList);
		return messagesObj.toString();
	}
}
