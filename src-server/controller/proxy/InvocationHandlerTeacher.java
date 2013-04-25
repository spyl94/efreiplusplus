package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerTeacher implements InvocationHandler, java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6075045240327584314L;
	RemoteController controller;

    public InvocationHandlerTeacher(RemoteController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("YOU ARE NOW TEACHER GRANTED");
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(controller, args);
            }
            else if (method.getName().equals("addMark")){
            	return method.invoke(controller, args);
            }else {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
