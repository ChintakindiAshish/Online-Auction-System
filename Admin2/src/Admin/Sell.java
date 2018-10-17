package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import CommonClasses.*;

public class Sell extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public String item,item2,item3;
	public Sell thisobject;
	public JLabel lblFillTheFollowing,lblName,lblcost,lblphoto,lblThisChoiceIs2,lblThisChoiceIs3,lblThisChoiceIs4,lblThisChoiceIs5,lblThisChoiceIs6,lblImageAlreadyAdded,lblImage,lblSelected,lblOwnerName,lblHeight;
	public JButton btnAdd,btnSubmit,btnNewButton;
	public JTextPane textPane,textPane2,textPane_1;
	public int cost,status=11,height,width;
	public JFileChooser jfile;
	public File file;
	public FileInputStream fis;
	public String path;
	public ImageIcon image;
	public Boolean selected=false,flag;
	
	public Sell(ObjectInputStream input,ObjectOutputStream output) throws Exception {
		
		setLayout(null);
		setBounds(309,180,980,564);
		
		jfile=new JFileChooser();
		jfile.setCurrentDirectory(new java.io.File("/home/ashish/Downloads"));
		jfile.setDialogTitle("Select Image");
		jfile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfile.setAcceptAllFileFilterUsed(false);
		jfile.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
		jfile.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(301, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblName = new JLabel("Item Name");
		lblName.setBounds(147, 118, 153, 25);
		lblName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblName);
		
		lblImage = new JLabel("Image");
		lblImage.setBounds(147, 384, 70, 25);
		lblImage.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblImage);
		
		lblcost = new JLabel("Owner Name");
		lblcost.setFont(new Font("Serif", Font.PLAIN, 20));
		lblcost.setBounds(147, 199, 153, 25);
		add(lblcost);
		
		lblSelected = new JLabel("Selected");
		lblSelected.setForeground(new Color(0, 128, 0));
		lblSelected.setIcon(new ImageIcon("/home/ashish/Downloads/mark.png"));
		lblSelected.setBounds(534, 381, 121, 43);
		lblSelected.setVisible(false);
		add(lblSelected);
		
		lblThisChoiceIs2 = new JLabel("Please select valid choice");
		lblThisChoiceIs2.setForeground(Color.RED);
		lblThisChoiceIs2.setBounds(395, 236, 211, 15);
		add(lblThisChoiceIs2);
		lblThisChoiceIs2.setVisible(false);
		
		lblThisChoiceIs3 = new JLabel("Please select valid choice");
		lblThisChoiceIs3.setForeground(Color.RED);
		lblThisChoiceIs3.setBounds(395, 154, 211, 15);
		add(lblThisChoiceIs3);
		lblThisChoiceIs3.setVisible(false);
		
		lblThisChoiceIs6 = new JLabel("Please select valid choice");
		lblThisChoiceIs6.setForeground(Color.RED);
		lblThisChoiceIs6.setBounds(395, 323, 211, 15);
		add(lblThisChoiceIs6);
		lblThisChoiceIs6.setVisible(false);
		
		lblThisChoiceIs4 = new JLabel("Please upload an Image");
		lblThisChoiceIs4.setForeground(Color.RED);
		lblThisChoiceIs4.setBounds(395, 423, 211, 15);
		add(lblThisChoiceIs4);
		lblThisChoiceIs4.setVisible(false);
		
		lblThisChoiceIs5 = new JLabel("Please upload a valid image ");
		lblThisChoiceIs5.setForeground(Color.RED);
		lblThisChoiceIs5.setBounds(395, 423, 211, 15);
		add(lblThisChoiceIs5);
		lblThisChoiceIs5.setVisible(false);
		
		lblImageAlreadyAdded = new JLabel("Image Already Added.");
		lblImageAlreadyAdded.setForeground(Color.RED);
		lblImageAlreadyAdded.setBounds(390, 458, 162, 15);
		add(lblImageAlreadyAdded);
		lblImageAlreadyAdded.setVisible(false);
		
		textPane = new JTextPane();
		textPane.setBounds(395, 118, 379, 36);
		textPane.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane.setBackground(new Color(179, 217, 255));
		add(textPane);
		
		textPane2 = new JTextPane();
		textPane2.setBounds(395, 199, 379, 36);
		textPane2.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane2.setBackground(new Color(179, 217, 255));
		add(textPane2);
		
		lblHeight = new JLabel("( Height & Width <= 200p) ");
		lblHeight.setBounds(147, 409, 194, 15);
		add(lblHeight);
		
		lblOwnerName = new JLabel("Reserve Price");
		lblOwnerName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblOwnerName.setBounds(147, 285, 153, 25);
		add(lblOwnerName);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		textPane_1.setBackground(new Color(179, 217, 255));
		textPane_1.setBounds(395, 285, 379, 36);
		add(textPane_1);
		
		btnNewButton = new JButton("Upload");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				status=jfile.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION)
				{
					lblSelected.setVisible(true);
					file=jfile.getSelectedFile();
					path=file.getAbsolutePath();
					image=new ImageIcon(path);
					height=image.getIconHeight();
					width=image.getIconWidth();
					if(height > 200 || width > 200)
					   {
							lblThisChoiceIs5.setVisible(true);
								Timer t=new Timer(2000,new ActionListener() 
								{
									public void actionPerformed(ActionEvent e)
									{
										lblThisChoiceIs5.setVisible(false);
									}
								});
								t.setRepeats(false);
								t.start();
								status=11;
								lblSelected.setVisible(false);
								return;
						}
					try 
					{
						file=new File(path);
						fis=new FileInputStream(file);
						selected=true;
					} catch (FileNotFoundException e)
					{	
						e.printStackTrace();
					}
				}
				else if (status == JFileChooser.CANCEL_OPTION)
				{
					if(selected==true)
					{
						status = JFileChooser.APPROVE_OPTION;
					}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("/home/ashish/Downloads/upload.png"));
		btnNewButton.setBounds(395, 384, 135, 36);
		btnNewButton.setFont(new Font("Arial",Font.PLAIN,20));
		btnNewButton.setFocusable(false);
		add(btnNewButton);
		
		btnAdd = new JButton("Add Item");
		btnAdd.setFocusable(false);
		btnAdd.setBounds(395, 475, 143, 43);
		btnAdd.setFont(new Font("Arial",Font.PLAIN,20));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				
				try {
					output.reset();
				} catch (SocketException e1)
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
			
				if(textPane.getText().isEmpty() || textPane_1.getText().isEmpty() || textPane2.getText().isEmpty() || status != JFileChooser.APPROVE_OPTION)
				   {
					if(textPane.getText().isEmpty())
						lblThisChoiceIs3.setVisible(true);
					if(textPane2.getText().isEmpty())
						lblThisChoiceIs2.setVisible(true);
					if(textPane2.getText().isEmpty())
						lblThisChoiceIs6.setVisible(true);
					if(status != JFileChooser.APPROVE_OPTION)
						lblThisChoiceIs4.setVisible(true);
							Timer t=new Timer(2000,new ActionListener() 
							{
								public void actionPerformed(ActionEvent e)
								{
									if(textPane.getText().isEmpty())
										lblThisChoiceIs3.setVisible(false);
									if(textPane2.getText().isEmpty())
										lblThisChoiceIs2.setVisible(false);
									if(textPane2.getText().isEmpty())
										lblThisChoiceIs6.setVisible(false);
									if(status != JFileChooser.APPROVE_OPTION)
										lblThisChoiceIs4.setVisible(false);
								}
							});
							t.setRepeats(false);
							t.start();
							return;
					}
				try {
					cost=Integer.parseInt(textPane_1.getText());
				}
				catch(NumberFormatException e1)
				{
					lblThisChoiceIs6.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblThisChoiceIs6.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
					return;
				}
				
				JOptionPane.showMessageDialog(null,"Item added Successfully");
				Item item=new Item(textPane.getText(),textPane2.getText(),cost);
				try 
				{
					item.b=new byte[10000];
					fis.read(item.b,0,item.b.length);
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
				textPane.setText("");
				textPane2.setText("");
				textPane_1.setText("");
				lblSelected.setVisible(false);
				status=11;
				try 
				{
					output.writeObject(item);
					output.reset();
					flag=(Boolean) input.readObject();
					if(!flag)
					{
						lblImageAlreadyAdded.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
								lblImageAlreadyAdded.setVisible(false);
							}
						});
						t.setRepeats(false);
						t.start();
						return;
					}
				} catch (Exception e3) 
				{
					e3.printStackTrace();
				}
				
			}
		});
		add(btnAdd);
	}
}
