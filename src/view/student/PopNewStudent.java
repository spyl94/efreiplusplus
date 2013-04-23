package view.student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainWindow;


import model.Major;

public class PopNewStudent extends JFrame {
	JFrame actual = this;
	private JPanel container = new JPanel();
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	private JPanel mid = new JPanel();
	private JButton button = new JButton("Ok");

	public PopNewStudent() {
		this.setTitle("Choix des matières");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel new1 = new JLabel("Vous n'êtes inscrit à aucune majeure, veuillez vous inscrire.");
		top.add(new1);
		container.setLayout(new BorderLayout());
		
		Set<Major> majorList;	
		JComboBox combo = new JComboBox();
		try {
			majorList = MainWindow.getInstance().getStub().getMajors();
			for(Major i : majorList){
				combo.addItem(i.getName());
				//TODO Modifier majeure de l'étudiant connecté
				//MainWindow.getInstance().getStub().addStudentMajor(s, m);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		button.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				    actual.dispose();
				  }
		});
		
		container.add(top,BorderLayout.NORTH);
		mid.add(combo);
		container.add(mid,BorderLayout.CENTER);
		bot.add(button);
		container.add(bot,BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true);
	}
}
