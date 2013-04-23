package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.student.PopNewStudent;
import view.student.ViewStudent;
import view.teacher.ViewTeacher;


import model.LectureCourse;
import model.Major;
import model.Student;

import controller.MainController;
import controller.proxy.RemoteController;

public class MainWindow  {
	private static MainWindow mainWindow;
	private RemoteController stub;
	private Registry registry;
	private Connexion connexionWindow;
	
	public static MainWindow getInstance() {
		if (mainWindow == null)
			new MainWindow();
		return mainWindow;
	}
	
	public RemoteController getStub(){
		return stub;
	}
	
	public void setStub(RemoteController newest){
		stub = newest;
	}
	
	public void switchPanel(String id){
		connexionWindow.dispose();
		
		Set<Student> stuList;
		try {
			stuList = stub.getStudents();
			for(Student i : stuList);
				//if (i.g)
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new PopNewStudent();
		//new ViewStudent();
		new ViewTeacher();
	}
	
	private MainWindow() {
		if (mainWindow == null)
			mainWindow = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
		
		 try {
	            registry = LocateRegistry.getRegistry("localhost", 13000);
	            stub = (RemoteController) registry.lookup("RemoteController");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
		 connexionWindow = new Connexion();
		 //new ViewStudent();
		 
	}
		
	public static void main(String[] args) {
		MainWindow.getInstance();	
	}
}
