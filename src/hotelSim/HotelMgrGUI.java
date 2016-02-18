package hotelSim;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
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
import java.time.LocalDate;
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
import javax.swing.ScrollPaneConstants;

public class HotelMgrGUI {

	private JFrame frmHotelReservationGui;
	private LocalDate endDate = LocalDate.now();
	private LocalDate startDate = LocalDate.now();

	final DefaultListModel<String> roomTypeModel = new DefaultListModel<String>();
	final JList<String> roomTypeList = new JList<String>(roomTypeModel);
	final DefaultListModel<String> roomNumModel = new DefaultListModel<String>();
	final JList<String> roomNumList = new JList<String>(roomNumModel);

	DatabaseWrapper db = new DatabaseWrapper();

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
		updateLists();
		setList(roomTypeModel, "SELECT distinct [name], [price], [NonSmoke?] FROM [Rooms] ORDER BY [price], [NonSmoke?] DESC");
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
		frmHotelReservationGui.setTitle("Hotel Reservation GUI");
		frmHotelReservationGui.setBounds(100, 100, 775, 643);
		frmHotelReservationGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 33, 41, 97, 21, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 19, 29, 87, 183, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmHotelReservationGui.getContentPane().setLayout(gridBagLayout);

		JLabel lblFrom = new JLabel("From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblFrom, gbc_lblFrom);

		JButton btnStartDate = new JButton(startDate.toString());
		GridBagConstraints gbc_btnStartDate = new GridBagConstraints();
		gbc_btnStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartDate.gridx = 2;
		gbc_btnStartDate.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnStartDate, gbc_btnStartDate);

		JLabel lblTo = new JLabel("to");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 3;
		gbc_lblTo.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblTo, gbc_lblTo);

		JButton btnEndDate = new JButton(endDate.toString());
		GridBagConstraints gbc_btnEndDate = new GridBagConstraints();
		gbc_btnEndDate.anchor = GridBagConstraints.WEST;
		gbc_btnEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndDate.gridx = 4;
		gbc_btnEndDate.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnEndDate, gbc_btnEndDate);

		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.gridwidth = 5;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 2;
		frmHotelReservationGui.getContentPane().add(splitPane, gbc_splitPane);

		JScrollPane roomTypeScroll = new JScrollPane();
		roomTypeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		roomTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomTypeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateLists();
			}
		});
		roomTypeScroll.setViewportView(roomTypeList);
		roomTypeScroll.setPreferredSize(new Dimension(250, 100));
		splitPane.setLeftComponent(roomTypeScroll);

		JScrollPane roomNumScroll = new JScrollPane();
		roomNumScroll.setViewportView(roomNumList);
		roomNumScroll.setPreferredSize(new Dimension(50, 100));
		splitPane.setRightComponent(roomNumScroll);

		ImageIcon icon = new ImageIcon();
		try {
			icon = new ImageIcon(ImageIO.read(new File("src/database/defaultImage.jpeg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 5;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		frmHotelReservationGui.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JLabel label = new JLabel("", icon, JLabel.CENTER);
		tabbedPane.addTab("New tab", null, label, null);

		ActionListener getDates = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectDate dateDialog = new SelectDate("Date Selection");
				// Sets dates if already entered
				dateDialog.setStartDate(btnStartDate.getText()); 
				dateDialog.setEndDate(btnEndDate.getText());
				
				// Listener grabs dates and closes window
				dateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startDate = dateDialog.getStartDate();
						endDate = dateDialog.getEndDate();
						btnStartDate.setText(startDate.toString());
						btnEndDate.setText(endDate.toString());
						updateLists();
						dateDialog.killSelf();
					}
				});
			}
		};

		btnStartDate.addActionListener(getDates);
		btnEndDate.addActionListener(getDates);
	}

	void updateLists() {
		String selected = roomTypeList.getSelectedValue();
		System.out.println(selected + " is selected! Updating Lists accordingly.");
		
		// show room numbers of a given type
		if (roomTypeList.getSelectedValue() != null && startDate != null && endDate != null)	
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
		else // show all room numbers if no type is given
			setList(roomNumModel, "SELECT [RoomNumber] FROM [Rooms]");

	}

	void setList(DefaultListModel<String> listModel, String query) {
		listModel.clear();
		for (ArrayList<String> results : db.getQueryResults(query)) {
			listModel.addElement(results.get(0));
		}
	}
	
	String toSQL(LocalDate in) {	// quick little helper class to clean things up a bit
		return "'" + in + " 00:00:00.000000'";
	}
}
