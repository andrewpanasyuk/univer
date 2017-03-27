package com.andrewpanasyuk.service.serviceImpl;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoImpl.GroupDaoImpl;
import com.andrewpanasyuk.dao.daoImpl.StudentDaoImpl;
import com.andrewpanasyuk.dao.daoService.GroupDao;
import com.andrewpanasyuk.dao.daoService.StudentDao;
import com.andrewpanasyuk.service.services.StudentService;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public class StudentServiceImpl implements StudentService{
	private StudentDao studentDB = new StudentDaoImpl();

	@Override
	public void createStudent(String firstName, String lastName) throws DAOException {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		studentDB.createStudent(student);
	}

	@Override
	public void removeStudent(String studentID) throws DAOException {
		int id = Integer.valueOf(studentID);
		Student student = studentDB.getStudentById(id);
		studentDB.removeStudent(student);
	}

	@Override
	public void updateGroup(String studentID, String groupID) throws DAOException {
		int student_id = Integer.valueOf(studentID);
		int group_id = Integer.valueOf(groupID);
		Student student = studentDB.getStudentById(student_id);
		GroupDao groupDB = new GroupDaoImpl();
		Group group = groupDB.getGroupById(group_id);
//		group.addStudent(student);
		studentDB.updateGroup(student, group);
	}

	@Override
	public void updateStudentFirstName(String studentID, String newFirstName)
			throws DAOException {
		int id = Integer.valueOf(studentID);
		Student student = studentDB.getStudentById(id);
		studentDB.updateStudentFirstName(student, newFirstName);
		
	}

	@Override
	public void updateStudentLastName(String studentID, String newLastName)
			throws DAOException {
		int id = Integer.valueOf(studentID);
		Student student = studentDB.getStudentById(id);
		studentDB.updateStudentLastName(student, newLastName);
		
	}

	@Override
	public Student getStudentById(String studentID) throws DAOException {
		int id = Integer.valueOf(studentID);
		Student student = studentDB.getStudentById(id);
		return student;
	}

	@Override
	public List<Student> getAllStudents() throws DAOException {
		List<Student> students = studentDB.getAllStudents();
		return students;
	}

	@Override
	public Student getLastStudent() throws DAOException {
		Student student = studentDB.getLastStudent();
		return student;
	}

}
