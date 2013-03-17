package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student implements Serializable {
	
	private String name;
	private int id;
	private Set<Course> courses;
	private Major major;
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		courses = new HashSet<Course>();
	}
	
	public Student() {
		this.id = 0;
		this.name = "unknown";
	}
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addCourse(Course course) {
		if(course != null)
			courses.add(course);
	}
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		if(major != null)
			this.major = major;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	
}
