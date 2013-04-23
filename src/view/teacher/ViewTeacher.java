package view.teacher;

import java.awt.BorderLayout;
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
import model.Student;
import model.Teacher;
import view.MainWindow;

public class ViewTeacher extends JFrame {
	private Teacher actual;
	private Set<Course> courses;
	private JComboBox mainCourse = new JComboBox();
	private JButton refreshMark = new JButton("Actualiser");
	private JPanel viewMark = new JPanel();
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	
	public ViewTeacher() {
		this.setTitle("Interface étudiant");
		this.setSize(500, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		try {
			Set<Teacher> teaList = MainWindow.getInstance().getStub().getTeachers();
			for(Teacher i : teaList){
				if (i.getName().equals("Busca")){
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
		
		String  title[] = {"Etudiant", "Note", "Alerte"};
		
		Object[][] data = {
			      {"", "", ""},
			    };
		
		JTable markTab = new JTable(data, title){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		top.add(mainCourse);
		top.add(refreshMark);
		bot.add(new JScrollPane(markTab));
		
		viewMark.setLayout(new BorderLayout());
		viewMark.add(top,BorderLayout.NORTH);
		viewMark.add(bot,BorderLayout.CENTER);

		
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Notes", viewMark);
		//tab.addTab("Planifier le cursus", bot);
				
		this.setContentPane(tab);
		this.setVisible(true);
	
	}
}
