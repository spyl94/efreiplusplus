package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Major implements Serializable {
	private Set<Course> courses;
	
	public Major() {
		courses = new HashSet<Course>();
	}
 }
