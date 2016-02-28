package hotelSim;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Insets;
import javax.swing.JSplitPane;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class HotelMgrGUI {

	private JFrame frmHotelReservationGui;
	private LocalDate endDate = LocalDate.now();
	private LocalDate startDate = LocalDate.now();

	final DefaultListModel<String> roomTypeModel = new DefaultListModel<String>();
	final JList<String> roomTypeList = new JList<String>(roomTypeModel);
	final DefaultListModel<String> roomNumModel = new DefaultListModel<String>();
	final JList<String> roomNumList = new JList<String>(roomNumModel);
	final JTextPane descriptionTxt = new JTextPane();
	final JLabel lblRoomName = new JLabel("Placeholder Room Type");
	final JLabel lblPrice = new JLabel("Price per night:");
	final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
	ActionListener logout = null;
	ActionListener login = null;
	
	double pricePerNight = 0;
	long numDays = 0;
	
	DatabaseWrapper db = new DatabaseWrapper();
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelMgrGUI window = new HotelMgrGUI();
					window.frmHotelReservationGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HotelMgrGUI() {
		
		initialize();
		
		//db.executeQuery("UPDATE [Rooms] SET [PicSuffix] = 'singleStandard'");
		setList(roomTypeModel, "SELECT distinct [name], [price], [NonSmoke?] FROM [Rooms] ORDER BY [price], [NonSmoke?] DESC");
		roomTypeList.setToolTipText("Select from the following room types to display their respective room numbers and other information");
		roomTypeList.setSelectedIndex(0);
		
		updateLists();
		//System.out.println(db.getQueryResults("SELECT [startDate], [endDate] FROM [Reservations]"));
		// FROM [Rooms] WHERE [roomNumber] > 100"));
	}

	private void initialize() {

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

		frmHotelReservationGui = new JFrame();
		frmHotelReservationGui.setIconImage(Toolkit.getDefaultToolkit().getImage(HotelMgrGUI.class.getResource("/pictures/hotel-icon-7505.png")));
		frmHotelReservationGui.setMinimumSize(new Dimension(800, 530));
		frmHotelReservationGui.setTitle("Hotel Reservation GUI");
		frmHotelReservationGui.setBounds(100, 100, 830, 530);
		frmHotelReservationGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 41, 97, 21, 0, 0, 0, 334, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 26, 58, 43, 283, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		frmHotelReservationGui.getContentPane().setLayout(gridBagLayout);

		JLabel lblFrom = new JLabel("From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.fill = GridBagConstraints.VERTICAL;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblFrom, gbc_lblFrom);

		JButton btnStartDate = new JButton(startDate.toString());
		btnStartDate.setToolTipText("Starting date - Check-in as early as 1:00PM");
		GridBagConstraints gbc_btnStartDate = new GridBagConstraints();
		gbc_btnStartDate.fill = GridBagConstraints.VERTICAL;
		gbc_btnStartDate.anchor = GridBagConstraints.EAST;
		gbc_btnStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartDate.gridx = 2;
		gbc_btnStartDate.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnStartDate, gbc_btnStartDate);

		JLabel lblTo = new JLabel("to");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.fill = GridBagConstraints.VERTICAL;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 3;
		gbc_lblTo.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblTo, gbc_lblTo);

		JButton btnEndDate = new JButton(endDate.toString());
		btnEndDate.setToolTipText("Ending date - Check-out as late as 11:00AM");
		GridBagConstraints gbc_btnEndDate = new GridBagConstraints();
		gbc_btnEndDate.fill = GridBagConstraints.VERTICAL;
		gbc_btnEndDate.anchor = GridBagConstraints.WEST;
		gbc_btnEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndDate.gridx = 4;
		gbc_btnEndDate.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnEndDate, gbc_btnEndDate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.anchor = GridBagConstraints.EAST;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 5;
		gbc_panel_2.gridy = 1;
		frmHotelReservationGui.getContentPane().add(panel_2, gbc_panel_2);
		
		JLabel lblUsername = new JLabel("Username:");
		panel_2.add(lblUsername);
		
		fieldUsername = new JTextField();
		fieldUsername.setText("ajsnyde");
		panel_2.add(fieldUsername);
		fieldUsername.setColumns(7);
		
		JLabel lblPassword = new JLabel("Password:");
		panel_2.add(lblPassword);
		
		fieldPassword = new JPasswordField();

		fieldPassword.setColumns(6);
		panel_2.add(fieldPassword);
		
		JLabel lblStatus = new JLabel("Not logged in!");
		lblStatus.setForeground(new Color(130,130,0));
		panel_2.add(lblStatus);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setMargin(new Insets(1, 5, 1, 5));
		
		panel_2.add(btnLogin);
		
		JButton button = new JButton("Create Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountCreator accountCreator = new AccountCreator(db);
			}
		});
		button.setMargin(new Insets(1, 5, 1, 5));
		panel_2.add(button);

		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.gridwidth = 5;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 2;
		frmHotelReservationGui.getContentPane().add(splitPane, gbc_splitPane);

		JScrollPane roomTypeScroll = new JScrollPane();
		roomTypeScroll.setMinimumSize(new Dimension(150, 67));
		roomTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomTypeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateLists();
			}
		});
		roomTypeScroll.setViewportView(roomTypeList);
		roomTypeScroll.setPreferredSize(new Dimension(400, 100));
		splitPane.setLeftComponent(roomTypeScroll);

		JScrollPane roomNumScroll = new JScrollPane();
		roomNumList.setToolTipText("Not seeing any numbers? The rooms are probably booked. Try changing the reservation period.");
		roomNumScroll.setViewportView(roomNumList);
		roomNumScroll.setPreferredSize(new Dimension(50, 100));
		splitPane.setRightComponent(roomNumScroll);


		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 7;
		gbc_panel_1.gridy = 2;
		frmHotelReservationGui.getContentPane().add(panel_1, gbc_panel_1);
		lblPrice.setToolTipText("\"Night\" is defined as from 1PM to 11AM the next morning.");
		
		lblPrice.setBounds(10, 28, 136, 24);
		panel_1.add(lblPrice);
		
		lblRoomName.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblRoomName.setBounds(10, 11, 389, 24);
		panel_1.add(lblRoomName);
		tabbedPane.setToolTipText("Pictures of existing rooms may not reflect actual available rooms.");

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_tabbedPane.anchor = GridBagConstraints.SOUTH;
		gbc_tabbedPane.gridwidth = 5;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 4;
		frmHotelReservationGui.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_descriptionPanel = new GridBagConstraints();
		gbc_descriptionPanel.gridheight = 2;
		gbc_descriptionPanel.anchor = GridBagConstraints.NORTH;
		gbc_descriptionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionPanel.fill = GridBagConstraints.BOTH;
		gbc_descriptionPanel.gridx = 7;
		gbc_descriptionPanel.gridy = 3;
		frmHotelReservationGui.getContentPane().add(descriptionPanel, gbc_descriptionPanel);
		descriptionPanel.setLayout(new BorderLayout(0, 0));
		
		
		descriptionTxt.setFont(new Font("Georgia", Font.PLAIN, 11));
		descriptionPanel.add(descriptionTxt);
		descriptionTxt.setBackground(SystemColor.control);
		descriptionTxt.setEditable(false);
		descriptionTxt.setText("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"");

		JPanel reservationPanel = new JPanel();
		GridBagConstraints gbc_reservationPanel = new GridBagConstraints();
		gbc_reservationPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_reservationPanel.insets = new Insets(0, 0, 0, 5);
		gbc_reservationPanel.gridx = 7;
		gbc_reservationPanel.gridy = 5;
		frmHotelReservationGui.getContentPane().add(reservationPanel, gbc_reservationPanel);
		
		JLabel lblDays = new JLabel("Days: 0");
		lblDays.setHorizontalAlignment(SwingConstants.RIGHT);
		reservationPanel.add(lblDays);
		
		JLabel labelPrice = new JLabel("Total Cost: $0.00");
		labelPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		reservationPanel.add(labelPrice);
		
		JButton btnReservation = new JButton("Make Reservation!");
		btnReservation.setToolTipText("Login required to make a reservation");
		btnReservation.setEnabled(false);

		btnReservation.setHorizontalAlignment(SwingConstants.RIGHT);
		reservationPanel.add(btnReservation);
		
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(roomNumList.isSelectionEmpty()) {
					if(roomNumModel.size() > 0) {
						roomNumList.setBackground(new Color(255,255,255));
						roomNumList.setSelectedIndex(0);
					}
					else {
						roomNumList.setBackground(new Color(255,192,203));
						return;
					}
				}
				
				if(numDays > 0) {
					btnStartDate.setBackground(new Color(240,240,240));
					btnEndDate.setBackground(new Color(240,240,240));
					new ReservationConfirmation(
						db,
						fieldUsername.getText(),
						roomTypeList.getSelectedValue(), 
						Integer.parseInt(roomNumList.getSelectedValue()),
						startDate,
						endDate,
						numDays,
						pricePerNight
						);
					}
				else {
					btnStartDate.setBackground(new Color(124,9,2));
					btnEndDate.setBackground(new Color(124,9,2));
				}
			}
		});
		
		fieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLogin.doClick();
			}
		});
		
		login = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login(fieldUsername.getText(), String.valueOf(fieldPassword.getPassword()))) {
					lblStatus.setText("Logged in!");
					lblStatus.setForeground(new Color(0,130,0));
					fieldPassword.setEnabled(false);
					fieldUsername.setEnabled(false);
					btnReservation.setEnabled(true);
					btnLogin.setText("Logout");
					btnLogin.addActionListener(logout);
					btnLogin.removeActionListener(this);
				}
				else {
					lblStatus.setText("Bad credentials!");
					lblStatus.setForeground(new Color(255,0,0));
				}
				fieldPassword.setText("");
			}
		};
		
		btnLogin.addActionListener(login);
		
		logout = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatus.setText("Not logged in!");
				btnReservation.setEnabled(false);
				lblStatus.setForeground(new Color(130,130,0));
				fieldPassword.setEnabled(true);
				fieldUsername.setEnabled(true);
				btnLogin.setText("Login");
				btnLogin.addActionListener(login);
				btnLogin.removeActionListener(this);
			}
		};
		
		ActionListener getDates = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectDate dateDialog = new SelectDate("Date Selection");
				// Sets dates if already entered
				dateDialog.setStartDate(btnStartDate.getText()); 
				dateDialog.setEndDate(btnEndDate.getText());
				
				// Listener grabs dates and closes window
				dateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(dateDialog.getStartDate().isBefore(dateDialog.getEndDate())) {	// basic sanitation
							startDate = dateDialog.getStartDate();
							endDate = dateDialog.getEndDate();	
						} else {
							endDate = dateDialog.getStartDate();
							startDate = dateDialog.getEndDate();	
						}
						
						if(startDate.isBefore(LocalDate.now()))
							startDate = LocalDate.now();
						if(endDate.isBefore(LocalDate.now()))
							endDate = LocalDate.now();
						
						numDays = Math.abs(Duration.between(endDate.atTime(0, 0), startDate.atTime(0, 0)).toDays());
						lblDays.setText("Days: " + numDays);
						btnStartDate.setText(startDate.toString());
						btnEndDate.setText(endDate.toString());
						updateLists();
						
						if(!roomNumModel.isEmpty())
							roomNumList.setSelectedIndex(0);
						
						dateDialog.killSelf();
					}
				});
			}
		};
		
		roomNumList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(roomNumList.getSelectedValue() != null) {
					labelPrice.setText("Total Cost: $" + new DecimalFormat("#0.00").format(pricePerNight*numDays));
				}
				else {
					labelPrice.setText("Total Cost: $0.00");
					
				}
				
			}
		});

		btnStartDate.addActionListener(getDates);
		btnEndDate.addActionListener(getDates);
	}

	void updateLists() {
		String selected = roomTypeList.getSelectedValue();
		System.out.println(selected + " is selected! Updating Lists accordingly.");
		
		// show room numbers of a given type
		if (roomTypeList.getSelectedValue() != null && startDate != null && endDate != null) {	
			// Note that the comments describe the portion of the SQL statement ABOVE
			setList(roomNumModel, "SELECT DISTINCT [RoomNumber] FROM [Rooms], [Reservations] WHERE "
						// In the end, I'm gonna want a list of numbers from rooms
					+ "[name] = \'" + selected + "\'"
					// That matches the type of room selected on the left panel
					+ " AND [RoomNumber] NOT IN "
					// And not in the following subgroup
					+ "(SELECT [RoomID] FROM [Reservations] WHERE "
					// Grab roomNumbers from Reservations
					+ toSQL(startDate) + " <= [endDate] AND"
					+ toSQL(endDate) + " >= [startDate])");
					// That don't conflict with the selected date
					// (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1) dateRanges overlap if this is true
			lblRoomName.setText(db.getQueryResults("SELECT [name] FROM [rooms] WHERE [name] = \'" + selected + "\'").get(0).get(0));
			pricePerNight = Double.parseDouble(db.getQueryResults("SELECT [price], [name] FROM [rooms] WHERE [name] = \'" + selected + "\'").get(0).get(0));
			lblPrice.setText("Cost per night: $" + new DecimalFormat("#0.00").format(pricePerNight));
			descriptionTxt.setText(db.getQueryResults("SELECT  [description], [name] FROM [rooms] WHERE [name] = \'" + selected + "\'").get(0).get(0));
			updatePics(db.getQueryResults("SELECT [PicSuffix],[name] FROM [rooms] WHERE [name] = \'" + selected + "\'").get(0).get(0));
			
			if(!roomNumModel.isEmpty())
				roomNumList.setSelectedIndex(0);
		}
		else // show all room numbers if no type is given
			setList(roomNumModel, "SELECT [RoomNumber] FROM [Rooms]");
	}

	void setList(DefaultListModel<String> listModel, String query) {
		listModel.clear();
		for (ArrayList<String> results : db.getQueryResults(query)) {
			listModel.addElement(results.get(0));
		}
	}
	
	void updatePics(String picSuffix) {
		ImageIcon icon = new ImageIcon();
		tabbedPane.removeAll();
		for(int i = 0; new File("src/pictures/" + picSuffix + i + ".jpg").exists(); ++i)
		try {
			icon = new ImageIcon((ImageIO.read(new File("src/pictures/" + picSuffix + i + ".jpg")).getScaledInstance(443, 295, Image.SCALE_FAST)));
			JLabel label = new JLabel("", icon, JLabel.CENTER);
			tabbedPane.addTab(i+1 + "", null, label, null);
		} catch (IOException e) {
			System.out.println("While setting an image for " + picSuffix + " , something broke!");
		}
	}
	
	
	boolean login(String username, String password) {
		System.out.println(fieldPassword.getPassword());
		
		System.out.println(db.getQueryResults("Select [Username],[Password] FROM [customers] WHERE [username] = '" + username + "'"
				+ " AND [password] = '" + password + "'"));
		
		if(!db.getQueryResults("Select [Username],[Password] FROM [customers] WHERE [username] = '" + username + "' "
				+ "AND [Password] = '" + password + "'").isEmpty())
			return true;
		return false;
	}
	
	void setPicList(DefaultListModel<String> listModel, String query) {
		listModel.clear();
		for (ArrayList<String> results : db.getQueryResults(query)) {
			listModel.addElement(results.get(0));
		}
	}
	
	String toSQL(LocalDate in) {	// quick little helper class to clean things up a bit
		return "'" + in + " 00:00:00.000000'";
	}
}
