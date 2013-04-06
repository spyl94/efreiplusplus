package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Student;
import model.Teacher;

public class TeacherDao extends Dao<Teacher> {

	public TeacherDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Teacher obj) {
		PreparedStatement addStudent;
		try {
			addStudent = conn.prepareStatement("INSERT INTO teachers (tid,sname) VALUES(?,?)");
			addStudent.setInt(1, obj.getId());
			addStudent.setString(2, obj.getName());
			return addStudent.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Teacher obj) {
		try {
			return !this.conn
					.createStatement()
					.execute(
							"DELETE FROM teachers WHERE tid = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Teacher obj) {
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM teachers WHERE tid = "
							+ obj.getId());
			rs.first();
			rs.updateString("tname", obj.getName());
			rs.updateRow();
			rs.close();
			stat.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Teacher find(int id) {
		Teacher Teacher = null;
		try {
		ResultSet rs = this.conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT * FROM teachers WHERE tid = " + id);
		if(rs.first())
			Teacher = new Teacher(id,rs.getString("tname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Teacher;
	}
	
	@Override
	public Set<Teacher> findAll() {
		Set<Teacher> set = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from teachers");
			set = new HashSet<Teacher>();
			while (rs.next()) {
				set.add(new Teacher(rs.getInt("tid"),rs.getString("tname")));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return set;
	}

}
