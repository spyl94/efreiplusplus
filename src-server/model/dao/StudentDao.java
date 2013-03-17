package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import model.Course;
import model.Student;

public class StudentDao extends Dao<Student> {

	public StudentDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Student obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Student obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Student obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student find(int id) {
		Student student = null;
		try {
		ResultSet rs = this.conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM student WHERE sid = " + id);
		if(rs.first())
			student = new Student(id,rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@Override
	public Set<Student> findAll() {
		return null;
	}

}
