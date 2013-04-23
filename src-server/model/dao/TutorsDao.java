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
import model.Student;

public class TutorsDao extends Dao<Pair<Teacher, Student>> {

    private StudentDao student;
    public TutorsDao(Connection conn) {
        super(conn);
        student = (StudentDao) DaoFactory.getStudentDao();
    }

    @Override
    public boolean create(Pair<Teacher, Student> obj) {
        PreparedStatement add;
        try {
            add = conn.prepareStatement("INSERT INTO tutors (tid,sid) VALUES(?,?)");
            add.setInt(1, obj.getLeft().getId());
            add.setInt(2, obj.getRight().getId());
            return add.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Pair<Teacher, Student> obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM tutors WHERE tid = " + obj.getLeft().getId() + " and sid =" + obj.getRight().getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Teacher obj) {
        try {
            return !this.conn.createStatement().execute("DELETE FROM tutors WHERE tid = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Pair<Teacher, Student> obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pair<Teacher, Student> find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Pair<Teacher, Student>> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Set<Student> findStudentByTeacher(Teacher t) {       
        Set<Student> set = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from tutors where tid = " + t.getId());
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
