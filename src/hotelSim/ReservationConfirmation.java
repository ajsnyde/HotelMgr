package hotelSim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JTextField;

public class ReservationConfirmation extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField notes;

	public ReservationConfirmation(DatabaseWrapper db, String username, String roomType, int roomNum, LocalDate startDate, LocalDate endDate, long numDays, double pricePerNight) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblPleaseConfirmThe = new JLabel("Please confirm the following data:");
			GridBagConstraints gbc_lblPleaseConfirmThe = new GridBagConstraints();
			gbc_lblPleaseConfirmThe.gridwidth = 2;
			gbc_lblPleaseConfirmThe.insets = new Insets(0, 0, 5, 5);
			gbc_lblPleaseConfirmThe.gridx = 1;
			gbc_lblPleaseConfirmThe.gridy = 0;
			contentPanel.add(lblPleaseConfirmThe, gbc_lblPleaseConfirmThe);
		}
		{
			JLabel lblNewLabel = new JLabel("Room Type:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblRoomtype = new JLabel(roomType);
			GridBagConstraints gbc_lblRoomtype = new GridBagConstraints();
			gbc_lblRoomtype.anchor = GridBagConstraints.WEST;
			gbc_lblRoomtype.insets = new Insets(0, 0, 5, 5);
			gbc_lblRoomtype.gridx = 2;
			gbc_lblRoomtype.gridy = 1;
			contentPanel.add(lblRoomtype, gbc_lblRoomtype);
		}
		{
			JLabel lblRoomNumber = new JLabel("Room Number:");
			GridBagConstraints gbc_lblRoomNumber = new GridBagConstraints();
			gbc_lblRoomNumber.anchor = GridBagConstraints.WEST;
			gbc_lblRoomNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblRoomNumber.gridx = 1;
			gbc_lblRoomNumber.gridy = 2;
			contentPanel.add(lblRoomNumber, gbc_lblRoomNumber);
		}
		{
			JLabel lblRoomnum = new JLabel(roomNum + "");
			GridBagConstraints gbc_lblRoomnum = new GridBagConstraints();
			gbc_lblRoomnum.anchor = GridBagConstraints.WEST;
			gbc_lblRoomnum.insets = new Insets(0, 0, 5, 5);
			gbc_lblRoomnum.gridx = 2;
			gbc_lblRoomnum.gridy = 2;
			contentPanel.add(lblRoomnum, gbc_lblRoomnum);
		}
		{
			JLabel lblCheckinDate = new JLabel("Check-in Date:");
			GridBagConstraints gbc_lblCheckinDate = new GridBagConstraints();
			gbc_lblCheckinDate.anchor = GridBagConstraints.WEST;
			gbc_lblCheckinDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblCheckinDate.gridx = 1;
			gbc_lblCheckinDate.gridy = 3;
			contentPanel.add(lblCheckinDate, gbc_lblCheckinDate);
		}
		{
			JLabel lblStartdate = new JLabel(startDate + "");
			GridBagConstraints gbc_lblStartdate = new GridBagConstraints();
			gbc_lblStartdate.anchor = GridBagConstraints.WEST;
			gbc_lblStartdate.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartdate.gridx = 2;
			gbc_lblStartdate.gridy = 3;
			contentPanel.add(lblStartdate, gbc_lblStartdate);
		}
		{
			JLabel lblCheckoutDate = new JLabel("Check-out Date:");
			GridBagConstraints gbc_lblCheckoutDate = new GridBagConstraints();
			gbc_lblCheckoutDate.anchor = GridBagConstraints.WEST;
			gbc_lblCheckoutDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblCheckoutDate.gridx = 1;
			gbc_lblCheckoutDate.gridy = 4;
			contentPanel.add(lblCheckoutDate, gbc_lblCheckoutDate);
		}
		{
			JLabel lblEnddate = new JLabel(endDate + "");
			GridBagConstraints gbc_lblEnddate = new GridBagConstraints();
			gbc_lblEnddate.anchor = GridBagConstraints.WEST;
			gbc_lblEnddate.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnddate.gridx = 2;
			gbc_lblEnddate.gridy = 4;
			contentPanel.add(lblEnddate, gbc_lblEnddate);
		}
		{
			JLabel lblDays = new JLabel("Days:");
			GridBagConstraints gbc_lblDays = new GridBagConstraints();
			gbc_lblDays.anchor = GridBagConstraints.WEST;
			gbc_lblDays.insets = new Insets(0, 0, 5, 5);
			gbc_lblDays.gridx = 1;
			gbc_lblDays.gridy = 5;
			contentPanel.add(lblDays, gbc_lblDays);
		}
		{
			JLabel lblNumdays = new JLabel(numDays + "");
			GridBagConstraints gbc_lblNumdays = new GridBagConstraints();
			gbc_lblNumdays.anchor = GridBagConstraints.WEST;
			gbc_lblNumdays.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumdays.gridx = 2;
			gbc_lblNumdays.gridy = 5;
			contentPanel.add(lblNumdays, gbc_lblNumdays);
		}
		{
			JLabel lblTotalCost = new JLabel("Total Cost:");
			GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
			gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
			gbc_lblTotalCost.anchor = GridBagConstraints.WEST;
			gbc_lblTotalCost.gridx = 1;
			gbc_lblTotalCost.gridy = 6;
			contentPanel.add(lblTotalCost, gbc_lblTotalCost);
		}
		{
			JLabel lblTotalcost = new JLabel("$" + numDays*pricePerNight);
			GridBagConstraints gbc_lblTotalcost = new GridBagConstraints();
			gbc_lblTotalcost.insets = new Insets(0, 0, 5, 5);
			gbc_lblTotalcost.anchor = GridBagConstraints.WEST;
			gbc_lblTotalcost.gridx = 2;
			gbc_lblTotalcost.gridy = 6;
			contentPanel.add(lblTotalcost, gbc_lblTotalcost);
		}
		{
			JLabel lblNotes = new JLabel("Notes:");
			GridBagConstraints gbc_lblNotes = new GridBagConstraints();
			gbc_lblNotes.anchor = GridBagConstraints.WEST;
			gbc_lblNotes.insets = new Insets(0, 0, 0, 5);
			gbc_lblNotes.gridx = 1;
			gbc_lblNotes.gridy = 7;
			contentPanel.add(lblNotes, gbc_lblNotes);
		}
		{
			notes = new JTextField();
			GridBagConstraints gbc_notes = new GridBagConstraints();
			gbc_notes.insets = new Insets(0, 0, 0, 5);
			gbc_notes.fill = GridBagConstraints.HORIZONTAL;
			gbc_notes.gridx = 2;
			gbc_notes.gridy = 7;
			contentPanel.add(notes, gbc_notes);
			notes.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCheckout = new JButton("Checkout");
				btnCheckout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(db.newReservation(username, roomType, roomNum, startDate, endDate, numDays, pricePerNight, notes.getText()))
							JOptionPane.showMessageDialog(new Frame(), "Your reservation has been made. $" + pricePerNight*numDays + " has been charged to your account.");
						else
							JOptionPane.showMessageDialog(new Frame(),"Reservation creation has failed", "Database Error", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				});
				btnCheckout.setActionCommand("OK");
				buttonPane.add(btnCheckout);
				getRootPane().setDefaultButton(btnCheckout);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

}
