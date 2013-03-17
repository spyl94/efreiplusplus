package controller.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import model.Teacher;

public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController {

	public RemoteControllerImpl() throws RemoteException {}

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

}
