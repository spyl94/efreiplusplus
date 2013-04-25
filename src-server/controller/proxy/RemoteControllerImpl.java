package controller.proxy;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import controller.CourseController;
import controller.DatabaseController;
import controller.MainController;
import controller.StudentController;
import controller.TeacherController;

import model.Course;
import model.Major;
import model.Mark;
import model.Student;
import model.Teacher;
import model.User;

public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3441443474490088855L;
	StudentController student;
    TeacherController teacher;
    CourseController course;
    User user;
    
    ROLE role;
    
    public RemoteControllerImpl() throws RemoteException {
        DatabaseController.getConnection();
        student = StudentController.getInstance();
        teacher = TeacherController.getInstance();
        course = CourseController.getInstance();
        user = null;
        role = ROLE.ANONYMOUS;
    }
    
    public void setUser(User u) {
        user = u;
    }
    
    @Override
    public User getUser() {
        return user;
    }
    
    public void setGranted(ROLE r) {
        role = r;
    }
    
    @Override
    public ROLE getGranted() {
        return role;
    }

    @Override
    public Set<Teacher> getTeachers() {
        return teacher.getTeachers();
    }

    @Override
    public boolean addTeacher(String name) {
        return teacher.addTeacher(name);
    }

    @Override
    public Set<Student> getStudents() throws RemoteException {
        return student.getStudents();
    }

    @Override
    public boolean addStudent(String name) throws RemoteException {
        return student.addStudent(name);
    }

    @Override
    public boolean removeTeacher(Teacher t) throws RemoteException {
        return teacher.removeTeacher(t);
    }

    @Override
    public boolean addTeacherCourse(Teacher t, Course c) throws RemoteException {
        return teacher.addCourse(t, c);
    }

    @Override
    public boolean addStudentCourse(Student s, Course c) throws RemoteException {
        return student.addCourse(s, c);
    }

    @Override
    public boolean addStudentMajor(Student s, Major m) throws RemoteException {
        return student.setMajor(s, m);
    }

    @Override
    public Set<Mark> getStudentMark(Student s) throws RemoteException {
        return student.getMark(s);
    }

    @Override
    public boolean removeStudent(Student s) throws RemoteException {
        return student.removeStudent(s);
    }

    @Override
    public boolean addCourse(String name, Major m) throws RemoteException {
        return course.addCourse(name,m);
    }

    @Override
    public Set<Course> getCourses() throws RemoteException {
        return course.getCourses();
    }

    @Override
    public boolean addMark(Student s, Course c, int mark) throws RemoteException {
        return student.addMark(s, c, mark);
    }

    @Override
    public Set<Course> getTeacherCourse(Teacher t) throws RemoteException {
        return teacher.getCourse(t);
    }

    @Override
    public boolean addTutorStudent(Teacher t, Student s) throws RemoteException {
        return teacher.addStudentForTutor(t, s);
    }

    @Override
    public Set<Student> getStudentsFromTutor(Teacher t) throws RemoteException {
        return teacher.getStudentFromTutor(t);
    }

    @Override
    public Set<Major> getMajors() throws RemoteException {
        return course.getMajors();
    }

	@Override
	public RemoteController login(String user, String pass)
			throws RemoteException {
		return MainController.getInstance().getProxy(user, pass);
	}

	@Override
	public RemoteController logout() throws RemoteException {
		return MainController.getInstance().getProxy("","");
	}

	@Override
	public Set<Course> getStudentCourse(Student s) throws RemoteException {
		return student.getCourse(s);
	}

	@Override
	public Set<Student> getCourseStudent(Course c) throws RemoteException {
		return course.getStudent(c);
	}

	@Override
	public boolean removeStudentCourse(Student s, Course c)
			throws RemoteException {
		return student.removeCourseToStudent(s,c);
	}

	@Override
	public Set<Course> getCoursesByMajor(Major m) throws RemoteException {
		return course.getCourseByMajor(m);
	}

	@Override
	public boolean addMajor(String name) throws RemoteException {
		return course.addMajor(name);
	}

	@Override
	public boolean removeMajor(Major m) throws RemoteException {
		return course.removeMajor(m);
	}

	@Override
	public boolean removeCourse(Course c) throws RemoteException {
		return course.removeCourse(c);
	}

	@Override
	public boolean removeTeacherCourse(Teacher t, Course c)
			throws RemoteException {
		return teacher.removeCourse(t, c);
	}

	@Override
	public boolean removeTutorStudent(Teacher t, Student s)
			throws RemoteException {
		return teacher.removeStudentForTutor(t, s);
	}

}
