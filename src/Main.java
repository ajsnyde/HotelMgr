import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class Main {

	private JFrame frmMainWindow;
	private JTextField startDateField;
	private JTextField endDateField;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

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

				try {
					Main window = new Main();
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainWindow = new JFrame();
		frmMainWindow.setTitle("Main Window");
		frmMainWindow.setBounds(100, 100, 450, 300);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel startPanel = new JPanel();
		frmMainWindow.getContentPane().add(startPanel);

		JLabel lblStartDate = new JLabel("Start Date:");
		startPanel.add(lblStartDate);
		startDateField = new JTextField();
		startDateField.setEnabled(false);
		startDateField.setColumns(10);
		startPanel.add(startDateField);
		JButton btnSelectStartDate = new JButton("Select Date!");
		startPanel.add(btnSelectStartDate);
		btnSelectStartDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final SelectDate startDateDialog = new SelectDate();
				startDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startDateField.setText(dateFormat.format(startDateDialog.datePanel.getDate()));
						startDateDialog.killSelf();
					}
				});
			}
		});

		JPanel endPanel = new JPanel();
		frmMainWindow.getContentPane().add(endPanel);

		JLabel lblEndDate = new JLabel("End Date:");
		endPanel.add(lblEndDate);

		endDateField = new JTextField();
		endDateField.setEnabled(false);
		endDateField.setColumns(10);
		endPanel.add(endDateField);

		JButton btnSelectEndDate = new JButton("Select Date!");
		endPanel.add(btnSelectEndDate);
		btnSelectEndDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final SelectDate endDateDialog = new SelectDate();
				endDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						endDateField.setText(dateFormat.format(endDateDialog.datePanel.getDate()));
						endDateDialog.killSelf();
					}
				});
			}
		});
	}
}