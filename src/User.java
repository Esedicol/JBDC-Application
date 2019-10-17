
public class User {
	
	private int ID, SSN;
	private String fName, lName, email;
	
	public User(int id, int ssn, String firstName, String lastName, String email) {
		this.ID = id;
		this.SSN = ssn;
		this.fName = firstName;
		this.lName = lastName;
		this.email = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
