package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.*;
import model.dao.DaoFactory;
import model.dao.MajorDao;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.MainController;
import controller.StudentController;

public class StudentControllerTest {
    
    public static StudentController controller;
    public static MajorDao major;

    @BeforeClass
    public static void init() {
        MainController.getInstance();
        controller = StudentController.getInstance();
        major = (MajorDao) DaoFactory.getMajorDao();
        major.create(new Major());
    }

    @Test
    public void testAddFindAndRemoveStudent() {
       assertTrue(controller.addStudent("testAddFindAndRemoveStudent"));
       Student s = (Student) controller.getStudents().toArray()[0];
       assertNotNull(s);
       assertTrue(controller.removeStudent(s));
       assertNull(controller.getMark(s));
    }

    @Test
    public void testAddMark() {
        assertTrue(controller.addStudent("testAddMajor"));
        Iterator<Student> i = controller.getStudents().iterator();
        Student s = null;
        while(i.hasNext())
            s = (Student) i.next();
        assertNotNull(s);
        
        assertTrue(controller.addMark(s, new LectureCourse(), 10));
        assertFalse(controller.addMark(s, new LectureCourse(), -1));
        assertFalse(controller.addMark(s, new LectureCourse(), 21));
        assertFalse(controller.addMark(null, new LectureCourse(), 10));
        assertFalse(controller.addMark(s, null, 10));
        
       // assertTrue(controller.getMark(student).contains(new Mark(student, new LectureCourse(),10)));
    }
    
    @Test
    public void testAddMajor() {
        assertTrue(controller.addStudent("testAddMajor"));
        Iterator i = controller.getStudents().iterator();
        Student s = null;
        while(i.hasNext())
            s = (Student) i.next();
        assertNotNull(s);
        assertTrue(controller.setMajor(s, new Major()));
        assertTrue(s.getMajor().getId() == 0);
    }

}