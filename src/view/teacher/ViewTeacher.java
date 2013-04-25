package view.teacher;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Course;
import model.Major;
import model.Mark;
import model.Student;
import model.Teacher;
import view.MainWindow;

public class ViewTeacher extends JFrame {
	private Teacher actual;
	private Set<Course> courses;
	private JComboBox mainCourse = new JComboBox();
	private JButton refreshMark = new JButton("Afficher");
	private JButton addMark = new JButton("Ajouter note");
	private JPanel viewMark = new JPanel();
	private JPanel top = new JPanel();
	private JPanel tutorMid = new JPanel();
	private JTabbedPane tab = new JTabbedPane();
	private Object[][] dataMark;

	private JButton tutorButton = new JButton("Tutorats");

	public ViewTeacher() {
		this.setTitle("Interface professeur");
		this.setSize(600, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		try {
			Set<Teacher> teaList = MainWindow.getInstance().getStub().getTeachers();
			Teacher test = (Teacher) MainWindow.getInstance().getStub().getUser();
			for(Teacher i : teaList){
				if (i.getId() == test.getId()){
					actual = i;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		try {
			courses = MainWindow.getInstance().getStub().getTeacherCourse(actual);
			for(Course i : courses){
				mainCourse.addItem(i.getName());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
		
		
		addMark.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Course current = null;
			  	mainCourse.getSelectedItem().toString();
			  
				for(Course i : courses){
					if(mainCourse.getSelectedItem().toString().equals(i.getName())){
						current = i;
					}
				}
				new PopAddMark(current);
			}
		});
		
		tutorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new PopTutor(actual);
			}
		});
		
		refreshMark.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				  	Course current = null;
				  	mainCourse.getSelectedItem().toString();
				  
					for(Course i : courses){
						if(mainCourse.getSelectedItem().toString().equals(i.getName())){
							current = i;
						}
					}
					Set<Student> studentList = null;
					try {
						studentList = MainWindow.getInstance().getStub().getCourseStudent(current);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					int cptName = 0;
					int cptMark = 0;
					int total = 0;
					for(Student i : studentList){
						Set<Mark> marks = null;
						try {
							marks = MainWindow.getInstance().getStub().getStudentMark(i);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						total = total + marks.size();
					}
					
					dataMark = new Object[total][2];
					for(Student i : studentList){
						
						Set<Mark> marks = null;
						try {
							marks = MainWindow.getInstance().getStub().getStudentMark(i);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						for(int k = 0; k < marks.size(); k++){
							dataMark[cptName][0] = i.getName();
							cptName++;
						}
						for(Mark j : marks){
							if(j.getCours().equals(current)){
								System.out.println("test");
								dataMark[cptMark][1] = j.getMark();
								cptMark++;
							}
						}
					}
					cptName = 0;
					cptMark = 0;
					new ViewMark(dataMark);
				  }
		});
		
		
		
		top.add(mainCourse);
		top.add(refreshMark);
		top.add(addMark);
		tutorMid.add(tutorButton);
		viewMark.setLayout(new BorderLayout());
		viewMark.add(top,BorderLayout.NORTH);

		
		
		tab.addTab("Notes", viewMark);
		tab.addTab("Tutorat", tutorMid);
				
		this.setContentPane(tab);
		this.setVisible(true);
	
	}
}
