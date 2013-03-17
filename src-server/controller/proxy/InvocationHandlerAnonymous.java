package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAnonymous implements InvocationHandler {
	
	RemoteController controller;

	public InvocationHandlerAnonymous(RemoteController controller) {
		this.controller = controller;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {
		
		try {
			if(method.getName().equals("connection")) {
				return method.invoke(controller, args);
			} else {
				throw new IllegalAccessException();
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
