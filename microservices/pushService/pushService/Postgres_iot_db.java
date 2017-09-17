package pushService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Postgres_iot_db {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String url = "jdbc:postgresql://35.177.24.238/iot-platform";
	String user = "chartio";
	String password = "ZTkT3jrNJ!I3XeUa";

	public Postgres_iot_db() {
		con = null;
		st = null;
		rs = null;
		url = "jdbc:postgresql://35.177.24.238/iot-platform";
		user = "chartio";
		password = "ZTkT3jrNJ!I3XeUa";

		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");
			if (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int saveMessage(String id, long sent_from_microservice_time, long sent_from_iot_device_time, long db_received_message_time) {
		String stm = "INSERT INTO iot_messages(id, sent_from_microservice_time, sent_from_iot_device_time, db_received_message_time) VALUES(?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, id);
			pst.setLong(2, sent_from_microservice_time);
			pst.setLong(3, sent_from_iot_device_time);
			pst.setLong(4, db_received_message_time);
			pst.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
