package hotelSim;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountCreator extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtUsername;
	private JTextField txtLastName;
	private JPasswordField txtPassword;
	// standard email regex I grabbed off the internet
	final Pattern EMAIL = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	final Pattern PHONE = Pattern
			.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"); // gotta love escape sequences..
	final short MIN_PASSWORD_SIZE = 5;
	
	public AccountCreator(DatabaseWrapper db) {
		setMinimumSize(new Dimension(375, 179));
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setTitle("Account Creation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 55, 0, 0, 72, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setMinimumSize(new Dimension(55, 20));
		txtUsername.setColumns(10);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 1;
		contentPane.add(txtUsername, gbc_txtUsername);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 4;
		gbc_lblEmail.gridy = 1;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setMinimumSize(new Dimension(40, 20));
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 5;
		gbc_txtEmail.gridy = 1;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setMinimumSize(new Dimension(55, 20));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 2;
		contentPane.add(txtPassword, gbc_txtPassword);
		
		JLabel lblPhone = new JLabel("Phone:");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 4;
		gbc_lblPhone.gridy = 2;
		contentPane.add(lblPhone, gbc_lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setMinimumSize(new Dimension(40, 20));
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhoneNum = new GridBagConstraints();
		gbc_txtPhoneNum.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhoneNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhoneNum.gridx = 5;
		gbc_txtPhoneNum.gridy = 2;
		contentPane.add(txtPhone, gbc_txtPhoneNum);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 3;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setMinimumSize(new Dimension(55, 20));
		txtFirstName.setColumns(10);
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 2;
		gbc_txtFirstName.gridy = 3;
		contentPane.add(txtFirstName, gbc_txtFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 4;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setMinimumSize(new Dimension(55, 20));
		txtLastName.setColumns(10);
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 4;
		contentPane.add(txtLastName, gbc_txtLastName);
		
		JLabel lblBalance = new JLabel("Balance (USD):");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lblBalance.gridx = 4;
		gbc_lblBalance.gridy = 4;
		contentPane.add(lblBalance, gbc_lblBalance);
		
		JSpinner spinnerBalance = new JSpinner();
		spinnerBalance.setMinimumSize(new Dimension(40, 20));
		spinnerBalance.setModel(new SpinnerNumberModel(100.0, 0.0, 9000.0, 100.0));
		JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinnerBalance.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(2);
        spinnerBalance.setValue(0.00);	
        // for the 0.00 to follow precision, I have to set it. To set it, the value must be different from
        // the existing value (likely for performance). 0 == 0.00, thus why I initialize with 100.0 and set to 0
		GridBagConstraints gbc_spinnerBalance = new GridBagConstraints();
		gbc_spinnerBalance.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerBalance.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerBalance.gridx = 5;
		gbc_spinnerBalance.gridy = 4;
		contentPane.add(spinnerBalance, gbc_spinnerBalance);
		
		JButton btnNewButton = new JButton("Create!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// has the responsibility to ensure sanitation of input
				if(txtUsername.getText().length() < 5 || !db.getQueryResults("SELECT [Username] FROM [Customers] "
						+ "WHERE [Username] = '" + txtUsername.getText() + "'").isEmpty()) { // username < 5 chars, or exists already
					lblSetError(lblUsername);
				}
				else lblSetNorm(lblUsername);
				
				// The following is a mess. However, to write a function that would do this
				// for each set would be more trouble than it was worth.
				
				if(txtFirstName.getText().isEmpty()) lblSetError(lblFirstName);
				else lblSetNorm(lblFirstName);
				
				if(txtLastName.getText().isEmpty()) lblSetError(lblLastName);
				else lblSetNorm(lblLastName);
				
				Matcher matcher = EMAIL.matcher(txtEmail.getText());
				if(!matcher.matches()) lblSetError(lblEmail);
				else lblSetNorm(lblEmail);
				
				if(txtPassword.getPassword().length <= MIN_PASSWORD_SIZE) lblSetError(lblPassword);
				else lblSetNorm(lblPassword);
				
				matcher = PHONE.matcher(txtPhone.getText());
				if(!matcher.matches()) lblSetError(lblPhone);
				else lblSetNorm(lblPhone);
				
				if(		checkLblNorm(lblUsername) 		// checks all boxes (through label color) for errors
						&& checkLblNorm(lblFirstName) 
						&& checkLblNorm(lblLastName) 
						&& checkLblNorm(lblPhone) 
						&& checkLblNorm(lblEmail)
						&& checkLblNorm(lblPassword)) {
					System.out.println("Creating account!");
					createAccount(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(),
							String.valueOf(txtPassword.getPassword()), txtPhone.getText(), txtEmail.getText(),
							Double.parseDouble(spinnerBalance.getValue().toString()));
				}
				else { // change "create account" box light red?
					
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 5;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		this.setVisible(true);
	}

	void lblSetError(JLabel in) {		// adds star if it doesn't have one, and sets font to red
		if (!in.getText().contains("*"))
			in.setText("*" + in.getText());
		in.setForeground(new Color(255, 0, 0));
	}
	
	void lblSetNorm(JLabel in) {		// removes star/s, sets font black
		in.setText(in.getText().replace("*", ""));
		in.setForeground(new Color(0, 0, 0));
	}	
	
	boolean checkLblNorm(JLabel in) {	// checks whether the label is red, which indicates trouble
		if(!in.getForeground().equals(new Color(255,0,0)))
			return true;
		else
			return false;
	}
	
	static boolean createAccount(String firstName, String lastName, String username, String password, String phoneNum,
			String email, double balance) {
		return false;
	}

}
