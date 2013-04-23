package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAnonymous implements InvocationHandler, java.io.Serializable {

    RemoteController controller;

    public InvocationHandlerAnonymous(RemoteController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("YOU ARE ANONYMOUS");
        try {
            if (method.getName().equals("login")) {
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
