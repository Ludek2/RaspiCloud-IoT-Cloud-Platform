package pullService;

import org.json.simple.JSONObject;

public class mongo_iot_message {
	String id;
	Long sent_from_microservice_time;
	Long sent_from_iot_device_time;
	Long dbReceivedMessageTime;
	
	public mongo_iot_message(String id, Long sent_from_microservice_time,
			Long sent_from_iot_device_time, Long dbReceivedMessageTime) {
		this.id = id;
		this.sent_from_microservice_time = sent_from_microservice_time;
		this.sent_from_iot_device_time = sent_from_iot_device_time;
		this.dbReceivedMessageTime = dbReceivedMessageTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSent_from_microservice_time() {
		return sent_from_microservice_time;
	}

	public void setSent_from_microservice_time(Long sent_from_microservice_time) {
		this.sent_from_microservice_time = sent_from_microservice_time;
	}

	public Long getSent_from_iot_device_time() {
		return sent_from_iot_device_time;
	}

	public void setSent_from_iot_device_time(Long sent_from_iot_device_time) {
		this.sent_from_iot_device_time = sent_from_iot_device_time;
	}
	
	public Long getDbReceivedMessageTime() {
		return this.dbReceivedMessageTime;
	}
	
	public void setDbReceivedMessageTime(Long dbReceivedMessageTime) {
		this.dbReceivedMessageTime = dbReceivedMessageTime;;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id",id);
		obj.put("sent_from_microservice_time",sent_from_microservice_time);
		obj.put("sent_from_iot_device_time",sent_from_iot_device_time);
		obj.put("db_received_message_time", dbReceivedMessageTime);
		return obj;
	}
}
