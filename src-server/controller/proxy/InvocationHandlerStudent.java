package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerStudent implements InvocationHandler, java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1386756384181235979L;
	RemoteController controller;

    public InvocationHandlerStudent(RemoteController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("YOU ARE NOW STUDENT GRANTED");
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(controller, args);
            }
            else if (method.getName().equals("addStudentMajor")){
            	return method.invoke(controller, args);
            }
            else if (method.getName().equals("addStudentCourse")){
            	return method.invoke(controller, args);
            }
            else if (method.getName().equals("removeStudentCourse")){
            	return method.invoke(controller, args);
            }
            else {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
