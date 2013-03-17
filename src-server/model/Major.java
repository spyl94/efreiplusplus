package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Major implements Serializable {
	private Set<Course> courses;
	String name;
	
	public Major() {
		courses = new HashSet<Course>();
	}
	
	public Major(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Major m) {
		return this.name.equals(m.getName());
	}
 }
