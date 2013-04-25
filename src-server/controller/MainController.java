package controller;

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import controller.proxy.InvocationHandlerAdmin;
import controller.proxy.InvocationHandlerAnonymous;
import controller.proxy.InvocationHandlerStudent;
import controller.proxy.InvocationHandlerTeacher;
import controller.proxy.RemoteController;
import controller.proxy.RemoteControllerImpl;
import controller.proxy.ROLE;

import model.*;
import model.dao.DaoFactory;
import model.dao.UserDao;

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
	}

	private RemoteControllerImpl remote;
	private RemoteController proxremote;
	Registry registry;
	private UserDao userdao;

	private RemoteController getProxyAnonymous(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerAnonymous(remote));
	}

	private RemoteController getProxyAdmin(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerAdmin(remote));
	}

	private RemoteController getProxyStudent(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerStudent(remote));
	}
	
	private RemoteController getProxyTeacher(RemoteController remote) {
		return (RemoteController) Proxy.newProxyInstance(remote.getClass()
				.getClassLoader(), remote.getClass().getInterfaces(),
				new InvocationHandlerTeacher(remote));
	}

	public RemoteController getProxy(String login, String pass) {
		User u = userdao.findByLoginAndPass(login, pass);
		if (u == null) return getProxyAnonymous(remote);
		try {
			remote = new RemoteControllerImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (u instanceof Student) {
			try {
			    remote.setGranted(ROLE.STUDENT);
			    remote.setUser(u);
				return getProxyStudent(remote);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (u instanceof Teacher) {
			try {
			    remote.setGranted(ROLE.TEACHER);
			    remote.setUser(u);
				return getProxyTeacher(remote);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
			    remote.setGranted(ROLE.ADMIN);
				return getProxyAdmin(remote);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return getProxyAnonymous(remote);
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

		try {
			registry = java.rmi.registry.LocateRegistry.createRegistry(13000);
			remote = new RemoteControllerImpl();
			proxremote = getProxyAnonymous(remote);
			userdao = (UserDao) DaoFactory.getUserDao();
			registry.rebind("RemoteController", proxremote);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}