package Customer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotStarted extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public NotStarted() {

		setLayout(null);
		setBounds(309,180,980,564);
		
		JLabel lblNewLabel = new JLabel("Latest Auction is not Started yet,");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(125, 202, 739, 54);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 40));
		add(lblNewLabel);
		
		JLabel lblPleaseUploadThe = new JLabel("Please try after some Time.");
		lblPleaseUploadThe.setForeground(Color.RED);
		lblPleaseUploadThe.setFont(new Font("Serif", Font.BOLD, 40));
		lblPleaseUploadThe.setBounds(188, 256, 611, 54);
		add(lblPleaseUploadThe);
	}

}
