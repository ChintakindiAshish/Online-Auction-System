package Login;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Customer.*;
import CommonClasses.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public boolean n;
	public String username,pass;
	public Login thisframe;
	public static Container c;
	public static CustomerInfo customer;
	public static Socket c2;
	public static ObjectInputStream input;
	public static ObjectOutputStream output;
	public static void main(String[] args)
	{	
		EventQueue.invokeLater(new Runnable() 
		{		
			public void run() 
			{
				try
				{
					customer=new CustomerInfo();
					Login frame = new Login();
				    frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLabel label3;
	public JPanel panel;
	public Dimension screenSize;
	public ImageIcon icon,icon2;
	public JLabel label2,lblNewLabel,lblNewLabel_1,label,lblPleaseFillAll,lblPleaseFillValid,lblAdminPortal;
	public ForgotPass forgotpass;
	public NewUser newuser;
	public JTextPane textPane;
	public JPasswordField password;
	public JButton btnNewButton_1,btnNewUser,btnNewButton;
	
	public Login() throws UnknownHostException, IOException {
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		try {
		c2=new Socket("localhost",9783);
		output=new ObjectOutputStream(c2.getOutputStream());
		input=new ObjectInputStream(c2.getInputStream());
		}
		catch(ConnectException e)
		{
			JOptionPane.showMessageDialog(null,"Server Down");
			System.exit(0);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Login Portal");
		icon=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/index.png");
		setIconImage(icon.getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAdminPortal = new JLabel("Customer Portal");
		lblAdminPortal.setBounds(538, 133, 211, 26);
		lblAdminPortal.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblAdminPortal);
		lblAdminPortal.setForeground(Color.WHITE);
		
		label2=new JLabel("AUCTION BASKET LOGIN");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Dialog", Font.BOLD, 25));
		label2.setBounds(469,79,354,60);
		contentPane.add(label2);
		
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/login.jpg");
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 255, 127));
		panel.setBounds(338, 171, 594, 411);
		panel.setLayout(null);
		contentPane.add(panel);
		
		forgotpass=new ForgotPass(this,input,output);
		getContentPane().add(forgotpass);
		forgotpass.setVisible(false);
		
		newuser=new NewUser(this,input,output);
		getContentPane().add(newuser);
		newuser.setVisible(false);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/Login2.png"));
		lblNewLabel.setBounds(250, 12, 100, 100);
		panel.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBounds(92, 124, 414, 37);
		textPane.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(textPane);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/User5.png"));
		lblNewLabel_1.setBounds(49, 124, 37, 37);
		panel.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(92, 173, 414, 37);
		password.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(password);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/pwd3.png"));
		label.setBounds(49, 173, 37, 37);
		panel.add(label);
		
		btnNewButton_1 = new JButton("Forgot Password?");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
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
				panel.setVisible(false);
				forgotpass.setVisible(true);
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(87, 310, 171, 25);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				btnNewButton_1.setForeground(new Color(0, 100, 0));
			}
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton_1.setForeground(new Color(77, 153, 0));
			}
		});
		btnNewButton_1.setForeground(new Color(0, 100, 0));
		btnNewButton_1.setBackground(new Color(0, 255, 127));
		btnNewButton_1.setFocusable(false);
		panel.add(btnNewButton_1);
		
		thisframe=this;
		btnNewUser = new JButton("New User");
		btnNewUser.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					output.writeObject("Check");
					output.reset();
					Object obj=input.readObject();
					if(obj instanceof Boolean)
					{
						n=(boolean)obj;
						if(!n)
						{
							JOptionPane.showMessageDialog(thisframe,"Addmission Process is Closed");
							return;
						}
					}
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (Exception e1) 
				{	
					e1.printStackTrace();
				}
				panel.setVisible(false);
				newuser.setVisible(true);
			}
		});
		btnNewUser.setFocusable(false);
		btnNewUser.setBorder(null);
		btnNewUser.setBounds(413, 310, 94, 15);
		btnNewUser.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				btnNewUser.setForeground(new Color(0, 100, 0));
			}
			public void mouseEntered(MouseEvent e) {
				
				btnNewUser.setForeground(new Color(77, 153, 0));
			}
		});
		btnNewUser.setForeground(new Color(0, 100, 0));
		btnNewUser.setBackground(new Color(0, 255, 127));
		panel.add(btnNewUser);
		
		label3=new JLabel(icon2);
		label3.setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane.add(label3);
		
		lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setForeground(new Color(178, 34, 34));
		lblPleaseFillAll.setBounds(223, 234, 161, 15);
		lblPleaseFillAll.setVisible(false);	
		panel.add(lblPleaseFillAll);
		
		lblPleaseFillValid = new JLabel("Please fill valid credentials");
		lblPleaseFillValid.setForeground(new Color(178, 34, 34));
		lblPleaseFillValid.setBounds(199, 234, 196, 15);
		lblPleaseFillValid.setVisible(false);		
		panel.add(lblPleaseFillValid);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(92, 261, 414, 37);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 17));
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
				
				if(textPane.getText().isEmpty() || password.getText().isEmpty())
				{
					   lblPleaseFillAll.setVisible(true);				   
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseFillAll.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
				username=textPane.getText();
				pass=password.getText();
				textPane.setText("");
				password.setText("");
				try 
				{
					customer.name=username;
					customer.password=pass;
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
					try {
						CustomerFrame frame = new CustomerFrame(thisframe,input,output);
						frame.setVisible(true);
						thisframe.setVisible(false);
						} catch (Exception e)
					    {
							e.printStackTrace();
						}
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
					return;
				}
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				
				btnNewButton.setBackground(new Color(0,128,0));
				btnNewButton.setForeground(Color.white);
			}
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton.setBackground(new Color(50,205,50));
				btnNewButton.setForeground(Color.white);
			}
		});
		panel.add(btnNewButton);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton.setBorder(emptyBorder);
	}
}
