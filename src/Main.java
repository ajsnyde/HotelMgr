import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import database.*;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Main {
	boolean toggle = true;
	int currentPage = 1;
	int maxPage = 10;
	
	private LocalDate endDate;
	private LocalDate startDate;
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	
	final DefaultListModel<String> roomNameModel = new DefaultListModel<String>();
	final JList<String> roomNameList = new JList<String>(roomNameModel);	
	final DefaultListModel<String> roomNumModel = new DefaultListModel<String>();
	final JList<String> roomNumList = new JList<String>(roomNumModel);	
	
	// loads of components:
	JFrame frmMainWindow;
	JButton btnForward = new JButton("Forward ->");
	JButton btnBack = new JButton("<- Back");	
	
	JPanel startPanel = new JPanel();
	JLabel lblStartDate = new JLabel("Start Date:");
	JTextField startDateField = new JTextField();
	JButton btnSelectStartDate = new JButton("Select Date!");
	
	JPanel endPanel = new JPanel();
	JLabel lblEndDate = new JLabel("End Date:");	
	JTextField endDateField = new JTextField();
	JButton btnSelectEndDate = new JButton("Select Date!");
	private final JPanel midPanel = new JPanel();
	private final JPanel bottomPanel = new JPanel();
	private final JLabel lblPageOf = new JLabel("Page " + currentPage + " of " + maxPage);
	private final JPanel RoomPanel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Available Rooms:");
	private final JSplitPane splitPane_1 = new JSplitPane();
	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane roomTypeScroll = new JScrollPane();
	private final JScrollPane roomNumScroll = new JScrollPane();

	Database database = new Database();
	private final JPanel datesPanel = new JPanel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				
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
		updateRoomNameList();
		updateRoomNumList();
		roomTypeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		roomTypeScroll.setPreferredSize(new Dimension(150,100));
		roomNameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateRoomNumList();
			}
		});
		roomTypeScroll.setViewportView(roomNameList);
		roomNumScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		roomNumScroll.setViewportView(roomNumList);
		roomNumScroll.setPreferredSize(new Dimension(50,100));
		
		
		frmMainWindow = new JFrame();
		frmMainWindow.setTitle("Main Window");
		frmMainWindow.setBounds(100, 100, 347, 396);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frmMainWindow.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(btnBack);
		bottomPanel.add(btnForward);
		bottomPanel.add(lblPageOf);
		
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(++currentPage);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGUI(--currentPage);
			}
		});
		
		frmMainWindow.getContentPane().add(midPanel, BorderLayout.CENTER);
		
		midPanel.add(datesPanel);
		datesPanel.setLayout(new BorderLayout(0, 0));
		datesPanel.add(startPanel, BorderLayout.NORTH);
		
		startPanel.add(lblStartDate);
		startDateField.setEnabled(false);
		startDateField.setColumns(10);
		startPanel.add(startDateField);
		startPanel.add(btnSelectStartDate);
		datesPanel.add(endPanel, BorderLayout.SOUTH);
		endPanel.add(lblEndDate);
		endDateField.setEnabled(false);
		endDateField.setColumns(10);
		endPanel.add(endDateField);
		endPanel.add(btnSelectEndDate);
		
		btnSelectEndDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectDate endDateDialog = new SelectDate("Checkout Date Selection");
				endDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						endDate = endDateDialog.datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						endDateField.setText(endDate.toString());
						endDateDialog.killSelf();
					}
				});
			}
		});
		btnSelectStartDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectDate startDateDialog = new SelectDate("Checkin Date Selection");
				startDateDialog.addWindowStateListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//this line converts Date.util to LocalDate; no time needed
						startDate = startDateDialog.datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						startDateField.setText(startDate.toString());
						startDateDialog.killSelf();
					}
				});
			}
		});
		
		midPanel.add(RoomPanel);
		RoomPanel.add(splitPane_1);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setLeftComponent(lblNewLabel);
		splitPane_1.setRightComponent(splitPane);
		splitPane.setLeftComponent(roomTypeScroll);
		splitPane.setRightComponent(roomNumScroll);
	}
	
	public void updateRoomNameList(){
		roomNameModel.clear();
		for(String roomName: database.getRoomNames()) {
			if(!roomNameModel.contains(roomName))
				roomNameModel.addElement(roomName);
		}
	}
	public void updateRoomNumList(){
		roomNumModel.clear();
		for(Room room: database.getRooms()) {
			if(room.name.equals(roomNameList.getSelectedValue()))
				roomNumModel.addElement(room.number + "");
		}
	}
	
	
	
	void updateGUI(int page){	// not quite sure whether I should remove components (which might overly complicate things),
								// or just set components visible/invisible (which would hold resources and complicate things)
		
		//for(Component component: frmMainWindow.getContentPane().getComponents())	// screw it, lesser of two evils..
		//	component.setVisible(false);
		
		switch(page) {
		case 1:
			startPanel.setVisible(true);
			endPanel.setVisible(true);
			updateRoomNameList();
			updateRoomNumList();
			roomTypeScroll.setViewportView(roomNameList);
			roomNumScroll.setViewportView(roomNumList);
			break;
		case 2:
			startPanel.setVisible(false);
			endPanel.setVisible(false);
			break;
		default:
			break;
		}	
		lblPageOf.setText("Page " + page + " of " + maxPage);
	}
}