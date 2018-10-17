package Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.Timer;

import CommonClasses.*;

public class ForgotPass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public boolean n;
	public String name,pass1,pass2,nickname;
	public CustomerInfo customer;
	public JLabel lblNewLabel_1,lblNewPassword,lblConfirmPassword,lblPleaseEnterThe,lblPleaseEnterThe2,lblPleaseEnterThe3,lblUsername,lblPleaseEnterThe4,lblPleaseEnterThe5,lblPleaseFillValid,lblResetPassword;
	public JTextPane textPane,textPane_3;
	public JPasswordField textPane_1,textPane_2;
	public JButton btnNewButton,button;
	
	public ForgotPass(Login parent,ObjectInputStream input,ObjectOutputStream output){

		setBackground(new Color(0, 250, 154));
		setBounds(338, 171, 594, 535);
		setLayout(null);
		
		lblNewLabel_1 = new JLabel("What is your nick name?");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setBounds(81, 184, 253, 25);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblNewLabel_1);
		
		textPane = new JTextPane();
		textPane.setBounds(81, 209, 428, 37);
		textPane.setFont(new Font("Arial", Font.PLAIN, 25));
		add(textPane);
		
		textPane_1 = new JPasswordField();
		textPane_1.setFont(new Font("Dialog", Font.PLAIN, 25));
		textPane_1.setBounds(81, 299, 428, 37);
		add(textPane_1);
		
		textPane_2 = new JPasswordField();
		textPane_2.setFont(new Font("Dialog", Font.PLAIN, 25));
		textPane_2.setBounds(81, 388, 428, 37);
		add(textPane_2);
		
		lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(0, 0, 139));
		lblNewPassword.setBounds(81, 272, 150, 30);
		lblNewPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblNewPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(new Color(0, 0, 139));
		lblConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(81, 358, 187, 37);
		add(lblConfirmPassword);
		
		lblPleaseEnterThe = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe.setVisible(false);
		lblPleaseEnterThe.setBounds(81, 245, 225, 15);
		add(lblPleaseEnterThe);
		
		lblPleaseEnterThe2 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe2.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe2.setVisible(false);
		lblPleaseEnterThe2.setBounds(81, 336, 225, 15);
		add(lblPleaseEnterThe2);
		
		lblPleaseEnterThe3 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe3.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe3.setVisible(false);
		lblPleaseEnterThe3.setBounds(81, 424, 225, 15);
		add(lblPleaseEnterThe3);
		
		lblUsername = new JLabel("User_name");
		lblUsername.setForeground(new Color(0, 0, 139));
		lblUsername.setBounds(81, 98, 123, 15);
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblUsername);
		
		textPane_3 = new JTextPane();
		textPane_3.setBounds(81, 118, 428, 37);
		textPane_3.setFont(new Font("Arial", Font.PLAIN, 25));
		add(textPane_3);
		
		lblPleaseEnterThe4 = new JLabel("Please enter the valid text");
		lblPleaseEnterThe4.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe4.setBounds(81, 154, 198, 15);
		lblPleaseEnterThe4.setVisible(false);
		add(lblPleaseEnterThe4);
		
		lblPleaseEnterThe5 = new JLabel("Password not matched");
		lblPleaseEnterThe5.setForeground(new Color(178, 34, 34));
		lblPleaseEnterThe5.setVisible(false);
		lblPleaseEnterThe5.setBounds(81, 425, 225, 15);
		add(lblPleaseEnterThe5);
		
		lblPleaseFillValid = new JLabel("Please fill valid credentials");
		lblPleaseFillValid.setForeground(new Color(178, 34, 34));
		lblPleaseFillValid.setBounds(197, 440, 198, 15);
		lblPleaseFillValid.setVisible(false);		
		add(lblPleaseFillValid);
		
		btnNewButton = new JButton("CHANGE PASSWORD");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
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
				
				if(textPane_3.getText().isEmpty() || textPane.getText().isEmpty() || textPane_1.getText().isEmpty() || textPane_2.getText().isEmpty())
				{
				   if(textPane.getText().isEmpty())
						lblPleaseEnterThe.setVisible(true);
				   if(textPane_1.getText().isEmpty())
						lblPleaseEnterThe2.setVisible(true);
				   if(textPane_2.getText().isEmpty())
						lblPleaseEnterThe3.setVisible(true);
				   if(textPane_3.getText().isEmpty())
						lblPleaseEnterThe4.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							if(textPane.getText().isEmpty())
								lblPleaseEnterThe.setVisible(false);
						   if(textPane_1.getText().isEmpty())
								lblPleaseEnterThe2.setVisible(false);
						   if(textPane_2.getText().isEmpty())
								lblPleaseEnterThe3.setVisible(false);
						   if(textPane_3.getText().isEmpty())
								lblPleaseEnterThe4.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
				name=textPane_3.getText();
				nickname=textPane.getText();
				pass1=textPane_1.getText();
				pass2=textPane_2.getText();
				if(!pass1.equals(pass2))
				{
					textPane_1.setText("");
					textPane_2.setText("");
					lblPleaseEnterThe5.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseEnterThe5.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
				try 
				{
					customer=new CustomerInfo();
					customer.name=name;
					customer.password=pass1;
					customer.nickname=nickname;
					output.writeObject(customer);
					output.reset();
					n=(boolean) input.readObject();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if(n)
				{
					parent.panel.setVisible(true);
					setVisible(false);
				}
				else
				{
					lblPleaseFillValid.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseFillValid.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
				}
				textPane.setText("");
				textPane_1.setText("");
				textPane_2.setText("");
				textPane_3.setText("");
			}
		});
		btnNewButton.setBounds(176, 468, 237, 34);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 17));
		btnNewButton.setBackground(new Color(0, 128, 0));
		add(btnNewButton);
		
		lblResetPassword = new JLabel("RESET PASSWORD");
		lblResetPassword.setForeground(new Color(0, 0, 139));
		lblResetPassword.setBounds(187, 31, 208, 42);
		lblResetPassword.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblResetPassword);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.panel.setVisible(true);
				setVisible(false);
			}
		});
		button.setBackground(new Color(0, 100, 0));
		button.setFocusable(false);
		button.setIcon(new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/back5.png"));
		button.setBounds(12, 12, 50, 37);
		add(button);
	}
}
