package pushService;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class httpHandler {

	HttpClient client = new HttpClient();

	public String getMessages() {
		GetMethod get = new GetMethod("http://localhost:4567/");
		// Provide custom retry handler is necessary
		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			return new String(responseBody);
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
		return null;
	}
}
