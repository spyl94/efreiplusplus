package model.dao;

import java.sql.Connection;

import controller.DatabaseController;

public class DaoFactory {
	
	protected static final Connection conn = DatabaseController.getConnection();

	/**
	* @return DAO
	*/
	public static Dao<?> getStudentDao(){
		return new StudentDao(conn);
	}
	
	/**
	* @return DAO
	*/
	public static Dao<?> getTeacherDao(){
		return new TeacherDao(conn);
	}
	
	/**
	* @return DAO
	*/
	public static Dao<?> getCourseDao(){
		return new CourseDao(conn);
	}
	
	/**
	* @return DAO
	*/
	public static Dao<?> getMarkDao(){
		return new MarkDao(conn);
	}

}
