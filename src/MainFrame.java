import java.awt.EventQueue;

import javax.swing.JFrame;
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
			}
		}
		btnAdd.setBounds(30, 210, 100, 50);
		frame.getContentPane().add(btnAdd);


		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}
		btnUpdate.setBounds(140, 210, 100, 50);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}
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
}
