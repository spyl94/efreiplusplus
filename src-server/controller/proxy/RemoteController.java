package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import model.Teacher;

public interface RemoteController extends Remote {
	
	public Set<Teacher> getTeachers() throws RemoteException;
	public boolean addTeacher(String name) throws RemoteException;

	public boolean connection(String user, String pass) throws RemoteException;
}
