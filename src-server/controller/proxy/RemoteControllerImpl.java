package controller.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import controller.StudentController;

import model.Student;
import model.Teacher;

public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController {

	StudentController student;
	
	
	public RemoteControllerImpl() throws RemoteException {
		student = StudentController.getInstance();
	}

	@Override
	public Set<Teacher> getTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTeacher(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean connection(String user, String pass) {
		return true;
	}

	@Override
	public Set<Student> getStudents() throws RemoteException {
		return student.getStudents();
	}

	@Override
	public boolean addStudent(String name) throws RemoteException {
		return student.addStudent(name);
	}

	@Override
	public boolean removeStudent(int id) throws RemoteException {
		return student.removeStudent(id);
	}

}
