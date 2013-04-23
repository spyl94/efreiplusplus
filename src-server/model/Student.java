package model;

import java.io.Serializable;

public class Student extends User implements Serializable {

    private String name;
    private int id;
    private Major major;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
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
