package controller;

import java.sql.*;

public class DatabaseController 
{
	private String url = "jdbc:sqlite:efrei.db";
	private static Connection connect;
	
	public static final String STUDENTS = "create table students (sid integer PRIMARY KEY, sname string)";
	public static final String TEACHERS = "create table teachers (tid integer PRIMARY KEY, tname string)";
	public static final String MAJORS = "create table majors (mid integer PRIMARY KEY, mname string)";
	public static final String COURSES = "create table courses (cid integer PRIMARY KEY, cname string)";
	public static final String MARKS = "create table marks (sid integer, cid integer, mark integer, PRIMARY KEY(sid,cid), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(cid) REFERENCES courses(cid))";
	public static final String TEACHES = "create table teaches (tid integer, cid integer, PRIMARY KEY(tid,cid), FOREIGN KEY(tid) REFERENCES teachers(tid), FOREIGN KEY(cid) REFERENCES courses(cid))";
	
	public static final String STUDENTS_MAJORS = "create table students_majors (sid integer, mid integer, PRIMARY KEY(sid,mid), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(mid) REFERENCES majors(mid))";
	public static final String STUDIES = "create table studies (sid integer, cid integer, PRIMARY KEY(sid,cid), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(cid) REFERENCES courses(cid))";
	
	
	private DatabaseController(){
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
			createDatabase();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		if(connect == null){
			new DatabaseController();
		}
		return connect;
	}
	
	public void createDatabase() {
		try {
			Statement statement = connect.createStatement();
			statement.setQueryTimeout(30);
			
			statement.executeUpdate(STUDENTS);
		    statement.executeUpdate(TEACHERS);
		    statement.executeUpdate(MAJORS);
		    statement.executeUpdate(COURSES);
		    statement.executeUpdate(MARKS);
		    
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
