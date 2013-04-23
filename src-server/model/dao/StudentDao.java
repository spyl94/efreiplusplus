package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
            addStudent = conn.prepareStatement("INSERT INTO students (sid,sname,mid) VALUES(?,?,?)");
            addStudent.setInt(1, obj.getId());
            addStudent.setString(2, obj.getName());
            if(obj.getMajor() != null) addStudent.setInt(3, obj.getMajor().getId());
            else addStudent.setNull(3, 0);
            return addStudent.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Student obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM students WHERE sid = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student obj) {
        try {
            String req = "update students set sname = ?, mid = ? where sid = " + obj.getId();
            PreparedStatement updateStudent = conn.prepareStatement(req);
            updateStudent.setString(1, obj.getName());
            updateStudent.setInt(2, obj.getMajor().getId());
            return updateStudent.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student find(int id) {
        Student student = null;
        try {
            ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM students WHERE sid = " + id);
                student = new Student(id, rs.getString("sname"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Set<Student> findAll() {
        Set<Student> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from students");
            set = new HashSet<Student>();
            while (rs.next()) {
                set.add(new Student(rs.getInt("sid"), rs.getString("sname")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }

}
