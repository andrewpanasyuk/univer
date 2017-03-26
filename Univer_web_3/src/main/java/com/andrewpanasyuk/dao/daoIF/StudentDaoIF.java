package com.andrewpanasyuk.dao.daoIF;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public interface StudentDaoIF {
	void createStudent(Student student) throws DAOException;

	void removeStudent(Student student) throws DAOException;

	void updateGroup(Student student, Group group) throws DAOException;

	void updateStudentFirstName(Student student, String newFirstName)
			throws DAOException;

	void updateStudentLastName(Student student, String newLastName)
			throws DAOException;

	Student getStudentById(int studentId) throws DAOException;

	List<Student> getAllStudents() throws DAOException;

	Student getLastStudent() throws DAOException;

}
