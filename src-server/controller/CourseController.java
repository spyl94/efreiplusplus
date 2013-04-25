package controller;

import java.util.Hashtable;
import java.util.Set;

import model.Course;
import model.LectureCourse;
import model.Major;
import model.OptionalCourse;
import model.Student;
import model.dao.DaoFactory;
import model.dao.MajorDao;
import model.dao.CourseDao;
import model.dao.StudiesDao;

public class CourseController {

    private static CourseController controller;
    private Hashtable<Integer, Course> Courses;
    private CourseDao dao = (CourseDao) DaoFactory.getCourseDao();
    private MajorDao majordao = (MajorDao) DaoFactory.getMajorDao();
    private StudiesDao studiesdao= (StudiesDao) DaoFactory.getStudiesDao();

    /**
     * The only constructor, the private no-argument constructor, can only be
     * called from this class within the getInstance method. It should be called
     * exactly once, the first time getInstance is called.
     */
    private CourseController() {
        if (controller == null)
            controller = this;
        else
            throw new IllegalArgumentException("Default constructor called more than once.");
        Courses = new Hashtable<Integer, Course>();
    }

    public static CourseController getInstance() {
        if (controller == null)
            controller = new CourseController();
        return controller;
    }

    public boolean setMajor(Course c, Major m) {
        if (c != null && m != null)
            return dao.update(c);
        return false;
    }

    public Set<Course> getCourses() {
        return dao.findAll();
    }
    
    public Set<Course> getCourseByMajor(Major m) {
    	return dao.findByMajor(m);
    }
    
    public boolean addMajor(String name) {
    	return majordao.create(new Major((int) (Math.random() * 10000) + 20, name));
    }
    
    public boolean removeMajor(Major m) {
    	return majordao.delete(m);
    }

    public boolean addCourse(String name, Major m) {
        if(m != null) return dao.create(new LectureCourse((int) (Math.random() * 10000) + 20, name, m));
        else return dao.create(new OptionalCourse((int) (Math.random() * 10000) + 20, name));
    }

    public boolean removeCourse(Course c) {
        return dao.delete(c);
    }
    
    public Set<Student>  getStudent(Course c) {
    	return studiesdao.getStudentByCourse(c);
    }
    
    public Set<Major> getMajors() {
        return majordao.findAll();
    }

}
