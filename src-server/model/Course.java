/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Aurel
 * 
 */
public abstract class Course implements Serializable {

    private String name;
    private int id;

    public Course() {
        this.id = 0;
        this.name = "unknown";
    }

    public Course(int i, String name) {
        this.id = i;
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

    public int getId() {
        return id;
    }
    
    public boolean equals(Course c) {
        return this.id == c.getId();
    }
}
