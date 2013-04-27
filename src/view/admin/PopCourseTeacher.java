package view.admin;

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
import model.Teacher;

public class PopCourseTeacher extends JFrame{
	
	private JFrame window = this;
	private JPanel top = new JPanel();
	private JPanel bot = new JPanel();
	private JPanel quitPan = new JPanel();
	private JPanel all = new JPanel();
	private JComboBox addCombo = new JComboBox();
	private JComboBox remCombo = new JComboBox();
	private JButton addButton = new JButton("Attribuer");
	private JButton remButton = new JButton("Enlever");
	private JButton quitButton = new JButton("Quitter");
	private Set<Course> courses;
	private Set<Course> teacherCourses;
	private Teacher actualTeacher;
	
	public PopCourseTeacher(Teacher actual){
		actualTeacher = actual;
		this.setTitle("Attribution cours");
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		all.setLayout(new BorderLayout());
		
		try {
			courses = MainWindow.getInstance().getStub().getCourses();
			teacherCourses = MainWindow.getInstance().getStub().getTeacherCourse(actual);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for (Course i : courses) {
			addCombo.addItem(i.getName());
		}
		
		for (Course i : teacherCourses) {
			remCombo.addItem(i.getName());
		}
		
		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Course actualCourse = null;
				for (Course i : courses) {
					if (i.getName().equals(
							addCombo.getSelectedItem().toString())) {
						actualCourse = i;
					}
				}
				try {
					MainWindow.getInstance().getStub().addTeacherCourse(actualTeacher, actualCourse);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					teacherCourses = MainWindow.getInstance().getStub().getTeacherCourse(actualTeacher);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				remCombo.removeAllItems();
				for (Course i : teacherCourses) {
					remCombo.addItem(i.getName());
				}
			}
		});
		
		remButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Course actualCourse = null;
				for (Course i : courses) {
					if (i.getName().equals(
							remCombo.getSelectedItem().toString())) {
						actualCourse = i;
					}
				}
				/*
				try {
					MainWindow.getInstance().getStub();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				*/
				
				try {
					teacherCourses = MainWindow.getInstance().getStub().getTeacherCourse(actualTeacher);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				remCombo.removeAllItems();
				for (Course i : teacherCourses) {
					remCombo.addItem(i.getName());
				}
				
			}
		});
		
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				window.dispose();
			}
		});

		top.add(addCombo);
		top.add(addButton);
		bot.add(remCombo);
		bot.add(remButton);
		quitPan.add(quitButton);
		
		all.add(top, BorderLayout.NORTH);
		all.add(bot, BorderLayout.CENTER);
		all.add(quitPan, BorderLayout.SOUTH);
		
		this.setContentPane(all);
		this.setVisible(true);
		
	}
}
