package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import view.DialogBox;
import view.MainWindow;

import model.Course;
import model.Major;
import model.Student;
import model.Teacher;

public class ViewAdmin extends JFrame {

	private JPanel studentTop = new JPanel();
	private JPanel studentBot = new JPanel();
	private JPanel studentAll = new JPanel();
	private JTextField studentName = new JTextField();
	private JComboBox studentCombo = new JComboBox();
	private JButton addStudent = new JButton("Ajouter");
	private JButton remStudent = new JButton("Retirer");
	private Set<Student> students = null;

	private JPanel teacherTop = new JPanel();
	private JPanel teacherMid = new JPanel();
	private JPanel teacherBot = new JPanel();
	private JPanel buttonPan = new JPanel();
	private JPanel teacherAll = new JPanel();
	private JTextField teacherName = new JTextField();
	private JComboBox teacherCombo = new JComboBox();
	private JComboBox choiceTeacherCombo = new JComboBox();
	private JButton addTeacher = new JButton("Ajouter");
	private JButton remTeacher = new JButton("Retirer");
	private JButton manage = new JButton("Attribution cours");
	private Set<Teacher> teachers = null;

	private JPanel majorTop = new JPanel();
	private JPanel majorBot = new JPanel();
	private JPanel majorAll = new JPanel();
	private JTextField majorName = new JTextField();
	private JComboBox majorCombo = new JComboBox();
	private JButton addMajor = new JButton("Ajouter");
	private JButton remMajor = new JButton("Retirer");
	private Set<Major> majors = null;

	private JPanel courseTop = new JPanel();
	private JPanel courseBot = new JPanel();
	private JPanel courseAll = new JPanel();
	private JTextField courseName = new JTextField();
	private JComboBox courseCombo = new JComboBox();
	private JComboBox courseMajorCombo = new JComboBox();
	private JButton addCourse = new JButton("Ajouter");
	private JButton remCourse = new JButton("Retirer");
	private Set<Course> courses = null;

