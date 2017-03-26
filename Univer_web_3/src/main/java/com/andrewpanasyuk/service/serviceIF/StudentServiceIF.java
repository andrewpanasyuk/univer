package com.andrewpanasyuk.service.serviceIF;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Student;

public interface StudentServiceIF {
	void createStudent(String firstName, String lastName) throws DAOException;

	void removeStudent(String studentID) throws DAOException;

	void updateGroup(String studentID, String groupID) throws DAOException;

	void updateStudentFirstName(String studentID, String newFirstName)
			throws DAOException;

	void updateStudentLastName(String studentID, String newLastName)
			throws DAOException;

	Student getStudentById(String studentID) throws DAOException;

	List<Student> getAllStudents() throws DAOException;

	Student getLastStudent() throws DAOException;

}
