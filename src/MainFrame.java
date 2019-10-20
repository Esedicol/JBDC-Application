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
	}
}