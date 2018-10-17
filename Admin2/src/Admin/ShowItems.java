package Admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import CommonClasses.*;

public class ShowItems extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Item item;
	public int row,rowcount;
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	private JLabel label,label2,lblPleaseSelectThe;
	public byte Barray[]=new byte[120000];   //120 KBytes
	public FileOutputStream fos;
	public ImageIcon image;
	public ShowItems(ObjectInputStream input,ObjectOutputStream output) throws Exception {

		setLayout(null);
		setBounds(309,180,980,564);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("Item ID");
		model.addColumn("Item Image");
        model.addColumn("Item Name");
        model.addColumn("Owner Name");
        model.addColumn("Reserve Price");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		table.getColumn("Item Image").setCellRenderer(new LabelRenderer());
		
		label = new JLabel("List of Items for Auction");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Dialog", Font.PLAIN, 25));
		label.setBounds(325, 24, 308, 30);
		add(label);
		
		lblPleaseSelectThe = new JLabel("Please select the row to delete.");
		lblPleaseSelectThe.setForeground(Color.RED);
		lblPleaseSelectThe.setBounds(70, 503, 253, 15);
		lblPleaseSelectThe.setVisible(false);
		add(lblPleaseSelectThe);
		
		JButton btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				
				row=table.getSelectedRow();
				rowcount=table.getRowCount();
				if(row < 0 || row >= rowcount)
				{
					lblPleaseSelectThe.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseSelectThe.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
				}
				try 
				{
					output.writeObject((Integer)table.getValueAt(row,0));
					model.removeRow(row);
					output.reset();
					output.writeObject("ItemList");
					output.reset();
					ServerCollection.ItemList=(Vector<Item>) input.readObject();
					row=-1;
				} catch (Exception e2) 
				{
					return;               // return from ActionPerformed
				}
			}
		});
		btnDeleteRow.setBounds(720, 515, 124, 37);
		add(btnDeleteRow);
		
		Iterator<Item> i=ServerCollection.ItemList.iterator();
		ServerCollection.items=new HashMap<Integer,Item>();
		ServerCollection.Images=new HashMap<Integer,ImageIcon>();
		while(i.hasNext())
		{
			item=i.next();
			Barray=item.b;
			File file=new File("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
			fos=new FileOutputStream(file);
			fos.write(Barray);
			image=new ImageIcon("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
			label2=new JLabel(image);
			ServerCollection.items.put(item.ID,item);
			ServerCollection.Images.put(item.ID,image);
			model.addRow(new Object[]{item.ID,label2,item.name,item.Owner_name,item.Reserve_Price});
		}
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
		Iterator<Item> i=ServerCollection.ItemList.iterator();
		ServerCollection.items.clear();
		ServerCollection.Images.clear();
		while(i.hasNext())
		{
			item=i.next();
			Barray=item.b;
			File file=new File("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
			fos=new FileOutputStream(file);
			fos.write(Barray);
			image=new ImageIcon("/home/ashish/eclipse-workspace/Admin2/AdminImages/"+String.valueOf(item.ID)+".txt");
			label2=new JLabel(image);
			ServerCollection.items.put(item.ID,item);
			ServerCollection.Images.put(item.ID,image);
			model.addRow(new Object[]{item.ID,label2,item.name,item.Owner_name,item.Reserve_Price});
		}
	}
}
