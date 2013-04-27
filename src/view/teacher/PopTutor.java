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
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.MainWindow;

import model.Course;
import model.Mark;
import model.Student;
import model.Teacher;

public class PopTutor extends JFrame {
	private Set<Student> tutored;
	private Object[][] dataTutor;
	private String  titleTutor[] = {"Etudiant", "Alerte"};
	private JPanel tutorMid = new JPanel();
	
	PopTutor(Teacher actual){
		
		this.setTitle("Tutorat");
		this.setSize(500, 550);
		this.setLocationRelativeTo(null);
		
		try {
			tutored = MainWindow.getInstance().getStub().getStudentsFromTutor(actual);
			dataTutor = new Object[tutored.size()][2];
			int cpt = 0;
			for(Student i : tutored){
				dataTutor[cpt][0] = i.getName();
				Set<Mark> marks = MainWindow.getInstance().getStub().getStudentMark(i);
				int alerteNb = 0;
				for(Mark j : marks){
					if(j.getMark() < 10){
						alerteNb++;
					}
				}
				if(i.isAlerted()){
					dataTutor[cpt][1] = "Alerte";
				}
				else{
					dataTutor[cpt][1] = "";
				}
				cpt++;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		JTable tutorTab = new JTable(dataTutor, titleTutor){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		tutorMid.add(new JScrollPane(tutorTab));
		
		this.setContentPane(tutorMid);
		this.setVisible(true);
		
	}
}
