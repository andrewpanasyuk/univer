package com.andrewpanasyuk.dao.daoIF;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Teacher;

public interface TeacherDaoIF {

	void createTeacher(Teacher teacher) throws DAOException;

	void removeTeacher(Teacher teacher) throws DAOException;

	void updateTeacherFirstName(Teacher teacher, String newFirstName)
			throws DAOException;

	void updateTeacherLastName(Teacher teacher, String newLastName)
			throws DAOException;

	Teacher getTeacherByID(int teacherId) throws DAOException;

	List<Teacher> getAllTeachers() throws DAOException;

}
