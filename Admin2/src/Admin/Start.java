package Admin;

import java.awt.Color;
import java.awt.Font;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Start extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public ImageIcon image;
	public Start thisobject;
	public JLabel lblFillTheFollowing,lblName,lblcost,lblphoto,lblBidInfoIn,lblname,lblname2,label,label_1;
	public JButton btnSubmit;
	public int cost;
	public JLabel lblItemCost;
	public JLabel lblTimer;
	public JLabel lblNewLabel;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	
	public Start(ObjectInputStream input,ObjectOutputStream output) throws Exception {
		
		this.input=input;
		this.output=output;
		
		setLayout(null);
		setBounds(0,0,980,564);
		
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
        j1.setBounds(225,341,513,161);
		add(j1);
		
		
		lblFillTheFollowing = new JLabel("Item for Auction");
		lblFillTheFollowing.setBounds(388, 23, 225, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblName = new JLabel();
		lblName.setBounds(197, 86, 200, 200);
		lblName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblName.setBorder(new LineBorder(new Color(0, 128, 255),1));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName);
		
		lblname2 = new JLabel("Item Name");
		lblname2.setFont(new Font("Serif", Font.PLAIN, 20));
		lblname2.setBounds(502, 163, 153, 25);
		add(lblname2);
		
		lblcost = new JLabel("1000");
		lblcost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblcost.setBounds(692, 231, 225, 25);
		add(lblcost);
		
		lblname = new JLabel("Bed");
		lblname.setFont(new Font("Serif", Font.PLAIN, 20));
		lblname.setBounds(692, 163, 225, 25);
		add(lblname);
		
		lblItemCost = new JLabel("Reserved Price");
		lblItemCost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblItemCost.setBounds(502, 231, 153, 25);
		add(lblItemCost);
		
		lblBidInfoIn = new JLabel("Bid Info. in descending order of Bid Price.");
		lblBidInfoIn.setForeground(Color.BLUE);
		lblBidInfoIn.setBounds(336, 324, 311, 15);
		add(lblBidInfoIn);
		
		lblTimer = new JLabel("Time -");
		lblTimer.setForeground(new Color(255, 0, 0));
		lblTimer.setBounds(708, 8, 122, 56);
		lblTimer.setFont(new Font("Serif", Font.BOLD, 30));
		add(lblTimer);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(842, 8, 126, 56);
		lblNewLabel.setText("00 : 31");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 30));
		add(lblNewLabel);
		
		label = new JLabel("Item ID");
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		label.setBounds(502, 97, 111, 25);
		add(label);
		
		label_1 = new JLabel("1");
		label_1.setBounds(692, 93, 105, 29);
		label_1.setFont(new Font("Serif", Font.PLAIN, 20));
		add(label_1);
	}
	public void StartTheAuction(Sell sell,AuctionRunning parent,StartAuction startAuction) throws Exception
	{
		output.writeObject("AuctionStarted");
		output.reset();
		
		TimerThread t=new TimerThread(input,output,this,sell,parent,startAuction);
		Thread t2=new Thread(t);
		t2.start();
	}
	public void ShowTheAuction(Sell sell,AuctionRunning parent,StartAuction startAuction) throws Exception
	{
		TimerThread t=new TimerThread(input,output,this,sell,parent,startAuction);
		Thread t2=new Thread(t);
		t2.start();
	}
}