	public ViewAdmin() {
		this.setTitle("Interface administrateur");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Student
		try {
			students = MainWindow.getInstance().getStub().getStudents();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (Student i : students) {
			studentCombo.addItem(i.getName());
		}

		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Major test = new Major();
				try {
					MainWindow.getInstance().getStub()
							.addStudent(studentName.getText());

					Set<Student> temp = MainWindow.getInstance().getStub()
							.getStudents();
					for (Student i : temp) {
						if (i.getName().equals(studentName.getText())) {
							System.out.println("Ajout de :" + i.getName()
									+ " avec " + test.getName());
							MainWindow.getInstance().getStub()
									.addStudentMajor(i, test);
						}
					}

				} catch (RemoteException e) {
					e.printStackTrace();
				}
				studentName.setText("");
				studentCombo.removeAllItems();
				try {
					students = MainWindow.getInstance().getStub().getStudents();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Student i : students) {
					studentCombo.addItem(i.getName());
				}
			}
		});

		remStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Student actual = null;
				for (Student i : students) {
					if (i.getName().equals(
							studentCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}

				try {
					MainWindow.getInstance().getStub().removeStudent(actual);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				studentCombo.removeAllItems();
				try {
					students = MainWindow.getInstance().getStub().getStudents();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Student i : students) {
					studentCombo.addItem(i.getName());
				}
			}
		});

		studentName.setPreferredSize(new Dimension(150, 25));
		studentAll.setLayout(new BorderLayout());
		studentTop.add(studentName);
		studentTop.add(addStudent);
		studentBot.add(studentCombo);
		studentBot.add(remStudent);
		studentAll.add(studentTop, BorderLayout.NORTH);
		studentAll.add(studentBot, BorderLayout.CENTER);

		// Teacher
		try {
			teachers = MainWindow.getInstance().getStub().getTeachers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (Teacher i : teachers) {
			teacherCombo.addItem(i.getName());
		}

		for (Teacher i : teachers) {
			choiceTeacherCombo.addItem(i.getName());
		}

		addTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MainWindow.getInstance().getStub()
							.addTeacher(teacherName.getText());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				teacherName.setText("");
				teacherCombo.removeAllItems();
				choiceTeacherCombo.removeAllItems();
				try {
					teachers = MainWindow.getInstance().getStub().getTeachers();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Teacher i : teachers) {
					teacherCombo.addItem(i.getName());
				}
				for (Teacher i : teachers) {
					choiceTeacherCombo.addItem(i.getName());
				}
			}
		});

		remTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Teacher actual = null;
				for (Teacher i : teachers) {
					if (i.getName().equals(
							teacherCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}

				try {
					MainWindow.getInstance().getStub().removeTeacher(actual);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				teacherCombo.removeAllItems();
				choiceTeacherCombo.removeAllItems();
				try {
					teachers = MainWindow.getInstance().getStub().getTeachers();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Teacher i : teachers) {
					teacherCombo.addItem(i.getName());
				}
				for (Teacher i : teachers) {
					choiceTeacherCombo.addItem(i.getName());
				}
			}
		});

		manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Teacher actual = null;
				for (Teacher i : teachers) {
					if (i.getName().equals(
							choiceTeacherCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}
				new PopCourseTeacher(actual);
			}
		});

		buttonPan.add(choiceTeacherCombo);
		buttonPan.add(manage);
		teacherName.setPreferredSize(new Dimension(150, 25));
		teacherAll.setLayout(new BorderLayout());
		teacherTop.add(teacherName);
		teacherTop.add(addTeacher);
		teacherMid.add(teacherCombo);
		teacherMid.add(remTeacher);
		teacherAll.add(teacherTop, BorderLayout.NORTH);
		teacherAll.add(teacherMid, BorderLayout.CENTER);
		teacherAll.add(buttonPan, BorderLayout.SOUTH);

		// Major
		try {
			majors = MainWindow.getInstance().getStub().getMajors();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (Major i : majors) {
			if (i.getId() != 0) {
				majorCombo.addItem(i.getName());
			}
		}

		addMajor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MainWindow.getInstance().getStub()
							.addMajor(majorName.getText());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				majorName.setText("");
				majorCombo.removeAllItems();
				courseMajorCombo.removeAllItems();
				try {
					majors = MainWindow.getInstance().getStub().getMajors();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Major i : majors) {
					if (i.getId() != 0) {
						majorCombo.addItem(i.getName());
					}
				}

				for (Major i : majors) {
					if (i.getId() != 0) {
						courseMajorCombo.addItem(i.getName());
					}
				}
				courseMajorCombo.addItem("Optionnel");
			}
		});

		remMajor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Major actual = null;
				for (Major i : majors) {
					if (i.getName().equals(
							majorCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}

				try {
					MainWindow.getInstance().getStub().removeMajor(actual);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				majorCombo.removeAllItems();
				courseMajorCombo.removeAllItems();
				try {
					majors = MainWindow.getInstance().getStub().getMajors();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Major i : majors) {
					if (i.getId() != 0) {
						majorCombo.addItem(i.getName());
					}
				}

				for (Major i : majors) {
					if (i.getId() != 0) {
						courseMajorCombo.addItem(i.getName());
					}
				}
				courseMajorCombo.addItem("Optionnel");
			}
		});

		majorName.setPreferredSize(new Dimension(150, 25));
		majorAll.setLayout(new BorderLayout());
		majorTop.add(majorName);
		majorTop.add(addMajor);
		majorBot.add(majorCombo);
		majorBot.add(remMajor);
		majorAll.add(majorTop, BorderLayout.NORTH);
		majorAll.add(majorBot, BorderLayout.CENTER);

		// Course

		try {
			courses = MainWindow.getInstance().getStub().getCourses();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (Course i : courses) {
			courseCombo.addItem(i.getName());
		}

		for (Major i : majors) {
			if (i.getId() != 0) {
				courseMajorCombo.addItem(i.getName());
			}
		}
		courseMajorCombo.addItem("Optionnel");

		addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Major actual = null;
				for (Major i : majors) {
					if (i.getName().equals(
							courseMajorCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}
				try {
					MainWindow.getInstance().getStub()
							.addCourse(courseName.getText(), actual);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				courseName.setText("");
				courseCombo.removeAllItems();
				try {
					courses = MainWindow.getInstance().getStub().getCourses();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Course i : courses) {
					courseCombo.addItem(i.getName());
				}
			}
		});

		remCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Course actual = null;
				for (Course i : courses) {
					if (i.getName().equals(
							courseCombo.getSelectedItem().toString())) {
						actual = i;
					}
				}

				try {
					MainWindow.getInstance().getStub().removeCourse(actual);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				courseCombo.removeAllItems();
				try {
					courses = MainWindow.getInstance().getStub().getCourses();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				for (Course i : courses) {
					courseCombo.addItem(i.getName());
				}
			}
		});

		courseName.setPreferredSize(new Dimension(150, 25));
		courseAll.setLayout(new BorderLayout());
		courseTop.add(courseMajorCombo);
		courseTop.add(courseName);
		courseTop.add(addCourse);
		courseBot.add(courseCombo);
		courseBot.add(remCourse);
		courseAll.add(courseTop, BorderLayout.NORTH);
		courseAll.add(courseBot, BorderLayout.CENTER);

		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Gestion élèves", studentAll);
		tab.addTab("Gestion professeurs", teacherAll);
		tab.addTab("Gestion majeures", majorAll);
		tab.addTab("Gestion cours", courseAll);

		this.setContentPane(tab);
		this.setVisible(true);

	}
}
