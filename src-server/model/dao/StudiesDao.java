package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Pair;
import model.Student;
import model.Course;

public class StudiesDao extends Dao<Pair<Student, Course>> {

    private CourseDao course;
    private StudentDao student;
    
    public StudiesDao(Connection conn) {
        super(conn);
        course = (CourseDao) DaoFactory.getCourseDao();
        student = (StudentDao) DaoFactory.getStudentDao();
    }

    @Override
    public boolean create(Pair<Student, Course> obj) {
        PreparedStatement add;
        try {
            add = conn.prepareStatement("INSERT INTO studies (sid,cid) VALUES(?,?)");
            add.setInt(1, obj.getLeft().getId());
            add.setInt(2, obj.getRight().getId());
            return add.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Pair<Student, Course> obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM studies WHERE sid = " + obj.getLeft().getId() + " and cid =" + obj.getRight().getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Student obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM studies WHERE sid = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Course obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM studies WHERE cid = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Pair<Student, Course> obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pair<Student, Course> find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Pair<Student, Course>> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Set<Course> getCourseByStudent(Student t) {       
        Set<Course> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from studies where sid = " + t.getId());
            set = new HashSet<Course>();
            while (rs.next()) {
                set.add(course.find(rs.getInt("cid")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }
    
    public Set<Student> getStudentByCourse(Course c) {       
        Set<Student> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from studies where cid = " + c.getId());
            set = new HashSet<Student>();
            while (rs.next()) {
                set.add(student.find(rs.getInt("sid")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }

}
