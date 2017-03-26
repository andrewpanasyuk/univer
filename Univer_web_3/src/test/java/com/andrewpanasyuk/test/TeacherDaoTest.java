package com.andrewpanasyuk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Teacher;

public class TeacherDaoTest {
	private Teacher teacher;
	private TeacherDao teacherDao = new TeacherDao();

	@Before
	public void createTestData() {
		teacher = new Teacher();
		teacher.setFirstName("fn_teach1");
		teacher.setLastName("ln_teach1");
	}
	
	@Test
	public void testCreateTeacherDao() {
		try {
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			teacher.setId(lastAddTeacher.getId());
			assertTrue(teacher.equals(lastAddTeacher));
			teacherDao.removeTeacher(lastAddTeacher);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRemoveTeacherDao() {
		try {
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			int teacher_id = lastAddTeacher.getId();
			assertNotNull(teacherDao.getTeacherByID(teacher_id));
			teacherDao.removeTeacher(lastAddTeacher);
			assertNull(teacherDao.getTeacherByID(teacher_id));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRenameTeacherFirstName() {
		try {
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			String newName = "newName";
			teacherDao.updateTeacherFirstName(lastAddTeacher, newName);
			lastAddTeacher = teacherDao.getLastTeacher();
			assertTrue(lastAddTeacher.getFirstName().equals(newName));
			teacherDao.removeTeacher(lastAddTeacher);

		} catch (DAOException e) {
			e.printStackTrace();
		}
			}

	@Test
	public void testRenameTeacherLastName() {
		try {
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			String newName = "newName";
			teacherDao.updateTeacherLastName(lastAddTeacher, newName);
			lastAddTeacher = teacherDao.getLastTeacher();
			assertTrue(lastAddTeacher.getLastName().equals(newName));
			teacherDao.removeTeacher(lastAddTeacher);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

}
