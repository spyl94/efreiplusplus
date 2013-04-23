package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.proxy.RemoteController;

public class Connexion extends JFrame {
	private JPanel all = new JPanel();
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	private JTextField user = new JTextField("Login");
	private JPasswordField pwd = new JPasswordField("Password");
	private JButton valid = new JButton("Valider");
	private Boolean onceUser = true;

	private JLabel label = new JLabel("Veuillez entrer vos identifiants :");

	public Connexion() {
		this.setTitle("Efrei++");
		this.setSize(250, 180);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		Font police = new Font("Arial", Font.PLAIN, 14);

		user.setFont(police);
		user.setPreferredSize(new Dimension(150, 30));
		user.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (onceUser == true) {
					user.setText("");
					pwd.setText("");
					onceUser = false;
				}
			}
		});

		pwd.setFont(police);
		pwd.setPreferredSize(new Dimension(150, 30));
		pwd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (onceUser == true) {
					user.setText("");
					pwd.setText("");
					onceUser = false;
				}
			}
		});
		valid.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				    try {
				    	MainWindow.getInstance().setStub(MainWindow.getInstance().getStub().login(user.getText(), pwd.getPassword().toString()));
				    	MainWindow.getInstance().switchPanel("");
						/*if(temp.equals("admin")){
							new DialogBox("Great!", "Vous êtes connecté en tant qu'Administateur.");
						}
						else if(temp.equals("user")){
							new DialogBox("Great!", "Vous êtes connecté en tant qu'Utilisateur.");
						}
						else{
							new DialogBox("Attention!", "Problème d'identifiants ou de communication, recommencez.");								
						}*/
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				  }
		});
		
		all.setLayout(new BorderLayout());
		top.add(label);
		top.add(user);
		top.add(pwd);
		all.add(top, BorderLayout.CENTER);
		bot.add(valid);
		all.add(bot, BorderLayout.SOUTH);

		this.setContentPane(all);
		this.setVisible(true);
	}
}
	