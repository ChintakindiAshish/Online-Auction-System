package Admin;

import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CommonClasses.*;
import java.awt.Color;

public class CustomerList extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CustomerInfo customer;
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public JLabel lblNewLabel;
	public CustomerList() {

		setLayout(null);
		setBounds(309,180,980,564);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("Mob.");
        model.addColumn("DOB");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		lblNewLabel = new JLabel("Customer List");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(383, 22, 182, 30);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,25));
		add(lblNewLabel);
		
		Iterator<CustomerInfo> i=ServerCollection.customers.iterator();
		while(i.hasNext())
		{
			customer=i.next();
			model.addRow(new Object[]{customer.ID,customer.name,customer.address,customer.mob,customer.dob});
		}
	}
	public void refresh()
	{
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		Iterator<CustomerInfo> i=ServerCollection.customers.iterator();
		while(i.hasNext())
		{
			customer=i.next();
			model.addRow(new Object[]{customer.ID,customer.name,customer.address,customer.mob,customer.dob});
		}
	}
}
