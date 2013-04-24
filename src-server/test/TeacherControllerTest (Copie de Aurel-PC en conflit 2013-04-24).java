package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.MainController;
import controller.TeacherController;

public class TeacherControllerTest {
    
    public static TeacherController controller;

    @BeforeClass
    public static void init() {
        MainController.getInstance();
        controller = TeacherController.getInstance();
    }

    @Test
    public void testAddFindAndRemoveTeacher() {
       assertTrue(controller.addTeacher("testAddFindAndRemoveTeacher"));
       Iterator i = controller.getTeachers().iterator();
       Teacher t = null;
       while(i.hasNext())
           t =  (Teacher) i.next();
       assertNotNull(t);
       assertTrue(controller.removeTeacher(t));
       assertNull(controller.getCourse(t));
    }
    
    @Test
    public void testAddCourseTeacher() {
       assertTrue(controller.addTeacher("testAddCourseTeacher"));
       Iterator i = controller.getTeachers().iterator();
       Teacher t = null;
       while(i.hasNext())
           t = (Teacher) i.next();
       assertNotNull(t);
       assertTrue(controller.removeTeacher(t));
       assertNull(controller.getCourse(t));
    }
    
    @Test
    public void testAddStudentTeacher() {
       assertTrue(controller.addTeacher("testAddStudentTeacher"));
       Teacher t = (Teacher) controller.getTeachers().toArray()[0];
       assertNotNull(t);
       assertTrue(controller.addStudentForTutor(t, new Student()));
       assertNotNull(controller.getStudentFromTutor(t));
    }

}