package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import model.Student;

public class StudentDao extends Dao<Student> {

	public StudentDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Student obj) {

		PreparedStatement addStudent;
		try {
			addStudent = conn
					.prepareStatement("INSERT INTO Student (sid,sname) VALUES(?,?)");
			addStudent.setInt(1, obj.getId());
			addStudent.setString(2, obj.getName());
			return addStudent.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Student obj) {
		try {
			return !this.conn
					.createStatement()
					.execute(
							"DELETE FROM student WHERE sid = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Student obj) {
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM student WHERE sid = "
							+ obj.getId());
			rs.first();
			rs.updateString("sname", obj.getName());
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
	public Student find(int id) {
		Student student = null;
		try {
			ResultSet rs = this.conn.createStatement().executeQuery(
					"SELECT * FROM student WHERE sid = " + id);
			if (rs.first())
				student = new Student(id, rs.getString("sname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Set<Student> findAll() {
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from student");
			while (rs.next()) {
				System.out.println("name = " + rs.getString("sname"));
				System.out.println("sid = " + rs.getInt("sid"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
