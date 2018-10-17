package Server;
import java.awt.Color;
import java.awt.Font;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerClass extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel label_1;
	public ServerClass()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 300, 200);
		setTitle("Server");
		setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(200, 255, 255));
		panel.setBounds(340, 171, 594, 411);
		panel.setLayout(null);
		add(panel);
		
		label_1 = new JLabel("Server Is Active");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Dialog", Font.BOLD, 25));
		label_1.setBounds(35,80, 300,25);
		panel.add(label_1);
	}
	
	public void Perform() throws Exception
	{
		@SuppressWarnings("resource")
		ServerSocket s=new ServerSocket(9783);
		Socket s2;
		while(true)
		{
			s2=s.accept();
			CheckThread checkthread=new CheckThread(s2);
			Thread t=new Thread(checkthread);
			t.start();
	    }
	}
	public static void main(String[] args) throws Exception
	{
		ServerClass server=new ServerClass();
		server.setVisible(true);
		Jdbc.createConnection();
		server.Perform();
	}
}
