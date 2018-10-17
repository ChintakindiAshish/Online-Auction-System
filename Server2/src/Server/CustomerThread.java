package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.Vector;

import CommonClasses.*;

public class CustomerThread implements Runnable,Serializable{

	private static final long serialVersionUID = 1L;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public int n,ID;
	public String name,password,store;
	public Vector<Item> ItemList;
	public SendCurrentItem current=new SendCurrentItem();
	public CustomerThread(ObjectInputStream input,ObjectOutputStream output,String name,String password,int ID) throws Exception
	{
		this.input=input;
		this.output=output;
		this.name=name;
		this.password=password;
		this.ID=ID;
	}
	public void run()
	{
		try
		{
			CustomerInfo customer=Jdbc.LoadCustomerState(name,password);
			output.writeObject(customer);
			output.reset();
			ItemList=Jdbc.LoadState();
			output.writeObject(ItemList);
			output.reset();
			while(true)
			{
				try
				{
					Object obj=input.readObject();
					if(obj instanceof CustomerInfo)
					{
						if(((CustomerInfo)obj).myorders==null)
							name=Jdbc.UpdateCustomerInfo(((CustomerInfo)obj),name,password);
					}
					else if(obj instanceof String)
					{
						store=String.valueOf(obj);
						if(store.equals("ItemList"))
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
						else if(store.equals("AuctionStatus"))
						{
							output.writeObject(Jdbc.AuctionStatus());
							output.reset();
						}
						else if(store.equals("Status"))
						{
							current=Jdbc.CurrentItemStatus(current);
							output.writeObject(current);
							output.reset();
							output.writeObject(Jdbc.CurrentBidStatus(current.ID));
							output.reset();
						}
						else if(store.equals("MyOrders"))
						{
							ItemList=Jdbc.MyOrders(ID);
							output.writeObject(ItemList);
							output.reset();
						}
					}
					else if(obj instanceof SendCost)
					{
						Jdbc.InsertBidCost((SendCost)obj);
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