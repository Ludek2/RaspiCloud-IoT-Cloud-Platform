package pushService;

import java.awt.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Iot_messages {
	JSONObject msgsJson;
	Set<pg_iot_message> msgs;

	public Iot_messages(String msgsJson) {
		JSONParser parser = new JSONParser();
		try {
			this.msgsJson = (JSONObject) parser.parse(msgsJson);
			System.out.println("msgsJson in Iot_messages:" + this.msgsJson);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<pg_iot_message> getMsgsSet() {
		msgs = new HashSet<pg_iot_message>();
		JSONArray jsonArray = (JSONArray) msgsJson.get("messages");
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject msg = iterator.next();

			String id = msg.get("id").toString();
			long sent_from_iot_device_time = Long.parseLong(msg.get("sent_from_iot_device_time").toString());
			long sent_from_microservice_time = Long.parseLong(msg.get("sent_from_microservice_time").toString());
			msgs.add(new pg_iot_message(id, sent_from_microservice_time, sent_from_iot_device_time));
		}
		// System.out.println(jsonArray);
		return msgs;
	}
	
	
}
