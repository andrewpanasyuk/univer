package com.andrewpanasyuk.service;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.dao.StudentDao;
import com.andrewpanasyuk.dao.daoIF.GroupDaoIF;
import com.andrewpanasyuk.dao.daoIF.StudentDaoIF;
import com.andrewpanasyuk.service.serviceIF.StudentServiceIF;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public class StudentService implements StudentServiceIF{
	private StudentDaoIF studentDB = new StudentDao();

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
		GroupDaoIF groupDB = new GroupDao();
		Group group = groupDB.getGroupById(group_id);
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
