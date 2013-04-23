package view.student;

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

import view.MainWindow;

import model.Course;
import model.Major;
import model.Mark;
import model.Student;

public class ViewStudent extends JFrame {
	private JButton addButton = new JButton("Ajouter");
	private JButton remButton = new JButton("Enlever");
	private Student actual = null;
	private Set<Course> courseList;
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	private JPanel midAdd = new JPanel();
	private JPanel midRem = new JPanel();
	private Set<Mark> setMark;
	private Object data[][] = null;
	private JComboBox addCombo = new JComboBox();
	private JComboBox remCombo = new JComboBox();
	
	public ViewStudent() {
		this.setTitle("Interface étudiant");
		this.setSize(500, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		top.setLayout(new BorderLayout());

		String  title[] = {"Matière", "Note"};
		
		try {
			Set<Student> stuList = MainWindow.getInstance().getStub().getStudents();
			for(Student i : stuList){
				if (i.getName().equals("Adrien")){
					actual = i;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			setMark = MainWindow.getInstance().getStub().getStudentMark(actual);
			data = new Object[setMark.size()][2];
			int cpt = 0;
			for(Mark i : setMark){
				data[cpt][0] = i.getCours().getName();
				data[cpt][1] = i.getMark();
				cpt++;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		JTable markTab = new JTable(data, title){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
			
		
		try {
			courseList = MainWindow.getInstance().getStub().getCourses();
			for(Course i : courseList){
				addCombo.addItem(i.getName());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		addButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				    try {
				    	Course c = null;
						for(Course i : courseList){
							if(addButton.getText().equals(i.getName())){
								c = i;
							}
						}
						
						//TODO On doit vérifier que le cours n'est pas déjà présent.
						
				    	MainWindow.getInstance().getStub().addStudentCourse(actual, c);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				  }
		});
		
		
		//TODO Ajouter l'autre bouton pour qu'il puisse désinscrire l'élève
		
		
		midAdd.add(addCombo);
		midAdd.add(addButton);
		midRem.add(remCombo);
		midRem.add(remButton);
		top.add(midAdd,BorderLayout.NORTH);
		top.add(midRem,BorderLayout.CENTER);
		
		bot.add(new JScrollPane(markTab));
		JTabbedPane tab = new JTabbedPane();
		
		tab.addTab("Notes", bot);
		tab.addTab("Planifier le cursus", top);
				
		this.setContentPane(tab);
		this.setVisible(true);
	
	}
}
