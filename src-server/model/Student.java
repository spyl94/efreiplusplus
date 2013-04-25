package model;

import java.io.Serializable;

public class Student extends User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7557630722984163909L;
	private String name;
    private int id;
    private Major major;
    private boolean isAlert;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.isAlert = false;
    }
    
    public Student(int id, String name, Major major, boolean alert) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.isAlert = alert;
    }

    public Student() {
        this.id = 0;
        this.name = "unknown";
    }
    
    public boolean isAlerted() {
    	return isAlert;
    }
    
    public void setAlerted() {
    	this.isAlert = true;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        if (major != null)
            this.major = major;
    }

    public boolean equals(Student s) {
        return this.id == s.getId();
    }

}
