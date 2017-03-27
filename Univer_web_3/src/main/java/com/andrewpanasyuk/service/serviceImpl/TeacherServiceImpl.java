package com.andrewpanasyuk.service.serviceImpl;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoImpl.TeacherDaoImpl;
import com.andrewpanasyuk.dao.daoService.TeacherDao;
import com.andrewpanasyuk.service.services.TeacherService;
import com.andrewpanasyuk.university.Teacher;

public class TeacherServiceImpl implements TeacherService {
	private TeacherDao teacherDB = new TeacherDaoImpl();

	@Override
	public void createTeacher(String firstName, String lastName) throws DAOException {
		Teacher teacher = new Teacher();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacherDB.createTeacher(teacher);
	}

	@Override
	public void removeTeacher(String teacherID) throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		teacherDB.removeTeacher(teacher);
	}

	@Override
	public void updateTeacherFirstName(String teacherID, String newFirstName)
			throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		teacherDB.updateTeacherFirstName(teacher, newFirstName);
		
	}

	@Override
	public void updateTeacherLastName(String teacherID, String newLastName)
			throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		teacherDB.updateTeacherLastName(teacher, newLastName);
		
	}

	@Override
	public Teacher getTeacherByID(String teacherID) throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		return teacher;
	}

	@Override
	public List<Teacher> getAllTeachers() throws DAOException {
		List<Teacher> teachers = teacherDB.getAllTeachers();
		return teachers;
	}


}
