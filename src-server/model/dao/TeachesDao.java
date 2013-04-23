package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.Pair;
import model.Teacher;
import model.Course;

public class TeachesDao extends Dao<Pair<Teacher, Course>> {

    private CourseDao course;
    public TeachesDao(Connection conn) {
        super(conn);
        course = (CourseDao) DaoFactory.getCourseDao();
    }

    @Override
    public boolean create(Pair<Teacher, Course> obj) {
        PreparedStatement add;
        try {
            add = conn.prepareStatement("INSERT INTO teaches (tid,cid) VALUES(?,?)");
            add.setInt(1, obj.getLeft().getId());
            add.setInt(2, obj.getRight().getId());
            return add.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Pair<Teacher, Course> obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM teaches WHERE tid = " + obj.getLeft().getId() + " and cid =" + obj.getRight().getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Teacher obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM teaches WHERE tid = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Pair<Teacher, Course> obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pair<Teacher, Course> find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Pair<Teacher, Course>> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Set<Course> getCourseByTeacher(Teacher t) {       
        Set<Course> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from teaches where tid = " + t.getId());
            set = new HashSet<Course>();
            while (rs.next()) {
                set.add(course.find(rs.getInt("cid")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return set;
    }

}
