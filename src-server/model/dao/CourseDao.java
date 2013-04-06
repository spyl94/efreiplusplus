package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import model.Course;
import model.LectureCourse;
import model.OptionalCourse;

public class CourseDao extends Dao<Course> {

	public CourseDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Course obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Course obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Course obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Course find(int id) {
		Course course = null;
		try {
		ResultSet rs = this.conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM courses WHERE cid = " + id);
		if(rs.first())
			course = new LectureCourse();
			course = new OptionalCourse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}
	
	@Override
	public Set<Course> findAll() {
		return null;
	}

}
