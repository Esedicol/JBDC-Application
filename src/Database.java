import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {


	public Database() {
	}

	// *********** Connection Method *********** //
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "esedicol");
		connectionProps.put("password", "krlafe8IM");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/Employee",connectionProps);
		return conn;
	}


	// *********** Execute an SQL Query ***********  //
	public void execute(String query) throws SQLException {
		Statement st = null;
		try {
			Connection conn = getConnection();

			st = conn.createStatement();
			st.executeUpdate(query); 

		} finally {
			if (st != null) {
				st.close(); 
			}
		}
	}
}
