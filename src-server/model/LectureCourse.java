package model;

public class LectureCourse extends Course {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8502141057323439098L;
	private Major major;

    public LectureCourse() {
        super();
    }

    public LectureCourse(int id, String name) {
        super(id, name);
    }
    
    public LectureCourse(int id, String name, Major m) {
        super(id, name);
        major = m;
    }

    @Override
    public boolean isLecture() {
        return true;
    }
    
    public void setMajor(Major major) {
        if(major != null)
            this.major = major;
    }

    @Override
    public Major getMajor() {
        return this.major;
    }
}
