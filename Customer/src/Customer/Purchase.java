package Customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import CommonClasses.*;

public class Purchase extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public String item,item2,item3;
	public Purchase thisobject;
	public JLabel lblFillTheFollowing,lblName,lblcost,lblphoto,lblThisChoiceIs2,label_1,label_2,label_3,label,lblYourCost,lblItemCost,lblname,lblNewLabel,lblname2;
	public JButton btnAdd,btnSubmit;
	public JTextPane textPane2;
	public int cost,ID,highest=0;
	public boolean status,flag=false;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public SendCurrentItem current;
	public Purchase purchase;
	public SendCost sendcost=new SendCost();
	public NotStarted notStarted;
	public Vector<Item> ItemList;
	public Iterator<Item> it;
	public Item sentitem;
	public FileOutputStream fos;
	public ImageIcon image;
	public byte Barray[]=new byte[120000];   //120 KBytes
	
	public Purchase(ObjectInputStream input,ObjectOutputStream output,CustomerInfo customer,NotStarted notStarted) throws Exception {
		
		this.input=input;
		this.output=output;
		this.notStarted=notStarted;
		purchase=this;
		
		setLayout(null);
		setBounds(309,180,980,564);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("Bidder Name");
        model.addColumn("Bid Price ");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        j1=new JScrollPane(table);
        j1.setBounds(225,364,513,161);
		add(j1);
		
		
		lblFillTheFollowing = new JLabel("Enter your cost for this Item");
		lblFillTheFollowing.setBounds(300, 23, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblName = new JLabel();
		lblName.setBounds(124, 83, 200, 200);
		lblName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblName.setBorder(new LineBorder(new Color(0, 128, 255),1));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName);
		
		lblname = new JLabel("Item Name");
		lblname.setFont(new Font("Serif", Font.PLAIN, 20));
		lblname.setBounds(406, 83, 153, 25);
		add(lblname);
		
		lblcost = new JLabel();
		lblcost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblcost.setBounds(596, 149, 153, 25);
		add(lblcost);
		
		lblname2 = new JLabel();
		lblname2.setFont(new Font("Serif", Font.PLAIN, 20));
		lblname2.setBounds(596, 83, 153, 25);
		add(lblname2);
		
		lblYourCost = new JLabel("Your Cost");
		lblYourCost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblYourCost.setBounds(406, 215, 153, 25);
		add(lblYourCost);
		
		lblItemCost = new JLabel("Reserved Cost");
		lblItemCost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblItemCost.setBounds(406, 149, 153, 25);
		add(lblItemCost);
		
		lblThisChoiceIs2 = new JLabel("Please enter valid amount");
		lblThisChoiceIs2.setForeground(Color.RED);
		lblThisChoiceIs2.setBounds(596, 251, 211, 15);
		add(lblThisChoiceIs2);
		lblThisChoiceIs2.setVisible(false);
		
		textPane2 = new JTextPane();
		textPane2.setBounds(596, 215, 182, 36);
		textPane2.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane2.setBackground(new Color(179, 217, 255));
		add(textPane2);
		
		btnAdd = new JButton("Add Cost");
		btnAdd.setFocusable(false);
		btnAdd.setBounds(416, 281, 143, 43);
		btnAdd.setFont(new Font("Arial",Font.PLAIN,20));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				
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
			
				if(textPane2.getText().isEmpty())
				   {
						lblThisChoiceIs2.setVisible(true);
							Timer t=new Timer(2000,new ActionListener() 
							{
								public void actionPerformed(ActionEvent e)
								{
									lblThisChoiceIs2.setVisible(false);
								}
							});
							t.setRepeats(false);
							t.start();
							return;
					}
				try 
				{
					cost=Integer.parseInt(textPane2.getText());
					if(cost <= Integer.parseInt(lblcost.getText()) || cost <=highest)
					{
						lblThisChoiceIs2.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
								lblThisChoiceIs2.setVisible(false);
							}
						});
						t.setRepeats(false);
						t.start();
						return;
					}
				}
				catch(NumberFormatException e1)
				{
					lblThisChoiceIs2.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblThisChoiceIs2.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
					return;
				}
				
				textPane2.setText("");
				JOptionPane.showMessageDialog(null,"Bid added Successfully");
				sendcost.Consumer_Name=customer.name;
				sendcost.cost=cost;
				sendcost.ID=ID;
				sendcost.Consumer_ID=customer.ID;
				try 
				{
					synchronized(output)
		    		{
						output.writeObject(sendcost);
						output.reset();
		    		}
				} catch (IOException e) 
				{
					e.printStackTrace();
				}	
			}
		});
		add(btnAdd);
		
		JLabel lblBidInfoIn = new JLabel("Bid Info. in descending order of Bid Price.");
		lblBidInfoIn.setForeground(Color.BLUE);
		lblBidInfoIn.setBounds(336, 347, 311, 15);
		add(lblBidInfoIn);
		
		label_1 = new JLabel("Time -");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Serif", Font.BOLD, 30));
		label_1.setBounds(708, 12, 122, 56);
		add(label_1);
		
		label_2 = new JLabel();
		label_2.setText("00 : 30");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Serif", Font.BOLD, 30));
		label_2.setBounds(842, 12, 126, 56);
		add(label_2);
		
		lblNewLabel = new JLabel("( Cost >  Reserved Cost,");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setBounds(403, 236, 175, 15);
		add(lblNewLabel);
		
		JLabel lblBiddedCost = new JLabel("Bidded Cost )");
		lblBiddedCost.setForeground(new Color(139, 0, 0));
		lblBiddedCost.setBounds(467, 251, 94, 15);
		add(lblBiddedCost);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(124, 295, 70, 25);
		lblItemId.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblItemId);
		
		label_3 = new JLabel("");
		label_3.setBounds(225, 295, 70, 29);
		add(label_3);
	}
	@SuppressWarnings("unchecked")
	public void refresh() throws Exception
	{
		output.writeObject("AuctionStatus");
		output.reset();
		status=(boolean)input.readObject();
		if(status && !flag)
		{
			synchronized(output) 
			{
			output.writeObject("ItemList");
			output.reset();
			ItemList=(Vector<Item>)input.readObject();
			CustomerCollection.ItemList=ItemList;
			CustomerCollection.items.clear();
			CustomerCollection.Images.clear();
			it=ItemList.iterator();
			if(it==null)
				return;
			while(it.hasNext())
			{
				sentitem=it.next();
				Barray=sentitem.b;
				File file=new File("/home/ashish/eclipse-workspace/Customer/CustomerImages/"+String.valueOf(sentitem.ID)+".txt");
				fos=new FileOutputStream(file);
				fos.write(Barray);
				image=new ImageIcon("/home/ashish/eclipse-workspace/Customer/CustomerImages/"+String.valueOf(sentitem.ID)+".txt");
				CustomerCollection.items.put(sentitem.ID,sentitem);
				CustomerCollection.Images.put(sentitem.ID,image);
			}
			Check t=new Check(input,output,purchase,notStarted);
			Thread t1=new Thread(t);
			t1.start();
			flag=true;
			}
		}
	}
}
