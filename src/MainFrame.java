import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JPanel{

	public JFrame frame;
	private JTextField txtSsn;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtId;

	Database db = new Database();


	// Launch the application //
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();

					window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		ArrayList<User> employees = db.getUsersList();
	}

	// Initialize the contents of the frame //
	public void initialize() {

		frame =  new JFrame("MySQL CRUD");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		// ***************** Labels ***************** //
		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setBounds(30, 40, 80, 40);
		frame.getContentPane().add(lblSsn);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(30, 80, 80, 40);
		frame.getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(30, 120, 80, 40);
		frame.getContentPane().add(lblLastName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(30, 160, 80, 40);
		frame.getContentPane().add(lblEmail);


		// ***************** Buttons ***************** //

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = size + 1;
				String ssn = txtSsn.getText().strip();
				String fname = txtFirstName.getText().strip();
				String lname = txtLastName.getText().strip();
				String email = txtEmail.getText().strip();


				if(ssn.equals("") || fname.equals("") || lname.equals("") || email.equals("")) {
					JOptionPane.showMessageDialog(null,"Empty Inputs");
					db.refresh();
				}

				else if (db.checkSSN(Integer.parseInt(ssn)) == true || db.checkEmail(email) == true) {
					JOptionPane.showMessageDialog(null,"Dup Inputs");
					db.refresh();
				}

				else {
					db.add(id, Integer.parseInt(ssn), fname, lname, email);
				}
			}
		});
		btnAdd.setBounds(30, 210, 100, 50);
		frame.getContentPane().add(btnAdd);


		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ssn = txtSsn.getText().strip();
				String fname = txtFirstName.getText().strip();
				String lname = txtLastName.getText().strip();
				String email = txtEmail.getText().strip();

				if(ssn.equals("") || fname.equals("") || lname.equals("") || email.equals("")) {
					JOptionPane.showMessageDialog(null,"Empty Inputs");
					db.refresh();
				}

				else if (db.checkSSN(Integer.parseInt(ssn)) == true || db.checkEmail(email) == true) {
					JOptionPane.showMessageDialog(null,"SSN or Email already exist.");
					db.refresh();
				}
				else {
					db.update(Integer.parseInt(ssn), fname, lname, email);
					db.refresh();
				}
			}
		});
		btnUpdate.setBounds(140, 210, 100, 50);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ssn = txtSsn.getText().strip();
				String fname = txtFirstName.getText().strip();
				String lname = txtLastName.getText().strip();
				String email = txtEmail.getText().strip();

				if(ssn.equals("") || fname.equals("") || lname.equals("") || email.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty Inputs" );
					db.refresh();
				}
				else {
					if (db.checkSSN(Integer.parseInt(ssn)) == false || db.checkFName(fname) == false || db.checkLName(lname) == false || db.checkEmail(email) == false) {
						JOptionPane.showMessageDialog(null, "ERROR User not found" );
						db.refresh();
					}
					else {
						String query = "DELETE FROM `employee` WHERE ssn=" + ssn;
						try {
							db.execute(query);
							JOptionPane.showMessageDialog(null, "Employee: " + txtFirstName.getText().trim() + " " + txtLastName.getText().trim() + " has been deleted Successfully ");
							db.refresh();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDelete.setBounds(250, 210, 100, 50);
		frame.getContentPane().add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}
		btnClear.setBounds(360, 210, 100, 50);
		frame.getContentPane().add(btnClear);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputID = txtId.getText().strip();

				if(!inputID.equals("")) {
					if(db.checkID(Integer.parseInt(inputID)) == false || db.isNumeric(inputID) == false) {
						JOptionPane.showMessageDialog(null, "ERROR ID not found" );
						db.refresh();
					}

					else if (Integer.parseInt(inputID) > size + 1) {
						System.out.println("fail");
						JOptionPane.showMessageDialog(null, "ID Out of Bounds, please enter numbers below: " + size );
						db.refresh();
					}

					else {
						User user = db.search(Integer.parseInt(inputID));
						txtSsn.setText(Integer.toString(user.getSSN()));
						txtFirstName.setText(user.getfName());
						txtLastName.setText(user.getlName());
						txtEmail.setText(user.getEmail());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter an ID" );
				}
			}
		});
		btnSearch.setBounds(255, 166, 160, 30);
		frame.getContentPane().add(btnSearch);
	}


			// ***************** Text Fields ***************** //
	txtSsn = new JTextField();
	txtSsn.setBounds(120, 40, 130, 40);
	frame.getContentPane().add(txtSsn);
	txtSsn.setColumns(10);

	txtFirstName = new JTextField();
	txtFirstName.setBounds(120, 80, 130, 40);
	frame.getContentPane().add(txtFirstName);
	txtFirstName.setColumns(10);

	txtLastName = new JTextField();
	txtLastName.setBounds(120, 120, 130, 40);
	frame.getContentPane().add(txtLastName);
	txtLastName.setColumns(10);

	txtEmail = new JTextField();
	txtEmail.setBounds(120, 160, 130, 40);
	frame.getContentPane().add(txtEmail);
	txtEmail.setColumns(10);

	txtId = new JTextField();
	txtId.setBounds(423, 170, 57, 20);
	frame.getContentPane().add(txtId);
}
