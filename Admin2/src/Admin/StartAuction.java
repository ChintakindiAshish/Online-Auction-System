package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CommonClasses.ServerCollection;

public class StartAuction extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JButton btnStartAuction;
	public Start start;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public boolean status,flag=false;
	public AuctionRunning auctionrunning;
	public Sell sell;
	public StartAuction thisframe;
	
	public StartAuction(ObjectInputStream input,ObjectOutputStream output,AuctionRunning parent,Sell sell) throws Exception{

		this.input=input;
		this.output=output;
		this.auctionrunning=parent;
		this.sell=sell;
		
		thisframe=this;
		setLayout(null);
		setBounds(309,180,980,564);
		
		start=new Start(input,output);
		add(start);
		start.setVisible(false);
		
		btnStartAuction = new JButton("Start Auction");
		btnStartAuction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!ServerCollection.ItemList.isEmpty())
				{
					btnStartAuction.setVisible(false);
					start.setVisible(true);
					try 
					{
						start.StartTheAuction(sell,auctionrunning,thisframe);
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
					} catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"No Items in System,Please add Items First");
				}
			}
		});
		btnStartAuction.setBackground(new Color(0, 255, 127));
		btnStartAuction.setForeground(new Color(0, 139, 139));
		btnStartAuction.setBounds(355, 230, 266, 57);
		btnStartAuction.setFont(new Font("Serif", Font.BOLD, 30));
		add(btnStartAuction);
	}
	public void refresh() throws Exception
	{
		synchronized(output) 
		{
			output.writeObject("AuctionStatus");
			output.reset();
			status=(boolean)input.readObject();
		}
		if(status && !flag)
		{
			start.ShowTheAuction(sell,auctionrunning,thisframe);
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
			btnStartAuction.setVisible(false);
			start.setVisible(true);
			flag=true;
		}
	}
}
