package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import CommonClasses.*;
public class Jdbc {

	public static Connection conn;
	public static Statement st1;
	public static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11,ps12,ps13,ps14,ps15,ps16,ps17,ps18,ps19,ps20,ps21;
	public static ResultSet rset1,rset2,rset3,rset4;
	public static int n;
	public static Vector<String> College_list=new Vector<String>(15);
	public static Iterator<String> I1;
	public static byte Barray[];
	public static void createConnection() throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/CodeSkate003";
		String userName = "root";
		String password = "Mayuresh@123";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url, userName, password);
		st1=conn.createStatement();
		ps1=conn.prepareStatement("update Status set Auction=?;");
		ps2=conn.prepareStatement("update Admin set Password=? where Name=?;");
		ps3=conn.prepareStatement("select * from Status;");
		ps4=conn.prepareStatement("Insert into Bid values(?,?,?,?)");
		ps5=conn.prepareStatement("Insert into Customer(Name,Address,Mob,DOB,Password,Nickname) values(?,?,?,?,?,?);");
		ps6=conn.prepareStatement("select Name,Mob,Password from Customer;");
		ps7=conn.prepareStatement("update Customer set Password=? where Name=?;");
		ps8=conn.prepareStatement("select * from Customer where Name=? and Password=?;");
		ps9=conn.prepareStatement("update Customer set Name=?,Nickname=?,Address=?,Mob=?,DOB=? where Name=? and Password=?;");
		ps10=conn.prepareStatement("update Status set ID=?,Time=?;");
		ps11=conn.prepareStatement("select * from Bid where ID=?;");
		ps12=conn.prepareStatement("select count(*) as total from Item;");
		ps13=conn.prepareStatement("insert into SoldItem values(?,?,?,?,?,?,?,?);");
		ps14=conn.prepareStatement("select * from Bid where ID=? and Bid_Price in(select max(Bid_Price) from Bid where ID=?);");
		ps15=conn.prepareStatement("select Name,Password from Customer;");
		ps16=conn.prepareStatement("select * from SoldItem where Consumer_ID=?;");
		ps17=conn.prepareStatement("insert into Item(Name,Owner_Name,Reserve_Price,Image) values(?,?,?,?);");
		ps18=conn.prepareStatement("delete from Item where ID=?;");
		ps19=conn.prepareStatement("select * from Customer where Id=?;");
		ps20=conn.prepareStatement("select * from Customer where Name=? and Password=?;");
		ps21=conn.prepareStatement("select * from Item where ID=?;");
		ps1.setString(1,"NotStarted");
		ps1.executeUpdate();
	}
	public static boolean CheckAdmin(String s1,String s2) throws Exception
	{
		rset1 = st1.executeQuery("select * from Admin;");
		if(rset1.next())
		{
			if(rset1.getString("Name").equals(s1) && rset1.getString("Password").equals(s2))
				return true;
			else
				return false;
		}
		return false;
	}
	public static boolean CheckCustomer(String s1,String s2) throws Exception
	{
		rset3 = st1.executeQuery("select * from Customer;");
		boolean flag=false;
		while(rset3.next())
		{
			if(rset3.getString("Name").equals(s1) && rset3.getString("Password").equals(s2))
				flag=true;
		}
		return flag;
	}
	public static boolean ChangePassword(String s1,String s2,String s3) throws Exception
	{
		rset1 = st1.executeQuery("select * from Admin;");
		if(rset1.next())
		{
			if(rset1.getString("Name").equals(s1) && rset1.getString("Nickname").equals(s2))
			{
				ps2.setString(1,s3);
				ps2.setString(2,s1);
				ps2.executeUpdate();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	public static boolean ChangeCustomerPassword(String s1,String s2,String s3) throws Exception
	{
		rset1 = st1.executeQuery("select * from Customer;");
		if(rset1.next())
		{
			if(rset1.getString("Name").equals(s1) && rset1.getString("Nickname").equals(s2))
			{
				ps7.setString(1,s3);
				ps7.setString(2,s1);
				ps7.executeUpdate();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	public static boolean InsertCustomer(CustomerInfo s) throws Exception
	{
		rset1=ps6.executeQuery();
		while(rset1.next())
		{
			if(rset1.getLong("Mob")==s.mob && rset1.getString("Name").equals(s.name) && rset1.getString("Password").equals(s.password))
				return false;
		}
			ps5.setString(1,s.name);
			ps5.setString(2,s.address);
			ps5.setLong(3,s.mob);
			ps5.setString(4,s.dob);
			ps5.setString(5,s.password);
			ps5.setString(6,s.nickname);
			ps5.executeUpdate();
			return true;
	}
	public static void AuctionStarted() throws Exception
	{
		ps1.setString(1,"Started");
		ps1.executeUpdate();
	}
	public static void AuctionStopped() throws Exception
	{
		ps1.setString(1,"NotStarted");
		ps1.executeUpdate();
	}
	public static boolean AuctionStatus() throws Exception
	{
		synchronized(ps3)
		{
			rset2=ps3.executeQuery();
			String status;
			if(rset2.next())
			{
				status=rset2.getString("Auction");
				if(status.equals("Started"))
					return true;
			}
			return false;
		}
	}
	public static void InsertCurrentItem(SendCurrentItem c) throws Exception
	{
		ps10.setInt(1,c.ID);
		ps10.setString(2,c.Time);
		ps10.executeUpdate();
	}
	public static SendCurrentItem CurrentItemStatus(SendCurrentItem current) throws Exception
	{
		synchronized(ps3)
		{
		rset4=ps3.executeQuery();
		if(rset4.next())
		{
			current.ID=rset4.getInt("ID");
			current.Time=rset4.getString("Time");
		}}
		return current;
	}
	public static int ItemSize() throws Exception
	{
		rset1=ps12.executeQuery();
		rset1.next();
		return rset1.getInt("total");
	}
	public static Vector<SendBidStatus> CurrentBidStatus(int ID) throws Exception
	{
		synchronized(ps11)
		{
		ps11.setInt(1,ID);
		rset1=ps11.executeQuery();
		Vector<SendBidStatus> i=new Vector<SendBidStatus>();
		SendBidStatus bid;
		while(rset1.next())
		{
			bid=new SendBidStatus(rset1.getInt("ID"),rset1.getString("Consumer_Name"),rset1.getInt("Bid_Price"),rset1.getInt("Constomer_ID"));
			i.add(bid);
		}
		return i;
		}
	}
	public static void InsertBidCost(SendCost cost) throws Exception
	{
		ps4.setInt(1,cost.ID);
		ps4.setString(2,cost.Consumer_Name);
		ps4.setInt(3,cost.cost);
		ps4.setInt(4,cost.Consumer_ID);
		ps4.executeUpdate();
	}
	public static void InsertConsumer(int ID,String consumer,int price,int Consumer_ID) throws Exception
	{
		ps21.setInt(1,ID);
		rset2=ps21.executeQuery();
		rset2.next();
		ps13.setInt(1,rset2.getInt("ID"));
		ps13.setString(2,rset2.getString("Name"));
		ps13.setString(3,rset2.getString("Owner_Name"));
		ps13.setInt(4,rset2.getInt("Reserve_Price"));
		ps13.setBinaryStream(5,rset2.getBinaryStream("Image"));
		ps13.setInt(6,price);
		ps13.setInt(7,Consumer_ID);
		ps13.setString(8,consumer);
		ps13.executeUpdate();
		
		ps18.setInt(1,ID);
		ps18.executeUpdate();
	}
	public static int GetCustomerID(String name,String password) throws Exception
	{
		ps20.setString(1,name);
		ps20.setString(2,password);
		rset1=ps20.executeQuery();
		rset1.next();
		return rset1.getInt("Id");
	}
	public static SendBidStatus GetHighestBidder(int ID) throws Exception
	{
		ps14.setInt(1,ID);
		ps14.setInt(2,ID);
		rset1=ps14.executeQuery();
		SendBidStatus bid;
		if(rset1.next())
			bid=new SendBidStatus(ID,rset1.getString("Consumer_Name"),rset1.getInt("Bid_Price"),rset1.getInt("Constomer_ID"));
		else
			bid=new SendBidStatus(ID,"NOT SOLD",-1,-1);
		return bid;
	}
	public static Vector<Item> MyOrders(int ID) throws Exception
	{
		ps16.setInt(1,ID);
		rset3=ps16.executeQuery();
		Vector<Item> ItemList=new Vector<Item>();
		InputStream fis;
		int size;
		while(rset3.next())
		{
			Item item=new Item(rset3.getString("Name"),rset3.getString("Owner_Name"),rset3.getInt("Reserve_Price"));
			ps19.setInt(1,ID);
			rset2=ps19.executeQuery();
			rset2.next();
			item.Consumer_Name=rset2.getString("Name");
			item.ID=rset3.getInt("ID");
			item.Hammer_Price=rset3.getInt("Hammer_Price");
			fis=(InputStream) rset3.getBinaryStream("Image");
			size=fis.available();
			item.b=new byte[size];
			fis.read(item.b);
			ItemList.add(item);
		}
		return ItemList;
	}
	public static String UpdateCustomerInfo(CustomerInfo customer,String s1,String s2) throws Exception
	{
		ps9.setString(1,customer.name);
		ps9.setString(2,customer.nickname);
		ps9.setString(3,customer.address);
		ps9.setLong(4,customer.mob);
		ps9.setString(5,customer.dob);
		ps9.setString(6,s1);
		ps9.setString(7,s2);
		ps9.executeUpdate();
		return customer.name;
	}
	public static void DeleteItem(int ID) throws Exception
	{
		ps18.setInt(1,ID);
		ps18.executeUpdate();
	}
	public static Vector<Item> LoadState() throws Exception
	{
		rset3 = st1.executeQuery("select * from Item;");
		Vector<Item> ItemList=new Vector<Item>();
		InputStream fis;
		int size;
		while(rset3.next())
		{
			Item item=new Item(rset3.getString("Name"),rset3.getString("Owner_Name"),rset3.getInt("Reserve_Price"));
			item.ID=rset3.getInt("ID");
			fis=(InputStream) rset3.getBinaryStream("Image");
			size=fis.available();
			item.b=new byte[size];
			fis.read(item.b);
			ItemList.add(item);
		}
		return ItemList;
	}
	public static Vector<Item> LoadSoldState() throws Exception
	{
		rset3 = st1.executeQuery("select * from SoldItem;");
		Vector<Item> ItemList=new Vector<Item>();
		InputStream fis;
		int size;
		while(rset3.next())
		{
			Item item=new Item(rset3.getString("Name"),rset3.getString("Owner_Name"),rset3.getInt("Reserve_Price"));
			item.Consumer_Name=rset3.getString("Consumer_Name");
			item.Hammer_Price=rset3.getInt("Hammer_Price");
			item.ID=rset3.getInt("ID");
			fis=(InputStream) rset3.getBinaryStream("Image");
			size=fis.available();
			item.b=new byte[size];
			fis.read(item.b);
			ItemList.add(item);
		}
		return ItemList;
	}
	public static CustomerInfo LoadCustomerState(String name,String password) throws Exception
	{
		ps8.setString(1,name);
		ps8.setString(2,password);
		rset1=ps8.executeQuery();
		if(rset1.next())
		{
			CustomerInfo customer=new CustomerInfo();
			customer.ID=rset1.getInt("Id");
			customer.name=rset1.getString("Name");
			customer.address=rset1.getString("Address");
			customer.dob=rset1.getString("DOB");
			customer.nickname=rset1.getString("Nickname");
			customer.password=rset1.getString("Password");
			customer.mob=rset1.getLong("Mob");
			return customer;
		}
		return null;
	}
	public static Boolean InsertItem(Item item) throws Exception
	{
		FileInputStream fis;
		File file=new File("Image");
		Path path=Paths.get(file.getAbsolutePath());
		Files.write(path,item.b);
		fis=new FileInputStream(file);
		ps17.setString(1,item.name);
		ps17.setString(2,item.Owner_name);
		ps17.setInt(3,item.Reserve_Price);
		ps17.setBinaryStream(4,fis);
		ps17.executeUpdate();
		return true;
	}
	public static AllCustomers LoadAllCustomers() throws Exception
	{
		Vector<CustomerInfo> students=new Vector<CustomerInfo>();
		AllCustomers h=new AllCustomers();
		h.info=students;
		CustomerInfo ct;
		rset3=ps15.executeQuery();
		while(rset3.next())
		{
			ct=LoadCustomerState(rset3.getString("Name"),rset3.getString("Password"));
			h.info.add(ct);
		}
		return h;
	}
}
