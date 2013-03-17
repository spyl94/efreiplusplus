package model;

public class LectureCourse extends Course {

	private Major major;
	
	public LectureCourse(){
		super("unknown");
	}
	
	public LectureCourse(String name){
		super(name);
	}
	
	@Override
	public boolean isLecture() {
		return true;
	}
	
	@Override
	public Major getMajor() {
		return this.major;
	}
}
