package hotelSim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SelectDate extends JDialog {
	JButton okButton = new JButton("OK");
	final DateChooserPanel startDateBox = new DateChooserPanel();
	final DateChooserPanel endDateBox = new DateChooserPanel();
	private final JPanel datePanel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblReservationStartDate = new JLabel("Reservation Start Date:");
	private final JLabel lblReservationEndDate = new JLabel("Reservation End Date:");

	/**
	 * Launch the application.
	 */
	public static void main(String title) {
		try {
			SelectDate dialog = new SelectDate(title);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectDate(String title) {
		setType(Type.POPUP);
		setTitle(title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						killSelf();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		{
			getContentPane().add(datePanel, BorderLayout.CENTER);
		}
		datePanel.setLayout(new GridLayout(0, 2, 10, 0));
		{
			datePanel.add(startDateBox);
		}
		datePanel.add(endDateBox);
		{
			getContentPane().add(panel_1, BorderLayout.NORTH);
		}
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		{
			lblReservationStartDate.setHorizontalAlignment(SwingConstants.CENTER);
			panel_1.add(lblReservationStartDate);
		}
		{
			lblReservationEndDate.setHorizontalAlignment(SwingConstants.CENTER);
			panel_1.add(lblReservationEndDate);
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	void killSelf() {
		setVisible(false);
		dispose();
	}

	public void addWindowStateListener(ActionListener actionListener) {
		okButton.addActionListener(actionListener);
	}

	public LocalDate getStartDate() {
		return startDateBox.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public LocalDate getEndDate() {
		return endDateBox.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void setStartDate(String in) {
		try{
			startDateBox.setDate(Date.from(LocalDate.parse(in).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		catch (Exception e){
		  System.out.println("Failure while trying to set start date.");
		}
	}

	public void setEndDate(String in) {
		try{
			endDateBox.setDate(Date.from(LocalDate.parse(in).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		catch (Exception e){
		     System.out.println("Failure while trying to set end date.");
		}
	}
}
