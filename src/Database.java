import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Database {
	ArrayList<User> employees = getUsersList();

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


	// ************* SQL INSERT ************** //
	public void add(int id, int ssn, String fname, String lname, String email) {
		try{
			String query = "INSERT INTO `employee`(`id`, `ssn`, `fname`, `lname`, `email`) VALUES (" + "'" +id+"'" + "," + "'"+ssn+"'" + "," + "'"+fname+"'" + "," + "'"+lname+"'" + "," + "'"+email+"'" + ");";
			execute(query);
			JOptionPane.showMessageDialog(null, fname + " " + lname + " Succesfully Added");
//			refresh();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	// ************* SQL UPDATE ************** //
	public void update(int ssn, String fname, String lname, String email) {
		try{
			String query = "UPDATE `employee` SET `ssn`= '" + ssn + "', `fname`= '" + fname + "', `lname`= '" + lname + "', `email`= '" + email + "' WHERE fname = '" + fname + "';";
			execute(query);
			JOptionPane.showMessageDialog(null, fname + " " + lname + " Succesfully Updated");
//			refresh();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}


	// *********** List user in the DB by its ID *********** //
	public User search (int id) {
		User employee = null;

		try {
			Connection conn = getConnection();

			// MySQL Query //
			String query = "SELECT * FROM employee WHERE id = '" + id + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				employee = new User(rs.getInt("id"), rs.getInt("ssn"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "SQL Query Failed :)");
		}	
		return employee;
	}

	// ************ Return a list of employees from database ************* //
	public ArrayList<User> getUsersList()
	{
		ArrayList<User> usersList = new ArrayList<User>();

		try {
			Connection connection = getConnection();
			Statement st;
			ResultSet rs;
			User user;

			String query = "SELECT * FROM  `Employee` ";

			st = connection.createStatement();
			rs = st.executeQuery(query);

			while(rs.next())
			{
				user = new User(rs.getInt("id"),rs.getInt("ssn"),  rs.getString("fname"),rs.getString("lname"),rs.getString("email"));
				usersList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}
}