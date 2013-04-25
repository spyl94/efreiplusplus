package controller;

import java.util.Observable;
import java.util.Set;

import model.Course;
import model.Major;
import model.Mark;
import model.Pair;
import model.Student;
import model.User;
import model.dao.DaoFactory;
import model.dao.MarkDao;
import model.dao.StudentDao;
import model.dao.StudiesDao;
import model.dao.UserDao;

public class StudentController extends Observable {

    private static StudentController controller;
    private StudentDao dao;
    private MarkDao markdao;
    private StudiesDao studiesdao;
    private UserDao userdao;

    /**
     * The only constructor, the private no-argument constructor, can only be
     * called from this class within the getInstance method. It should be called
     * exactly once, the first time getInstance is called.
     */
    private StudentController() {
        if (controller == null)
            controller = this;
        else
            throw new IllegalArgumentException("Default constructor called more than once.");
        dao = (StudentDao) DaoFactory.getStudentDao();
        markdao = (MarkDao) DaoFactory.getMarkDao();
        studiesdao = (StudiesDao) DaoFactory.getStudiesDao();
        userdao = (UserDao) DaoFactory.getUserDao();
    }

    public static StudentController getInstance() {
        if (controller == null)
            controller = new StudentController();
        return controller;
    }

    public boolean setMajor(Student s, Major major) {
        if (s != null && major != null) {
            s.setMajor(major);
            return dao.update(s);
        }
         return false;  
    }

    public boolean addCourse(Student s, Course course) {
        if (s != null && course != null) {
            if (!course.isLecture() || (s.getMajor() != null && s.getMajor() == course.getMajor()))
                studiesdao.create(new Pair<Student,Course>(s,course));
    
        }
        return false;
    }
    
    public boolean setAlerted(Student s) {
    	s.setAlerted();
    	return dao.update(s);
    }

    public boolean addMark(Student s, Course c, int mark) {
        if ((mark >= 0 && mark <= 20) && s != null && c != null) {
            if (mark < 10 ) {
                setChanged();
                notifyObservers(s);
            }
            return markdao.create(new Mark(s, c, mark));
        }
        return false;
    }

    public Set<Mark> getMark(Student s) {
        return markdao.findByStudent(s.getId());
    }

    public Set<Student> getStudents() {
        return dao.findAll();
    }

    public boolean addStudent(String name) {
        Student s = new Student((int) (Math.random() * 10000) + 20, name);
        s.setMajor(new Major());
        userdao.create(new User(s.getName(),s.getName(),s.getId(),0));
        return dao.create(s);
    }

    public boolean removeStudent(Student s) {
        return dao.delete(s) && studiesdao.delete(s);
    }

	public Set<Course> getCourse(Student s) {
		return studiesdao.getCourseByStudent(s);
	}

	public boolean removeCourseToStudent(Student s, Course c) {
		return studiesdao.delete(new Pair<Student,Course>(s,c));
	}


}
