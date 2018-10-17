package CommonClasses;


import java.io.Serializable;
import java.util.Vector;


public class CustomerInfo implements Serializable{

	private static final long serialVersionUID = -2880869040951508387L;
	public String name,address,dob,nickname,password;
	public long mob;
	public int ID;
	public Vector<Item> myorders=new Vector<Item>();
}
