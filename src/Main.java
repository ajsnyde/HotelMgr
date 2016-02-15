import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import database.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class Main {
	boolean toggle = true;
	private JFrame frmMainWindow;
	private JTextField startDateField;
	private JTextField endDateField;
	private LocalDate endDate;
	private LocalDate startDate;
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Database database = new Database();
				User user = new User("Addison", "Snyder", "123 Anywhere Blvd.");
				
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

	public Main() {
		initialize();
	}

	private void initialize() {
		frmMainWindow = new JFrame();
		frmMainWindow.setTitle("Main Window");
		frmMainWindow.setBounds(100, 100, 322, 316);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JPanel startPanel = new JPanel();
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
				SelectDate startDateDialog = new SelectDate("Checkin Date Selection");
				startDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						//this line converts Date.util to LocalDate; no time needed
						startDate = startDateDialog.datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						startDateField.setText(dateFormat.format(startDate));
						startDateDialog.killSelf();
					}
				});
			}
		});

		final JPanel endPanel = new JPanel();
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
				SelectDate endDateDialog = new SelectDate("Checkout Date Selection");
				endDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						endDate = endDateDialog.datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						endDateField.setText(dateFormat.format(endDate));
						endDateDialog.killSelf();
					}
				});
			}
		});
		
		JButton btnKillGuiComponents = new JButton("Next..");
		btnKillGuiComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					
				startPanel.setVisible(toggle);
				if(!toggle)
					frmMainWindow.getContentPane().remove(endPanel);
				else
					frmMainWindow.getContentPane().add(endPanel);
				frmMainWindow.getContentPane().repaint();
				toggle = !toggle;
			}
		});
		frmMainWindow.getContentPane().add(btnKillGuiComponents);
	}
}