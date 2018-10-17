package Customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import CommonClasses.*;
import Login.Login;

import javax.swing.SwingConstants;

public class CustomerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public JLabel label_1,lblYouCanSell,label_2,label,lblAuctionBasket,lblPvtltd,label3,lblCustomer;
	public ImageIcon icon,icon2,icon3,icon4;
	public JPanel panel,panel_1,panel_2,panel_3;
	public JButton button,btnNewButton,btnEnterCutoffOf,button2,button_1,btnLogout;
	public ShowDetails showdetails;
	public CustomerInfo customer;
	public ShowItems showItems;
	public Purchase purchase;
	public MyOrders myorders;
	private JButton button_2;
	public Result result;
	public Object obj;
	private NotStarted notStarted;
	public boolean status;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public CustomerFrame(Login parent,ObjectInputStream input,ObjectOutputStream output) throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		icon=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/index.png");
		setIconImage(icon.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for(int i=0;i<2;i++)
		{
			obj=input.readObject();
			if(obj instanceof CustomerInfo)
			{
				customer=(CustomerInfo)obj;
			}
			else
			{
				CustomerCollection.ItemList=(Vector<Item>)obj;
			}
		}
		
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/Frame2.jpg");
		
		lblCustomer = new JLabel("Customer Portal");
		lblCustomer.setForeground(new Color(224, 255, 255));
		lblCustomer.setBounds(689,127,187,32);
		lblCustomer.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblCustomer);
		
		panel = new JPanel();
		panel.setBounds(0, 105, 1356, 10);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 115, 300, 758);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new LineBorder(new Color(0, 128, 255),3));
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(0, 60, 90));
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(312, 180, 977, 564);
		contentPane.add(panel_2);
		
		notStarted=new NotStarted();
		contentPane.add(notStarted);
		notStarted.setVisible(false);
		
		showdetails=new ShowDetails(input,output,customer);
		contentPane.add(showdetails);
		showdetails.setVisible(false);
		
		showItems=new ShowItems(input,output);
		contentPane.add(showItems);
		showItems.setVisible(false);
		
		purchase=new Purchase(input,output,customer,notStarted);
		contentPane.add(purchase);
		purchase.setVisible(false);
		
		myorders=new MyOrders(customer);
		contentPane.add(myorders);
		myorders.setVisible(false);
		
		result=new Result();
		contentPane.add(result);
		result.setVisible(false);
		
		icon4=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/a1.jpg");
		panel_2.setLayout(null);
		label_1 = new JLabel(icon4);
		label_1.setBounds(70, 23, 843, 517);
		panel_2.add(label_1);
		
		lblYouCanSell = new JLabel("You can sell your item here by uploading images, expected price of item.");
		lblYouCanSell.setForeground(new Color(128,0,0));
		lblYouCanSell.setBounds(40, 50, 600, 25);
		lblYouCanSell.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.add(lblYouCanSell);
		
		label_2 = new JLabel("Auction will start at 1 p.m. every saturday.");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_2.setBounds(40, 75, 400, 25);
		label_1.add(label_2);
		
		icon3=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/logo.png");
		label=new JLabel(icon3);
		label.setBounds(85, 3, 140, 100);
		contentPane.setLayout(null);
		contentPane.add(label);
		
		btnNewButton = new JButton("Personal Details");
		btnNewButton.setBounds(0, 57, 300, 60);btnNewButton.setFont(new Font("Dialog", Font.BOLD, 23));
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) { 
						
						try {
							output.reset();
						} catch (Exception e1) 
						{	
							JOptionPane.showMessageDialog(null,"Server Down");
							return;
						}

						panel_2.setVisible(false);
						showdetails.setVisible(true);
						showItems.setVisible(false);
						myorders.setVisible(false);
						purchase.setVisible(false);
						result.setVisible(false);
						notStarted.setVisible(false);
					} 
				});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 0, 77));
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBorder(new LineBorder(new Color(0, 128, 255),3));
		btnNewButton.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				btnNewButton.setBackground(new Color(0, 0, 77));
				btnNewButton.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				btnNewButton.setBackground(new Color(0, 115, 230));
				btnNewButton.setForeground(Color.white);
			}
		});
		panel_1.add(btnNewButton);
		
		btnEnterCutoffOf = new JButton("My Orders");
		btnEnterCutoffOf.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}

				panel_2.setVisible(false);
				showdetails.setVisible(false);
				showItems.setVisible(false);
				myorders.setVisible(true);
				purchase.setVisible(false);
				result.setVisible(false);
				notStarted.setVisible(false);
				try 
				{
					synchronized(output)
		    		{
						output.writeObject("MyOrders");
						output.reset();
						CustomerCollection.MyItemList=(Vector<Item>)input.readObject();
						myorders.refresh();
		    		}
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		btnEnterCutoffOf.setFont(new Font("Dialog", Font.BOLD, 23));
		btnEnterCutoffOf.setFocusable(false);
		btnEnterCutoffOf.setBackground(new Color(0, 0, 77));
		btnEnterCutoffOf.setForeground(Color.white);
		btnEnterCutoffOf.setBounds(0, 228, 300, 60);
		btnEnterCutoffOf.setBorder(new LineBorder(new Color(0, 128, 255),3));
		btnEnterCutoffOf.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				btnEnterCutoffOf.setBackground(new Color(0, 0, 77));
				btnEnterCutoffOf.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				btnEnterCutoffOf.setBackground(new Color(0, 115, 230));
				btnEnterCutoffOf.setForeground(Color.white);
			}
		});
		panel_1.add(btnEnterCutoffOf);
		
		button2 = new JButton("Show Items");
		button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
	
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				showItems.setVisible(true);
				myorders.setVisible(false);
				purchase.setVisible(false);
				result.setVisible(false);
				notStarted.setVisible(false);
				try 
				{
					synchronized(output)
		    		{
						output.writeObject("ItemList");
						output.reset();
						CustomerCollection.ItemList=(Vector<Item>)input.readObject();
						showItems.refresh();
		    		}
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		button2.setFont(new Font("Dialog", Font.BOLD, 23));
		button2.setFocusable(false);
		button2.setBounds(0, 171, 300, 60);
		button2.setBackground(new Color(0, 0, 77));
		button2.setForeground(Color.white);
		button2.setBorder(new LineBorder(new Color(0, 128, 255),3));
		button2.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				button2.setBackground(new Color(0, 0, 77));
				button2.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				button2.setBackground(new Color(0, 115, 230));
				button2.setForeground(Color.white);
			}
		});
		panel_1.add(button2);
		
		button_1 = new JButton("Home");
		button_1.setFont(new Font("Dialog", Font.BOLD, 23));
		button_1.setBackground(new Color(0, 0, 77));
		button_1.setForeground(Color.white);
		button_1.setFocusable(false);
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
			
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				
				panel_2.setVisible(true);
				showdetails.setVisible(false);
				showItems.setVisible(false);
				myorders.setVisible(false);
				purchase.setVisible(false);
				result.setVisible(false);
				notStarted.setVisible(false);
			}
		});
		button_1.setBounds(0, 0, 300, 60);
		button_1.setBorder(new LineBorder(new Color(0, 128, 255),3));
		button_1.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				button_1.setBackground(new Color(0, 0, 77));
				button_1.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				button_1.setBackground(new Color(51, 51, 255));
				button_1.setForeground(Color.white);
			}
		});
		panel_1.add(button_1);
		
		button = new JButton("Purchase");
		button.setBounds(0, 114, 300, 60);
		panel_1.add(button);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
	
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				showItems.setVisible(false);
				myorders.setVisible(false);
				purchase.setVisible(true);
				result.setVisible(false);
				try 
				{
					synchronized(output)
		    		{
						output.writeObject("AuctionStatus");
						output.reset();
						status=(boolean)input.readObject();
						if(!status)
						{
							purchase.setVisible(false);
							notStarted.setVisible(true);
							return;
						}
						purchase.setVisible(true);
						notStarted.setVisible(false);
		    		}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				try 
				{
					purchase.refresh();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 23));
		button.setFocusable(false);
		button.setBackground(new Color(0, 0, 77));
		button.setForeground(Color.white);
		button.setBorder(new LineBorder(new Color(0, 128, 255),3));
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					output.reset();
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e2) 
				{
					e2.printStackTrace();
				}
				setVisible(false);
			    Login frame;
				try 
				{
					frame = new Login();
					frame.setVisible(true);
				} catch (IOException e3) 
				{
					e3.printStackTrace();
				}
			    dispose();
			}
		});
		btnLogout.setBounds(0, 342, 300, 60);
		panel_1.add(btnLogout);
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBackground(new Color(0, 0, 77));
		btnLogout.setForeground(Color.white);
		btnLogout.setBorder(new LineBorder(new Color(0, 128, 255),3));
		
		button_2 = new JButton("Show Sold Items");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				showItems.setVisible(false);
				myorders.setVisible(false);
				purchase.setVisible(false);
				result.setVisible(true);
				notStarted.setVisible(false);
				try 
				{
					synchronized(output)
		    		{
						output.writeObject("SoldItemList");
						output.reset();
						CustomerCollection.SoldItemList=(Vector<Item>)input.readObject();
						result.refresh();
		    		}
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		button_2.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				button_2.setBackground(new Color(0, 0, 77));
				button_2.setForeground(Color.white);
			}
			public void mouseEntered(MouseEvent e) {
				
				button_2.setBackground(new Color(0, 115, 230));
				button_2.setForeground(Color.white);
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Dialog", Font.BOLD, 23));
		button_2.setFocusable(false);
		button_2.setBorder(new LineBorder(new Color(0, 128, 255),3));
		button_2.setBackground(new Color(0, 0, 77));
		button_2.setBounds(0, 285, 300, 60);
		panel_1.add(button_2);
		btnLogout.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				btnLogout.setBackground(new Color(0, 0, 77));
				btnLogout.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				btnLogout.setBackground(new Color(0, 115, 230));
				btnLogout.setForeground(Color.white);
			}
		});
		button.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				button.setBackground(new Color(0, 0, 77));
				button.setForeground(Color.white);
			}
			public void mouseEntered(MouseEvent e) {
				
				button.setBackground(new Color(0, 115, 230));
				button.setForeground(Color.white);
			}
		});
		
		lblAuctionBasket = new JLabel("Auction Basket");
		lblAuctionBasket.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuctionBasket.setForeground(Color.YELLOW);
		lblAuctionBasket.setBounds(590, 25, 340, 35);
		lblAuctionBasket.setFont(new Font("Dialog", Font.BOLD, 35));
		contentPane.add(lblAuctionBasket);
		
		lblPvtltd = new JLabel("Pvt.Ltd.");
		lblPvtltd.setForeground(Color.YELLOW);
		lblPvtltd.setBounds(858, 58, 70, 15);
		contentPane.add(lblPvtltd);
		
		label3=new JLabel(icon2);
		label3.setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane.add(label3);
	}
}
