package controller.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import controller.StudentController;
import controller.TeacherController;

import model.Course;
import model.Major;
import model.Mark;
import model.Student;
import model.Teacher;

public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController {

	StudentController student;
	TeacherController teacher;
	
	
	public RemoteControllerImpl() throws RemoteException {
		student = StudentController.getInstance();
		teacher = TeacherController.getInstance();
	}

	@Override
	public Set<Teacher> getTeachers() {
		return teacher.getTeachers();
	}

	@Override
	public boolean addTeacher(String name) {
		return teacher.addTeacher(name);
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
	public boolean removeTeacher(Teacher t) throws RemoteException {
		return teacher.removeTeacher(t);
	}

	@Override
	public boolean addTeacherCourse(Teacher t, Course c) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudentCourse(Student s, Course c) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudentMajor(Student s, Major m) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Mark> getStudentMark(Student s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeStudent(Student s) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourse(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Course> getCourses() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMark(Student s, Course c, int mark)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
