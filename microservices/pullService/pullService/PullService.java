package pullService;

import static spark.Spark.*;

import java.util.Set;

public class PullService {

	public static void main(String[] args) {
		Mongo_iot_db mongo = new Mongo_iot_db();
		mongo.getAllIotMessages();
		get("/hello", (req, res) -> "Hello World");

		get("/", (request, response) -> {
			// curl localhost:4567/?date1=103030303
			String date1 = request.queryParams("date1");
			String date2 = request.queryParams("date2");
			Mongo_iot_db mongoIot = new Mongo_iot_db();
			return mongoIot.getJsonFromMsgSet(mongoIot.getAllIotMessages());
		});
	}
}
