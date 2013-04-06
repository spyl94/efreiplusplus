package controller;

import java.util.Hashtable;
import java.util.Set;

import model.Course;
import model.Major;
import model.Mark;
import model.Student;
import model.dao.DaoFactory;
import model.dao.MarkDao;
import model.dao.StudentDao;

public class StudentController {
	
	private static StudentController controller;
	private Hashtable<Integer, Student> students;
	private StudentDao dao = (StudentDao) DaoFactory.getStudentDao();
	private MarkDao mark = (MarkDao) DaoFactory.getMarkDao();
	
	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private StudentController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
		students = new Hashtable<Integer, Student>();
	}
	
	public static StudentController getInstance() {
		if(controller == null)
			controller = new StudentController();
		return controller;
	}
	
	public void setMajor(int id, Major major) {
		Student s = students.get(id);
		if(s != null)
			s.setMajor(major);
		dao.update(s);
	}
	
	public void addCourse(int id, Course course){
		Student s = students.get(id);
		if(s != null && course != null) {
			if(course.isLecture() && s.getMajor() != null && s.getMajor() == course.getMajor())
				s.addCourse(course);
		}
			
	}
	
	public boolean addMark(Student s, Course c, int mark) {
		if(mark >= 0 && mark <= 20) {
			return mark.create(new Mark(s,c,mark));
		}
		return false;
	}
	
	public Set<Student> getStudents() {
		return dao.findAll();
	}
	
	public boolean addStudent(String name) {
		Student s = new Student(1,name);
		students.put(s.getId(), s);
		return dao.create(s);
	}
	
	public boolean removeStudent(int id) {
		Student s = students.get(id);
		students.remove(id);
		return dao.delete(s);
	}
	
}
