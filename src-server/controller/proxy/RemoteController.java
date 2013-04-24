package controller.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import model.*;

public interface RemoteController extends Remote {
    
    public enum ROLE {
        ANONYMOUS, ADMIN, STUDENT, TEACHER
    }
    
    // Teacher
    public Set<Teacher> getTeachers() throws RemoteException;

    public boolean addTeacher(String name) throws RemoteException;

    public boolean removeTeacher(Teacher t) throws RemoteException;

    public boolean addTeacherCourse(Teacher t, Course c) throws RemoteException;
    
    public Set<Course> getTeacherCourse(Teacher t) throws RemoteException;
    
    public boolean addTutorStudent(Teacher t, Student s) throws RemoteException;
    
    public Set<Student> getStudentsFromTutor(Teacher t) throws RemoteException;
    
    // Student
    public Set<Student> getStudents() throws RemoteException;

    public boolean addStudent(String name) throws RemoteException;

    public boolean addStudentCourse(Student s, Course c) throws RemoteException;

    public boolean addStudentMajor(Student s, Major m) throws RemoteException;

    public Set<Mark> getStudentMark(Student s) throws RemoteException;
    
    public Set<Course> getStudentCourse(Student s) throws RemoteException;

    public boolean removeStudent(Student s) throws RemoteException;

    public boolean addCourse(String name) throws RemoteException;
    
    // Course
    public Set<Course> getCourses() throws RemoteException;
    public Set<Major> getMajors() throws RemoteException;
    
    // Mark
    public boolean addMark(Student s, Course c, int mark) throws RemoteException;
    
    
    // Other
    public RemoteController login(String user, String pass) throws RemoteException;
    public RemoteController logout() throws RemoteException;
    public User getUser();
    public ROLE getGranted();
}
