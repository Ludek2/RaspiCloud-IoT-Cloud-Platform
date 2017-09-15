package pullService;
import static spark.Spark.*;


public class PullService {

	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
		get("/", (request, response) -> {
		    // Show something
			return "running ok";
		});
	}

}
