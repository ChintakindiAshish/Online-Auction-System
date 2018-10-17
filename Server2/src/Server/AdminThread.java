package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import CommonClasses.*;

public class AdminThread implements Runnable,Serializable{

	private static final long serialVersionUID = 1L;
	public ObjectInputStream input;
	public ObjectOutputStream output;int n;
	public AllCustomers customers;
	public Vector<Item> ItemList;
	public boolean result;
	public SendCurrentItem current=new SendCurrentItem();
    public Iterator<Item> iterate;
    public Item item;
	public AdminThread(ObjectInputStream input,ObjectOutputStream output) throws Exception
	{
		this.input=input;
		this.output=output;
	}
	public void run()
	{
		try
		{
			ItemList=Jdbc.LoadState();
			ServerCollection2.items=new HashMap<Integer,Item>();
			iterate=ItemList.iterator();
			while(iterate.hasNext())
			{
				item=iterate.next();
				ServerCollection2.items.put(item.ID,item);
			}
			ItemList=Jdbc.LoadState();
			output.writeObject(ItemList);
			output.reset();
			while(true)
			{
				try 
				{
					Object obj=input.readObject();
					if(obj instanceof SendCurrentItem)
					{
						Jdbc.InsertCurrentItem(((SendCurrentItem)obj));
						current=Jdbc.CurrentItemStatus(current);
						output.writeObject(Jdbc.CurrentBidStatus(current.ID));
						output.reset();
					}
					if(obj instanceof String)
					{
						if(((String)obj).equals("CustomerDetails"))
						{
							customers=Jdbc.LoadAllCustomers();
							output.writeObject(customers);
							output.reset();
						}
						if(((String)obj).equals("ItemList"))
						{
							ItemList=Jdbc.LoadState();
							output.writeObject(ItemList);
							output.reset();
						}
						if(((String)obj).equals("SoldItemList"))
						{
							ItemList=Jdbc.LoadSoldState();
							output.writeObject(ItemList);
							output.reset();
						}
						else if(((String)obj).equals("AuctionStarted"))
						{
							Jdbc.AuctionStarted();
							TimerThread t=new TimerThread(input,output,Jdbc.ItemSize());
							Thread t2=new Thread(t);
							t2.start();
						}
						else if(((String)obj).equals("Status"))
						{
							current=Jdbc.CurrentItemStatus(current);
							output.writeObject(current);
							output.reset();
							output.writeObject(Jdbc.CurrentBidStatus(current.ID));
							output.reset();
						}
						else if(((String)obj).equals("AuctionStatus"))
						{
							output.writeObject(Jdbc.AuctionStatus());
							output.reset();
						}
					}
					else if(obj instanceof Item)
					{
						item=(Item)obj;
						result=Jdbc.InsertItem(item);
						output.writeObject(result);
						output.reset();
						synchronized(ServerCollection2.items) {
						ItemList=Jdbc.LoadState();
						ServerCollection2.items.clear();
						iterate=ItemList.iterator();
						while(iterate.hasNext())
						{
							item=iterate.next();
							ServerCollection2.items.put(item.ID,item);
						}
						}
					}
					else if(obj instanceof Integer)
					{
						Jdbc.DeleteItem((Integer)obj);
					}
				}
				catch(SocketException se)
				{
					break;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}