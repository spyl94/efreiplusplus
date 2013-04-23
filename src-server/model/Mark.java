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

    public Mark(Student student, Course cours, int mark) {
        this.student = student;
        this.cours = cours;
        this.mark = mark;
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
    
    public boolean equals(Mark m) {
        return this.cours.equals(m.getCours()) && this.student.equals(m.getStudent()) && this.mark == m.getMark();
    }
}
