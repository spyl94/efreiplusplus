package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogBox extends JFrame {
	JFrame actual = this;
	private JPanel container = new JPanel();
	private JButton button = new JButton("Ok");
	private JLabel label = new JLabel();
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	
	public DialogBox(String title, String msg) {
		this.setTitle(title);
		label = new JLabel (msg);
		this.setSize(35 + msg.length()*8, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		top.add(label);
		bot.add(button);
		container.add(top, BorderLayout.NORTH);
		container.add(bot, BorderLayout.CENTER);
		button.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				    actual.dispose();
				  }
		});
		this.setContentPane(container);
		this.setVisible(true);
	}
}
