package hotelSim;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Insets;
import javax.swing.JSplitPane;
import java.awt.List;
import java.io.File;
import java.io.IOException;

public class HotelMgrGUI {

	private JFrame frmHotelReservationGui;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelMgrGUI window = new HotelMgrGUI();
					window.frmHotelReservationGui.setVisible(true);
					DatabaseWrapper db = new DatabaseWrapper();
					System.out.println(db.getQueryResults("SELECT distinct [name], [roomnumber] FROM [Rooms]"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HotelMgrGUI() {
		initialize();
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
		gridBagLayout.columnWidths = new int[]{13, 41, 97, 21, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{19, 29, 0, 87, 50, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmHotelReservationGui.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblFrom = new JLabel("From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblFrom, gbc_lblFrom);
		
		JButton btnNewButton = new JButton("startDate");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblTo = new JLabel("to");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 3;
		gbc_lblTo.gridy = 1;
		frmHotelReservationGui.getContentPane().add(lblTo, gbc_lblTo);
		
		JButton btnNewButton_1 = new JButton("endDate");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 1;
		frmHotelReservationGui.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.gridwidth = 5;
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 2;
		frmHotelReservationGui.getContentPane().add(splitPane, gbc_splitPane);
		
		List list = new List();
		splitPane.setLeftComponent(list);
		
		List list_1 = new List();
		splitPane.setRightComponent(list_1);

		ImageIcon icon = new ImageIcon();
		try {
			icon = new ImageIcon(ImageIO.read(new File("src/database/defaultImage.jpeg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel label = new JLabel("", icon, JLabel.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 4;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 4;
		frmHotelReservationGui.getContentPane().add(label, gbc_label);
	}

}
