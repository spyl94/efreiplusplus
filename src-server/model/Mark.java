/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Aurel
 *
 */
public class Mark implements Serializable {
	private Course cours;
	private int mark;
	private Student student;
	
	public Mark(Course cours, int mark, Student student) {
		this.cours = cours;
		this.mark = mark;
		this.student = student;
	}

	public Course getCours() {
		return cours;
	}

	public int getMark() {
		return mark;
	}

	public Student getStudent() {
		return student;
	}
}
