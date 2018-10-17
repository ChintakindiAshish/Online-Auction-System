package CommonClasses;

import java.io.Serializable;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 506837230885805592L;
	public String name,Owner_name,Consumer_Name;
	public int Reserve_Price,ID,Hammer_Price;
	public byte b[];
	public Item(String name,String Owner_name,int Reserve_Price)
	{
		this.name=name;
		this.Owner_name=Owner_name;
		this.Reserve_Price=Reserve_Price;
	}

}
