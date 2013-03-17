/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Aurel
 *
 */
public abstract class Course implements Serializable{
	
	private String name;
	
	public Course(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isLecture() {
		return false;
	}
	
	public Major getMajor() {
		return null;
	}
}
