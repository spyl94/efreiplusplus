package model;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Teacher implements Serializable {
	
	private String name;
	private int id;
	private Set<Course> courses;
	
	public Teacher(int id, String name) {
		this.name = name;
		this.id = id;
		courses = new HashSet<Course>();
	}
	
	public Teacher() {
		this.id = 0;
		this.name = "unknown";
	}
}
