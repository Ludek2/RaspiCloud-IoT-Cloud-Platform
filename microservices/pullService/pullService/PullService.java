package pullService;

import static spark.Spark.*;

public class PullService {

	public static void main(String[] args) {
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
