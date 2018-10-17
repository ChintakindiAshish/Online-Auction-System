package Admin;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import CommonClasses.*;
import Login.AdminLogin;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    public JLabel label_1,lblYouCanSell,lblWillStartAt,label,lblAuctionBasket,lblPvtltd,label3,lblCustomer;
	public ImageIcon icon,icon2,icon3,icon4;
	public JPanel panel,panel_1,panel_2,panel_3;
	public JButton btnStartAuction,btnNewButton,btnEnterCutoffOf,button2,button_1,btnLogout,btnShowItems;
	public CustomerInfo customer;
	public Sell sell;
	public CustomerList customerlist;
	public Result result;
	public ShowItems showItems;
	public AllCustomers customers;
	public Object obj;
	public StartAuction startAuction;
	public AuctionRunning auctionrunning;
	public boolean status;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public AdminFrame(AdminLogin parent,ObjectInputStream input,ObjectOutputStream output) throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		icon=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/index.png");
		setIconImage(icon.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/Frame2.jpg");
		
		ServerCollection.ItemList=(Vector<Item>) input.readObject();
		
		lblCustomer = new JLabel("Admin Portal");
		lblCustomer.setForeground(new Color(224, 255, 255));
		lblCustomer.setBounds(712,127,187,32);
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
		
		auctionrunning=new AuctionRunning();
		contentPane.add(auctionrunning);
		auctionrunning.setVisible(false);
		
		sell=new Sell(input,output);
		contentPane.add(sell);
		sell.setVisible(false);
		sell.add(auctionrunning);
		
		showItems=new ShowItems(input,output);
		contentPane.add(showItems);
		showItems.setVisible(false);

		startAuction=new StartAuction(input,output,auctionrunning,sell);
		contentPane.add(startAuction);
		startAuction.setVisible(false);
		
		customerlist=new CustomerList();
		contentPane.add(customerlist);
		customerlist.setVisible(false);
		
		result=new Result();
		contentPane.add(result);
		result.setVisible(false);
		
		icon4=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/a1.jpg");
		panel_2.setLayout(null);
		label_1 = new JLabel(icon4);
		label_1.setBounds(70, 23, 843, 517);
		panel_2.add(label_1);
		
		lblYouCanSell = new JLabel("Upload the images of Items for Auction, Then Start the Auction.");
		lblYouCanSell.setForeground(new Color(128,0,0));
		lblYouCanSell.setBounds(40, 50, 650, 25);
		lblYouCanSell.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.add(lblYouCanSell);
		
		lblWillStartAt = new JLabel("Customers will Bid them in the Auction.");
		lblWillStartAt.setForeground(new Color(128, 0, 0));
		lblWillStartAt.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWillStartAt.setBounds(40, 75, 400, 25);
		label_1.add(lblWillStartAt);
		
		icon3=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages2/logo.png");
		label=new JLabel(icon3);
		label.setBounds(85, 3, 140, 100);
		contentPane.setLayout(null);
		contentPane.add(label);
		
		btnNewButton = new JButton("Add Items");
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
						sell.setVisible(true);
						startAuction.setVisible(false);
						customerlist.setVisible(false);
						result.setVisible(false);
						showItems.setVisible(false);
						synchronized(output) 
						{
							try 
							{
								output.writeObject("AuctionStatus");
								output.reset();
								status=(boolean)input.readObject();
								if(status)
								{
									auctionrunning.setVisible(true);
									sell.lblcost.setVisible(false);
									sell.lblFillTheFollowing.setVisible(false);
									sell.lblHeight.setVisible(false);
									sell.lblImage.setVisible(false);
									sell.lblName.setVisible(false);
									sell.lblOwnerName.setVisible(false);
									sell.btnAdd.setVisible(false);
									sell.btnNewButton.setVisible(false);
									sell.textPane.setVisible(false);
									sell.textPane2.setVisible(false);
									sell.textPane_1.setVisible(false);
									startAuction.btnStartAuction.setVisible(false);
								}
							} catch (Exception e1) 
							{
								e1.printStackTrace();
							}
						}
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
		
		btnEnterCutoffOf = new JButton("Show Sold Items");
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
				sell.setVisible(false);
				startAuction.setVisible(false);
				customerlist.setVisible(false);
				result.setVisible(true);
				showItems.setVisible(false);
				try 
				{
					synchronized(output) {
					output.writeObject("SoldItemList");
					output.reset();
					ServerCollection.SoldItemList=(Vector<Item>)input.readObject();}
					result.refresh();
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
		btnEnterCutoffOf.setBounds(0, 285, 300, 60);
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
		
		button2 = new JButton("Show Customers");
		button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
	
				try {
					output.reset();
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e2) {
					
					e2.printStackTrace();
				}
				
				panel_2.setVisible(false);
				sell.setVisible(false);
				startAuction.setVisible(false);
				customerlist.setVisible(true);
				result.setVisible(false);
				showItems.setVisible(false);
				try 
				{
					synchronized(output) {
					output.writeObject("CustomerDetails");
					output.reset();
					customers=(AllCustomers)input.readObject();}
					ServerCollection.customers=customers.info;
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
				customerlist.refresh();
			}
		});
		button2.setFont(new Font("Dialog", Font.BOLD, 23));
		button2.setFocusable(false);
		button2.setBounds(0, 228, 300, 60);
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
				sell.setVisible(false);
				startAuction.setVisible(false);
				customerlist.setVisible(false);
				result.setVisible(false);
				showItems.setVisible(false);
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
		
		btnStartAuction = new JButton("Start Auction");
		btnStartAuction.setBounds(0, 171, 300, 60);
		panel_1.add(btnStartAuction);
		btnStartAuction.addActionListener(new ActionListener() 
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
				sell.setVisible(false);
				startAuction.setVisible(true);
				customerlist.setVisible(false);
				result.setVisible(false);
				showItems.setVisible(false);
				try 
				{
					startAuction.refresh();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnStartAuction.setFont(new Font("Dialog", Font.BOLD, 23));
		btnStartAuction.setFocusable(false);
		btnStartAuction.setBackground(new Color(0, 0, 77));
		btnStartAuction.setForeground(Color.white);
		btnStartAuction.setBorder(new LineBorder(new Color(0, 128, 255),3));
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				
				setVisible(false);
				AdminLogin frame;
				try 
				{
					frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
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
		
		btnShowItems = new JButton("Show Items");
		btnShowItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					output.reset();
				} catch (Exception e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				
				panel_2.setVisible(false);
				sell.setVisible(false);
				startAuction.setVisible(false);
				customerlist.setVisible(false);
				result.setVisible(false);
				showItems.setVisible(true);
				try 
				{
					synchronized(output) {
					output.writeObject("ItemList");
					output.reset();
					ServerCollection.ItemList=(Vector<Item>)input.readObject();}
					showItems.refresh();
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		btnShowItems.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				btnShowItems.setBackground(new Color(0, 0, 77));
				btnShowItems.setForeground(Color.white);
			}
			

			public void mouseEntered(MouseEvent e) {
				
				btnShowItems.setBackground(new Color(0, 115, 230));
				btnShowItems.setForeground(Color.white);
			}
		});
		btnShowItems.setBounds(0, 114, 300, 60);
		panel_1.add(btnShowItems);
		btnShowItems.setForeground(Color.WHITE);
		btnShowItems.setFont(new Font("Dialog", Font.BOLD, 23));
		btnShowItems.setFocusable(false);
		btnShowItems.setBorder(new LineBorder(new Color(0, 128, 255),3));
		btnShowItems.setBackground(new Color(0, 0, 77));
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
		btnStartAuction.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent e) {
				
				btnStartAuction.setBackground(new Color(0, 0, 77));
				btnStartAuction.setForeground(Color.white);
			}
			public void mouseEntered(MouseEvent e) {
				
				btnStartAuction.setBackground(new Color(0, 115, 230));
				btnStartAuction.setForeground(Color.white);
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
