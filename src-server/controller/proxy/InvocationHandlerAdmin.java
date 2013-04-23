package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAdmin implements InvocationHandler, java.io.Serializable {

    RemoteController controller;

    public InvocationHandlerAdmin(RemoteController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("YOU ARE NOW ADMIN");
        try {
        	try {
                return method.invoke(controller, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
