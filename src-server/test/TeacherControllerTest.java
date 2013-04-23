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
    
    private Teacher getLastTeacher() {
        Iterator i = controller.getTeachers().iterator();
        Teacher t = null;
        while(i.hasNext())
            t =  (Teacher) i.next();
        return t;
    }

    @Test
    public void testAddFindAndRemoveTeacher() {
       assertTrue(controller.addTeacher("testAddFindAndRemoveTeacher"));
       Teacher t = getLastTeacher();
       assertNotNull(t);
       assertTrue(controller.removeTeacher(t));
       //assertNull(controller.getCourse(t));
    }
    
    @Test
    public void testAddCourseTeacher() {
       assertTrue(controller.addTeacher("testAddCourseTeacher"));
       Iterator i = controller.getTeachers().iterator();
       Teacher t = getLastTeacher();
       assertNotNull(t);
       assertTrue(controller.addCourse(t, new LectureCourse()));
       assertNotNull(controller.getCourse(t));
       assertTrue(controller.removeTeacher(t));
       //assertNull(controller.getCourse(t));
    }
    
    @Test
    public void testAddStudentTeacher() {
       assertTrue(controller.addTeacher("testAddStudentTeacher"));
       Teacher t = getLastTeacher();
       assertNotNull(t);
       assertTrue(controller.addStudentForTutor(t, new Student()));
       assertNotNull(controller.getStudentFromTutor(t));
    }

}