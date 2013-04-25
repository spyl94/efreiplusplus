package controller;

import java.sql.*;

import model.Course;
import model.LectureCourse;
import model.Major;
import model.OptionalCourse;
import model.Pair;
import model.Student;
import model.Teacher;
import model.User;
import model.dao.CourseDao;
import model.dao.DaoFactory;
import model.dao.MajorDao;
import model.dao.StudentDao;
import model.dao.StudiesDao;
import model.dao.TeacherDao;
import model.dao.TeachesDao;
import model.dao.TutorsDao;
import model.dao.UserDao;

public class DatabaseController {
    private String url = "jdbc:sqlite:efrei.db";
    private static Connection connect;

    public static final String STUDENTS = "create table students (sid integer PRIMARY KEY, sname string, mid integer, isAlert boolean, FOREIGN KEY(mid) REFERENCES majors(mid))";
    public static final String TEACHERS = "create table teachers (tid integer PRIMARY KEY, tname string)";
    public static final String MAJORS = "create table majors (mid integer PRIMARY KEY, mname string)";
    public static final String COURSES = "create table courses (cid integer PRIMARY KEY, mid integer, cname string, ctype string, FOREIGN KEY(mid) REFERENCES majors(mid))";
    public static final String MARKS = "create table marks (id integer PRIMARY KEY, sid integer, cid integer, mark integer, FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(cid) REFERENCES courses(cid))";
    public static final String TEACHES = "create table teaches (tid integer, cid integer, PRIMARY KEY(tid,cid), FOREIGN KEY(tid) REFERENCES teachers(tid), FOREIGN KEY(cid) REFERENCES courses(cid))";
    public static final String TUTORS = "create table tutors (tid integer, sid integer, PRIMARY KEY(sid,tid), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(tid) REFERENCES teachers(tid))";
    public static final String STUDIES = "create table studies (sid integer, cid integer, PRIMARY KEY(sid,cid), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(cid) REFERENCES courses(cid))";
    public static final String USERS = "create table users (id integer, login string, pass string, sid integer, tid integer, role integer, PRIMARY KEY(id), FOREIGN KEY(sid) REFERENCES students(sid), FOREIGN KEY(tid) REFERENCES teachers(tid))";
    
    public static final String[] POPULATE_STUDENTS_NAME = { "Aurélien", "Adrien", "Pierre" };
    public static final String[] POPULATE_TEACHERS_NAME = { "Jean", "Michel", "Busca" };
    public static final String[] POPULATE_MAJORS_NAME = { "Intelligence et Réalité Virutelle", "Systèmes d'Information",
            "Informatique & Finance de Marchés", "Réseau et sécurité" };
    public static final String[] POPULATE_LECTURE_COURSES_NAME = { "3D", "Blender", "C++ Avancé", "Réalité Virtuelle",
            "Collaborative Software", "Advanced Database", "UML avancé", "Modélisation d'un SI",
            "Ingénierie et maths financières 1 & 2", "Statistiques pour la finance I", "Informatique pour la finance", "Langages d'automatisations : VBA",
            "Ingénierie des protocoles", "Sécurité systèmes et réseaux", "Réseaux Haut Débit et Nouvelles Technologies de l’IP", "Systèmes Temps Réel et Mobiles" };
    public static final String[] POPULATE_OPTIONAL_COURSES_NAME = { "Japonais", "Allemand",
        "Espagnol", "Chinois", "Communication", "Anglais" };
    
    public static final String[] POPULATE_USERS_LOGIN = { "aurel", "adrien","pierre","jean","michel","admin"};
    public static final String[] POPULATE_USER_PASS = { "aurel", "adrien","pierre","jean","michel","admin" };

    private StudentDao studentdao = null;
    private TeacherDao teacherdao = null;
    private TeachesDao teachesdao = null;
    private TutorsDao tutorsdao = null;
    private MajorDao majordao = null;
    private CourseDao coursedao = null;
    private StudiesDao studiesdao = null;
    private UserDao userdao = null;

    private DatabaseController() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(url);
            studentdao = (StudentDao) DaoFactory.getStudentDao();
            teacherdao = (TeacherDao) DaoFactory.getTeacherDao();
            teachesdao = (TeachesDao) DaoFactory.getTeachesDao(); 
            tutorsdao = (TutorsDao) DaoFactory.getTutorsDao();
            majordao = (MajorDao) DaoFactory.getMajorDao();
            coursedao = (CourseDao) DaoFactory.getCourseDao();
            studiesdao = (StudiesDao) DaoFactory.getStudiesDao();
            userdao = (UserDao) DaoFactory.getUserDao();
            createDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connect == null) {
            new DatabaseController();
        }
        return connect;
    }

    public void createDatabase() {
        try {
            Statement statement = connect.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(MAJORS);
            statement.executeUpdate(STUDENTS);
            statement.executeUpdate(TEACHERS);
            statement.executeUpdate(COURSES);
            statement.executeUpdate(MARKS);
            statement.executeUpdate(TEACHES);
            statement.executeUpdate(TUTORS);
            statement.executeUpdate(STUDIES);
            statement.executeUpdate(USERS);

            populateDatabase();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateDatabase() {
        System.out.println("Populating Database ...");
        
        int i = 1;
        for (String s : POPULATE_MAJORS_NAME) {
            majordao.create(new Major(i, s));
            i++;
        }
        i = 1;
        for (String s : POPULATE_LECTURE_COURSES_NAME) {
            LectureCourse c = new LectureCourse(i, s);
            c.setMajor(majordao.find(1));
            coursedao.create(c);
            i++;
        }
        for (String s : POPULATE_OPTIONAL_COURSES_NAME) {
           coursedao.create(new OptionalCourse(i, s));
           i++;
        }
        i = 1;
        for (String s : POPULATE_STUDENTS_NAME) {
            Student stu = new Student(i, s);
            stu.setMajor(majordao.find(1));
            studiesdao.create(new Pair<Student,Course>(stu,coursedao.find(1)));
            studentdao.create(stu);
            i++;
        }
        i = 1;
        for (String s : POPULATE_TEACHERS_NAME) {
            Teacher t = new Teacher(i, s);
            teacherdao.create(t);
            teachesdao.create(new Pair<Teacher,Course>(t,coursedao.find(1)));
            teachesdao.create(new Pair<Teacher,Course>(t,coursedao.find(2)));
            teachesdao.create(new Pair<Teacher,Course>(t,coursedao.find(3)));
            tutorsdao.create(new Pair<Teacher,Student>(t,studentdao.find(1)));
            i++;
        }
        i = 0;
        for (String s : POPULATE_USERS_LOGIN) {
            if (i <= 2) userdao.create(new User(s, POPULATE_USER_PASS[i],i+1,0));
            else if (i <= 4) userdao.create(new User(s, POPULATE_USER_PASS[i],0,i-2));
            else  userdao.create(new User(s, POPULATE_USER_PASS[i]));
            i++;
        }
        System.out.println("Database Populated");
    }
}
