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


import model.Course;
import model.Major;
import model.Student;

public class PopNewStudent extends JFrame {
	JFrame actual = this;
	private JPanel container = new JPanel();
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	private JPanel mid = new JPanel();
	private JButton button = new JButton("Ok");
	private Set<Major> majorList;
	private JComboBox combo = new JComboBox();
	
	public PopNewStudent() {
		this.setTitle("Choix des matières");
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		
		JLabel new1 = new JLabel("Vous n'êtes inscrit à aucune majeure, veuillez vous inscrire.");
		top.add(new1);
		container.setLayout(new BorderLayout());
				
		try {
			majorList = MainWindow.getInstance().getStub().getMajors();
			for(Major i : majorList){
				if(i.getId() != 0)
				combo.addItem(i.getName());

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		button.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
					Student newest = null;
					Set<Student> students;
					try {
						students = MainWindow.getInstance().getStub().getStudents();

						Set<Student> stuList = MainWindow.getInstance().getStub().getStudents();
						Student test = (Student) MainWindow.getInstance().getStub().getUser();
						for(Student i : stuList){
							if (i.getId() == test.getId()){
								newest = i;
							}
						}
					
			    	Major m = null;
					for(Major i : majorList){
						if(combo.getSelectedItem().equals(i.getName())){
							m = i;
						}
					}
				  	MainWindow.getInstance().getStub().addStudentMajor(newest, m);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					MainWindow.getInstance().switchStudent();
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
