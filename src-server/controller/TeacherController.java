package controller;

import java.util.Hashtable;
import java.util.Set;

import model.Course;
import model.Teacher;
import model.dao.DaoFactory;
import model.dao.TeacherDao;

public class TeacherController {
	
	private static TeacherController controller;
	private Hashtable<Integer, Teacher> teachers;
	private TeacherDao dao = (TeacherDao) DaoFactory.getTeacherDao();
	
	/**
	 * The only constructor, the private no-argument constructor, can only be
	 * called from this class within the getInstance method. It should be called
	 * exactly once, the first time getInstance is called.
	 */
	private TeacherController() {
		if (controller == null)
			controller = this;
		else
			throw new IllegalArgumentException(
					"Default constructor called more than once.");
		teachers = new Hashtable<Integer, Teacher>();
	}
	
	public static TeacherController getInstance() {
		if(controller == null)
			controller = new TeacherController();
		return controller;
	}
	
	public void addCourse(int id, Course course){
		Teacher s = teachers.get(id);
		/*if(s != null && course != null) {
			if(course.isLecture() && s.getMajor() != null && s.getMajor() == course.getMajor())
				s.addCourse(course);*/
		//}
			
	}
	
	public Set<Teacher> getTeachers() {
		return dao.findAll();
	}
	
	public boolean addTeacher(String name) {
		Teacher s = new Teacher(1,name);
		//Teachers.put(s.getId(), s);
		return dao.create(s);
	}
	
	public boolean removeTeacher(Teacher t) {
		return dao.delete(t);
	}
	
}
