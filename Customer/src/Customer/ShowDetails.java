package Customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CommonClasses.CustomerInfo;

public class ShowDetails extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JLabel Head,lblNewLabel,label,label_1,label_2,label_4,label_5,label_6,label_7,label_8,label_10;
	public JButton btnEditProfile;
	public ShowDetails(ObjectInputStream input,ObjectOutputStream output,CustomerInfo student) {
		
		setLayout(null);
		setBounds(309,180,980,564);
		
		PersonalDetail personaldetail=new PersonalDetail(input,output,this,student);
		personaldetail.setLayout(null);
		personaldetail.setVisible(false);
		add(personaldetail);
		
		Head = new JLabel("Customer Details");
		Head.setBounds(379, 24, 214, 30);
		Head.setForeground(Color.BLUE);
		Head.setFont(new Font("Arial", Font.PLAIN, 25));
		add(Head);
		
		lblNewLabel = new JLabel(student.name);
		lblNewLabel.setBounds(543, 80, 353, 25);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblNewLabel);
		
		label = new JLabel(student.address);
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		label.setBounds(543, 140, 353, 25);
		add(label);
		
		label_1 = new JLabel(student.dob);
		label_1.setFont(new Font("Serif", Font.PLAIN, 20));
		label_1.setBounds(543, 200, 353, 25);
		add(label_1);
		
		label_2 = new JLabel(String.valueOf(student.mob));
		label_2.setFont(new Font("Serif", Font.PLAIN, 20));
		label_2.setBounds(543, 260, 353, 25);
		add(label_2);
		
		label_4 = new JLabel(student.nickname);
		label_4.setFont(new Font("Serif", Font.PLAIN, 20));
		label_4.setBounds(543, 319, 353, 25);
		add(label_4);
		
		label_5 = new JLabel("Address");
		label_5.setFont(new Font("Serif", Font.PLAIN, 20));
		label_5.setBounds(201, 140, 153, 25);
		add(label_5);
		
		label_6 = new JLabel("Name");
		label_6.setFont(new Font("Serif", Font.PLAIN, 20));
		label_6.setBounds(201, 80, 153, 25);
		add(label_6);
		
		label_7 = new JLabel("Date of Birth");
		label_7.setFont(new Font("Serif", Font.PLAIN, 20));
		label_7.setBounds(201, 200, 153, 25);
		add(label_7);
		
		label_8 = new JLabel("Mobile No");
		label_8.setFont(new Font("Serif", Font.PLAIN, 20));
		label_8.setBounds(201, 260, 153, 25);
		add(label_8);
		
		label_10 = new JLabel("Nick Name");
		label_10.setFont(new Font("Serif", Font.PLAIN, 20));
		label_10.setBounds(201, 319, 153, 25);
		add(label_10);
		
		btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.setFont(new Font("Arial",Font.BOLD,20));
		btnEditProfile.setFocusable(false);
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					output.reset();
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
				
				lblNewLabel.setVisible(false);
				label.setVisible(false);
				label_1.setVisible(false);
				label_2.setVisible(false);
				label_4.setVisible(false);
				btnEditProfile.setVisible(false);
				personaldetail.setVisible(true);
			}
		});
		btnEditProfile.setBounds(379, 423, 172, 37);
		add(btnEditProfile);
	}
}
