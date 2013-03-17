package controller;

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import controller.proxy.InvocationHandlerAdmin;
import controller.proxy.InvocationHandlerAnonymous;
import controller.proxy.InvocationHandlerUser;
import controller.proxy.RemoteController;
import controller.proxy.RemoteControllerImpl;

import model.*;

/**
 * @author Aurel Adrien
 * @Singleton
 * 
 */
public class MainController {

	private static MainController controller;

	/**
	 * Returns the MainController.
	 * 
	 * @return the instance of MainController
	 */
	public static MainController getInstance() {
		if (controller == null)
			new MainController();
		return controller;
	}
	

	public static void main(String[] args) {
		MainController.getInstance().init(args);
		
	}
	
	public void init(String[] args) {
		try {
		    
			remote.addStudent("bidule");
			remote.getStudents();
			remote.removeStudent(1);
			remote.getStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Hashtable<String, Course> courses;
	private Hashtable<String, Teacher> teachers;
	private RemoteController remote;
	
	
	private RemoteController getProxyAnonymous(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(
				remote.getClass().getClassLoader(),
				remote.getClass().getInterfaces(),
				new InvocationHandlerAnonymous(remote)
				);
	}
	
	private RemoteController getProxyAdmin(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(
				remote.getClass().getClassLoader(),
				remote.getClass().getInterfaces(),
				new InvocationHandlerAdmin(remote)
				);
	}
	
	private RemoteController getProxyUser(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(
				remote.getClass().getClassLoader(),
				remote.getClass().getInterfaces(),
				new InvocationHandlerUser(remote)
				);
	}
	
	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private MainController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
		teachers = new Hashtable<String, Teacher>();
		courses = new Hashtable<String, Course>();
		
		try {
			remote = getProxyAnonymous(new RemoteControllerImpl());
			//Naming.rebind("RemoteController", remote);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}