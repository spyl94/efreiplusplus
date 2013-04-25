package model;

import java.io.Serializable;

public class Major implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3889951012901259891L;
	private int id;
    private String name;

    public Major() {
        id = 0;
        name = "unknown";
    }

    public Major(int id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    public boolean equals(Major m) {
        return this.id == m.getId();
    }
}
