package controller;

import java.util.Hashtable;

import model.Course;
import model.Major;
import model.Student;

public class StudentController {

	private Hashtable<Integer, Student> students;
	
	public StudentController(){
		students = new Hashtable<Integer, Student>();
	}
	
	public void setMajor(int id, Major major) {
		Student s = students.get(id);
		if(s != null)
			s.setMajor(major);
	}
	
	public void addCourse(int id, Course course){
		Student s = students.get(id);
		if(s != null && course != null) {
			if(course.isLecture() && s.getMajor() != null && s.getMajor() == course.getMajor())
				s.addCourse(course);
		}
			
	}
	
	public void addStudent(String name) {
		Student s = new Student(1,name);
		students.put(s.getId(), s);
	}
	
}
