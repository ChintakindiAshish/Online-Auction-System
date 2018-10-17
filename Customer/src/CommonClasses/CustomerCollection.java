package CommonClasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

public class CustomerCollection implements Serializable{

	private static final long serialVersionUID = 1L;
	public static Vector<Item> ItemList=null;
	public static Vector<Item> SoldItemList=null;
	public static HashMap<Integer,ImageIcon> Images=null;
	public static HashMap<Integer,Item> items=null;
	public static HashMap<Integer,ImageIcon> soldImages=null;
	public static HashMap<Integer,Item> solditems=null;
	public static Vector<Item> MyItemList=null;
}
