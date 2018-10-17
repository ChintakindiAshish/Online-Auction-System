package Customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import CommonClasses.*;

public class Result extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Item item;
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public JLabel lblResultOfAuction,label2;
	public byte Barray[]=new byte[120000];   //120 KBytes
	public FileOutputStream fos;
	public ImageIcon image;
	
	public Result() {

		setLayout(null);
		setBounds(309,180,980,564);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("No");
		model.addColumn("Item Name");
		model.addColumn("Item Image");
        model.addColumn("Owner Name");
        model.addColumn("Consumer");
        model.addColumn("Hammer Price");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        j1=new JScrollPane(table);
        j1.setBounds(40,71,890,420);
		add(j1);
		
		table.getColumn("Item Image").setCellRenderer(new LabelRenderer());
		
		lblResultOfAuction = new JLabel("Result of Auction");
		lblResultOfAuction.setForeground(Color.BLUE);
		lblResultOfAuction.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblResultOfAuction.setBounds(378, 23, 219, 30);
		add(lblResultOfAuction);
		
		CustomerCollection.solditems=new HashMap<Integer,Item>();
		CustomerCollection.soldImages=new HashMap<Integer,ImageIcon>();
	}
	class LabelRenderer implements TableCellRenderer
	{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			
			TableColumn tc=table.getColumn("Item Image");
			tc.setMinWidth(200);
			table.setRowHeight(200);
			return (Component)value;
		}
	}
	public void refresh() throws Exception
	{
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		Iterator<Item> i=CustomerCollection.SoldItemList.iterator();
		CustomerCollection.solditems.clear();
		CustomerCollection.soldImages.clear();
		while(i.hasNext())
		{
			item=i.next();
			Barray=item.b;
			File file=new File("/home/ashish/eclipse-workspace/Customer/CustomerImages/"+String.valueOf(item.ID)+".txt");
			fos=new FileOutputStream(file);
			fos.write(Barray);
			image=new ImageIcon("/home/ashish/eclipse-workspace/Customer/CustomerImages/"+String.valueOf(item.ID)+".txt");
			label2=new JLabel(image);
			CustomerCollection.solditems.put(item.ID,item);
			CustomerCollection.soldImages.put(item.ID,image);
			model.addRow(new Object[]{item.ID,item.name,label2,item.Owner_name,item.Consumer_Name,item.Hammer_Price});
		}
	}
}
