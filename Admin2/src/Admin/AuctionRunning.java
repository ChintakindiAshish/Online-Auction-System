package Admin;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JLabel;
import java.awt.Color;

public class AuctionRunning extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AuctionRunning() {
		
		setLayout(null);
		setBounds(0,0,980,564);
		
		JLabel lblNewLabel = new JLabel("Auction is Running now,");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(229, 202, 560, 54);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 40));
		add(lblNewLabel);
		
		JLabel lblPleaseUploadThe = new JLabel("Please Upload the Items Later.");
		lblPleaseUploadThe.setForeground(Color.RED);
		lblPleaseUploadThe.setFont(new Font("Serif", Font.BOLD, 40));
		lblPleaseUploadThe.setBounds(151, 256, 700, 54);
		add(lblPleaseUploadThe);
	}
}
