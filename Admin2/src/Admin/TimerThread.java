package Admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import CommonClasses.*;

public class TimerThread implements Runnable,Comparator<PriorityBid>{

	public Iterator<ImageIcon> iterate;
	public ImageIcon image,icon;
	public Start parent;
	public String data;
	public char letter;
	public int i,i2,size,ID=-1,rows;
	public String store;
	public SendCurrentItem current=new SendCurrentItem();
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public PriorityQueue<PriorityBid> pq;
	public PriorityBid pb;
	public Item item;
	public Vector<SendBidStatus> v;
	public SendBidStatus bid;
	public byte Barray[]=new byte[120000];   //120 KBytes
	public FileOutputStream fos;
	public boolean flag=false;
	public Sell sell;
	public AuctionRunning parent2;
	public StartAuction startAuction;
	public Vector<Item> ItemList;
	public Iterator<Item> it;
	
	public TimerThread(ObjectInputStream input,ObjectOutputStream output,Start parent,Sell sell,AuctionRunning parent2,StartAuction startAuction)
	{
		this.parent=parent;
		this.input=input;
		this.output=output;
		this.sell=sell;
		this.parent2=parent2;
		this.startAuction=startAuction;
		pq=new PriorityQueue<PriorityBid>(100,this);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		Timer timer = new Timer();
		try 
		{
			synchronized(output) {
			output.writeObject("ItemList");
			output.reset();
			ServerCollection.ItemList=(Vector<Item>)input.readObject();
			Iterator<Item> i=ServerCollection.ItemList.iterator();
			ServerCollection.items.clear();
			ServerCollection.Images.clear();
			while(i.hasNext())
			{
				item=i.next();
				Barray=item.b;
				File file=new File("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
				fos=new FileOutputStream(file);
				fos.write(Barray);
				image=new ImageIcon("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
				ServerCollection.items.put(item.ID,item);
				ServerCollection.Images.put(item.ID,image);
			}
			timer.schedule(new GetStatus(), 0, 1000);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	class GetStatus extends TimerTask {
	    @SuppressWarnings("unchecked")
		public void run() {
	       
	    	if(flag)
	    		return;
	    	try 
	    	{
	    		synchronized(output) 
				{
	    			output.writeObject("Status");
					output.reset();
					current=(SendCurrentItem)input.readObject();
					v=(Vector<SendBidStatus>) input.readObject();
					if(current.ID==0)
					{
						flag=true;
						parent.setVisible(false);
						parent2.setVisible(false);
						sell.lblcost.setVisible(true);
						sell.lblFillTheFollowing.setVisible(true);
						sell.lblHeight.setVisible(true);
						sell.lblImage.setVisible(true);
						sell.lblName.setVisible(true);
						sell.lblOwnerName.setVisible(true);
						sell.btnAdd.setVisible(true);
						sell.btnNewButton.setVisible(true);
						sell.textPane.setVisible(true);
						sell.textPane2.setVisible(true);
						sell.textPane_1.setVisible(true);
						startAuction.btnStartAuction.setVisible(true);
						output.writeObject("ItemList");
						output.reset();
						ItemList=(Vector<Item>)input.readObject();
						ServerCollection.ItemList=ItemList;
						ServerCollection.items.clear();
						ServerCollection.Images.clear();
						it=ItemList.iterator();
						if(it==null)
							return;
						while(it.hasNext())
						{
							item=it.next();
							Barray=item.b;
							File file=new File("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
							fos=new FileOutputStream(file);
							fos.write(Barray);
							image=new ImageIcon("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
							ServerCollection.items.put(item.ID,item);
							ServerCollection.Images.put(item.ID,image);
						}
						cancel();
						return;
					}
				}
				for(Integer n:ServerCollection.items.keySet())
				{
					if(n==current.ID)
					{
						icon=ServerCollection.Images.get(n);
						item=ServerCollection.items.get(n);
						parent.lblName.setIcon(icon);
						parent.lblname.setText(item.name);
						parent.label_1.setText(Integer.toString(item.ID));
						parent.lblcost.setText(String.valueOf(item.Reserve_Price));
						parent.lblNewLabel.setText(current.Time);
						break;
					}
				}
				rows = parent.model.getRowCount();
				for(int i = rows - 1; i >=0; i--)
				{
					parent.model.removeRow(i);
				}
				Iterator<SendBidStatus> iterate=v.iterator();
				size=v.size();
				pq.clear();
				for ( int row = 0; row < size; row++ )
				{
					bid=iterate.next();
			        PriorityBid pc=new PriorityBid(bid,bid.Bid_Price);
			        pq.add(pc);
				}
				while(!pq.isEmpty())
				{
			    	pb=pq.poll();
					parent.model.addRow(new Object[]{pb.bid.Customer_Name,pb.bid.Bid_Price});
				}	
			} catch (Exception e1) 
	    	{
				e1.printStackTrace();
			}
	    }
	}
	@Override
	public int compare(PriorityBid p1, PriorityBid p2) {
		if(p1.priority>p2.priority)
			return -1;
		else if(p1.priority<p2.priority)
			return 1;
		return -1;
	}
}
