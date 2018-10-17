package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;

import CommonClasses.*;

public class TimerThread implements Runnable{

	public Iterator<ImageIcon> iterate;
	public ImageIcon image;
	public String data;
	public char letter;
	public int i,i2,size,ID,rows,array[],cnt;
	public String store;
	public SendCurrentItem current=new SendCurrentItem();
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public Item item;
	public Vector<SendBidStatus> v;
	public SendBidStatus bid;
	public boolean flag=false;
	
	public TimerThread(ObjectInputStream input,ObjectOutputStream output,int size)
	{
		this.input=input;
		this.output=output;
		this.size=size;
		array=new int[20];
	}
	@Override
	public void run() {
		Timer timer = new Timer();
		size=size*60+size;
		data="01 : 01";
		timer.schedule(new MyTimer(), 0, 1000);
		cnt=1;
		synchronized(ServerCollection2.items.keySet()) {
		for(Integer n:ServerCollection2.items.keySet())
		{
			ID=n;
			item=ServerCollection2.items.get(n);
			try 
			{
				Thread.sleep(61000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			data="01 : 01";
			try 
			{
				bid=Jdbc.GetHighestBidder(ID);
				if(bid.Bid_Price!=-1)
				{
					Jdbc.InsertConsumer(ID,bid.Customer_Name,bid.Bid_Price,bid.Customer_ID);
					array[cnt]=n;
					cnt++;
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		}
		for(int i=0;i<cnt;i++)
		{
			ServerCollection2.items.remove(array[i]);
		}
		current.Time="null";
    	current.ID=0;
    	try 
    	{
    		Jdbc.AuctionStopped();
    		Jdbc.InsertCurrentItem(current);
			
		} catch (Exception e) 
    	{
			e.printStackTrace();
		}
	}
	class MyTimer extends TimerTask {
	    public void run() {
	       
	    	if(flag)
	    		return;
	    	size--;
	    	store=null;
	    	letter=data.charAt(5);
	    	store=String.valueOf(letter);
	    	letter=data.charAt(6);
	    	store=store+String.valueOf(letter);
	    	i=Integer.parseInt(store);
	    	i--;
	    	
	    	store=null;
    		letter=data.charAt(0);
	    	store=String.valueOf(letter);
	    	letter=data.charAt(1);
	    	store=store+String.valueOf(letter);
	    	i2=Integer.parseInt(store);
	    	
	    	if(size==0)
	    	{
	    		flag=true;
	    		this.cancel();
	    		return;
	    	}
	    	if(i==-1)
	    	{
	    		
		    	i2--;
	    		i=59;
	    		if(i2==-1)
	    			return;
	    	}
	    	if(i2<10)
	    	{
	    		if(i<10)
		    		data="0"+i2+" : "+"0"+i;
	    		else
	    			data="0"+i2+" : "+i;
	    	}
	    	else
	    	{
	    		if(i<10)
	    			data=i2+" : "+"0"+i;
	    		else
	    			data=i2+" : "+i;
	    	}
	    	current.Time=data;
	    	current.ID=ID;
	    	try 
	    	{
	    		Jdbc.InsertCurrentItem(current);
				
			} catch (Exception e) 
	    	{
				e.printStackTrace();
			}
	    	
	    }
	}
}
