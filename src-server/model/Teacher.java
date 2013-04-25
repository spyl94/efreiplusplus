package model;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8714801050597003528L;
	private String name;
    private int id;

    public Teacher(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Teacher() {
        this.id = 0;
        this.name = "unknown";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public boolean equals(Teacher t) {
        return this.id == t.getId();
    }
}
