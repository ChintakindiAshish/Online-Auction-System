package Customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;

import CommonClasses.*;

class Check implements Runnable,Comparator<PriorityBid>
{
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public SendCurrentItem current;
	public Purchase purchase;
	public Item item;
	public ImageIcon icon;
	public Vector<SendBidStatus> v;
	public int rows,size,highest,CheckID=0;
	public SendBidStatus bid;
	public PriorityQueue<PriorityBid> pq;
	public PriorityBid pb;
	public boolean flag,flag2=false;
	public NotStarted notStarted;
	
	public Check(ObjectInputStream input,ObjectOutputStream output,Purchase parent,NotStarted notStarted)
	{
		this.input=input;
		this.output=output;
		this.purchase=parent;
		this.notStarted=notStarted;
		pq=new PriorityQueue<PriorityBid>(100,this);
	}
	
	@Override
	public void run() {
		
		Timer timer = new Timer();
		timer.schedule(new GetStatus(), 0, 1000);
	}
	class GetStatus extends TimerTask {
    @SuppressWarnings("unchecked")
	public void run()
    {
    	if(flag2)
    		return;
    	try 
    	{
    		synchronized(output)
    		{
    			output.writeObject("Status");
    			output.reset();
    			current=(SendCurrentItem)input.readObject();
    			v=(Vector<SendBidStatus>) input.readObject();
    		}
			if(current.ID==0)
			{
				flag2=true;
				notStarted.setVisible(true);
				purchase.setVisible(false);
				cancel();
				return;
			}
			for(Integer n:CustomerCollection.items.keySet())
			{
				if(n==current.ID)
				{
					icon=CustomerCollection.Images.get(n);
					item=CustomerCollection.items.get(n);
					purchase.lblName.setIcon(icon);
					purchase.lblname2.setText(item.name);
					purchase.lblcost.setText(String.valueOf(item.Reserve_Price));
					purchase.label_2.setText(current.Time);
					purchase.label_3.setText(Integer.toString(item.ID));
					purchase.ID=n;
					break;
				}
			}
			if(CheckID!=current.ID)
			{
				purchase.highest=0;
				highest=0;
			}
			rows = purchase.model.getRowCount();
			for(int i = rows - 1; i >=0; i--)
			{
				purchase.model.removeRow(i);
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
			flag=false;
			while(!pq.isEmpty())
			{
		    	pb=pq.poll();
		    	if(flag==false)
				{
		    		highest=pb.bid.Bid_Price;
					flag=true;
				}
				purchase.model.addRow(new Object[]{pb.bid.Customer_Name,pb.bid.Bid_Price});
			}
			purchase.highest=highest;
		} catch (Exception e) 
    	{
			e.printStackTrace();
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