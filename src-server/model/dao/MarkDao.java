package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Mark;
import model.Student;

public class MarkDao extends Dao<Mark> {

    private StudentDao student;
    private CourseDao course;
    public MarkDao(Connection conn) {
        super(conn);
        student = (StudentDao) DaoFactory.getStudentDao();
        course = (CourseDao) DaoFactory.getCourseDao();
    }

    @Override
    public boolean create(Mark obj) {
        PreparedStatement addStudent;
        try {
            addStudent = conn.prepareStatement("INSERT INTO marks (sid,cid,mark) VALUES(?,?,?)");
            addStudent.setInt(1, obj.getStudent().getId());
            addStudent.setInt(2, obj.getCours().getId());
            addStudent.setInt(3, obj.getMark());
            return addStudent.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Mark obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM marks WHERE sid = " + obj.getStudent().getId() + " and cid =" + obj.getCours().getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Mark obj) {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM marks WHERE sid = " + obj.getStudent().getId() +" and cid =" + obj.getCours().getId());
            rs.first();
            rs.updateInt("mark", obj.getMark());
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
    public Mark find(int id) {
        return null;
    }

    @Override
    public Set<Mark> findAll() {
        Set<Mark> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from marks");
            set = new HashSet<Mark>();
            while (rs.next()) {
                set.add(new Mark(student.find(rs.getInt("sid")), course.find(rs.getInt("cid")), rs.getInt("mark")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }

    public Set<Mark> findByStudent(int id) {
        Set<Mark> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from marks where sid =" + id);
            set = new HashSet<Mark>();
            Student s = student.find(id);
            if(s == null) return null;
            while (rs.next()) {
                set.add(new Mark(s, course.find(rs.getInt("cid")), rs.getInt("mark")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }

}
