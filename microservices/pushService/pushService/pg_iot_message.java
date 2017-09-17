package pushService;

public class pg_iot_message {

	private String id;

	private long sent_from_microservice_time;

	private long sent_from_iot_device_time;

	private long db_received_message_time;
	

	public pg_iot_message(String id, long sent_from_microservice_time, long sent_from_iot_device_time,long db_received_message_time) {
		this.id = id;
		this.sent_from_microservice_time = sent_from_microservice_time;
		this.sent_from_iot_device_time = sent_from_iot_device_time;
		this.db_received_message_time = db_received_message_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSent_from_microservice_time() {
		return sent_from_microservice_time;
	}

	public void setSent_from_microservice_time(long sent_from_microservice_time) {
		this.sent_from_microservice_time = sent_from_microservice_time;
	}

	public long getSent_from_iot_device_time() {
		return sent_from_iot_device_time;
	}

	public void setSent_from_iot_device_time(long sent_from_iot_device_time) {
		this.sent_from_iot_device_time = sent_from_iot_device_time;
	}

	public long getDb_received_message_time() {
		return db_received_message_time;
	}

	public void setDb_received_message_time(long db_received_message_time) {
		this.db_received_message_time = db_received_message_time;
	}
	
}
