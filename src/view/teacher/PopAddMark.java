package view.teacher;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MainWindow;

import model.Course;
import model.Student;

public class PopAddMark extends JFrame{
	JFrame pop = this;
	private JComboBox student = new JComboBox();
	private JComboBox grade = new JComboBox();
	private JButton valid = new JButton("Valider");
	private Set<Student> students;
	private JPanel container = new JPanel();
	private JPanel mid = new JPanel();
	private JPanel central = new JPanel();
	private Course actualCourse;
	
	PopAddMark(Course actual){
		actualCourse = actual;
		this.setTitle("Ajout note");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		mid.setLayout(new BorderLayout());
		System.out.println(actual.getName());
		try {
			students = MainWindow.getInstance().getStub().getCourseStudent(actualCourse);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for(Student i : students){
			student.addItem(i.getName());
		}
		for(int i = 0; i <21 ; i++){
			grade.addItem(i);
		}
		
		valid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Student actual = null;
				for(Student i : students){
					if(student.getSelectedItem().equals(i.getName())){
						actual = i;
					}
				}
				int a = Integer.parseInt(grade.getSelectedItem().toString()); 
				try {
					MainWindow.getInstance().getStub().addMark(actual, actualCourse, a);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				pop.dispose();
			}
		});
		central.add(student);
		central.add(grade);
		central.add(valid);
		mid.add(central,BorderLayout.NORTH);
		container.add(mid);
		this.setContentPane(container);
		this.setVisible(true);
	}
}
