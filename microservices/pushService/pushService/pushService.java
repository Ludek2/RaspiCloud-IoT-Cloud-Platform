package pushService;

import static spark.Spark.*;

import java.util.Set;

import org.json.simple.JSONObject;

public class pushService {

	public static void main(String[] args) {
		httpHandler http = new httpHandler();
		port(4568);
		Postgres_iot_db db = new Postgres_iot_db();
		
		// curl localhost:4568/savetodb
		get("/savetodb", (request, response) -> {
			//get messages from push microservice
			String messagesJson = http.getMessages();
			System.out.println("returned from http call: "+messagesJson);
			
			//get set of pg_iot_message instances
			Set<pg_iot_message> msgsSet = new Iot_messages(messagesJson).getMsgsSet();;			
			for(pg_iot_message msg : msgsSet){
				System.out.println(msg.getId());
				db.saveMessage(msg.getId(), msg.getSent_from_microservice_time(), msg.getSent_from_iot_device_time());
			}
			
			//response to http get call
			JSONObject obj = new JSONObject();
			obj.put("status","Messages successfully saved to postgres db");
			response.status(201); //201 created
			return obj.toString();
	    });
	}
}
