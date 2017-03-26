package com.andrewpanasyuk.service.serviceIF;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Teacher;

public interface TeacherServiceIF {

	void createTeacher(String firstName, String lastName) throws DAOException;

	void removeTeacher(String teacherID) throws DAOException;

	void updateTeacherFirstName(String teacherID, String newFirstName)
			throws DAOException;

	void updateTeacherLastName(String teacherID, String newLastName)
			throws DAOException;

	Teacher getTeacherByID(String teacherID) throws DAOException;

	List<Teacher> getAllTeachers() throws DAOException;

}
