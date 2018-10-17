package CommonClasses;

import java.io.Serializable;

public class SendBidStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int ID,Bid_Price,Customer_ID;
	public String Customer_Name;
	public SendBidStatus(int ID,String Customer_Name,int Bid_Price,int Customer_ID)
	{
		this.ID=ID;
		this.Customer_Name=Customer_Name;
		this.Bid_Price=Bid_Price;
		this.Customer_ID=Customer_ID;
	}
}
