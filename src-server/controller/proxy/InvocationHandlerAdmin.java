package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAdmin implements InvocationHandler {

	RemoteController controller;

	public InvocationHandlerAdmin(RemoteController controller) {
		this.controller = controller;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, Throwable {

		try {
			if (method.getName().startsWith("add")) {
				try {
					return method.invoke(controller, args);
				} catch (InvocationTargetException e) {
					throw e.getCause();
				}
			} else {
				throw new IllegalAccessException();
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
