package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import model.Student;
import model.Teacher;

public class TeacherDao extends Dao<Teacher> {

	public TeacherDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Teacher obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Teacher obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Teacher obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Teacher find(int id) {
		Teacher Teacher = null;
		try {
		ResultSet rs = this.conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM Teacher WHERE sid = " + id);
		if(rs.first())
			Teacher = new Teacher(id,rs.getString("tname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Teacher;
	}
	
	@Override
	public Set<Teacher> findAll() {
		return null;
	}

}
