package com.andrewpanasyuk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.StudentDao;
import com.andrewpanasyuk.university.Student;

public class StudentDaoTest {
	private Student student;
	private StudentDao studentDao = new StudentDao();
	
	@Before
	public void createTestData() {
		student = new Student();
		student.setFirstName("fn_st1");
		student.setLastName("ln_st1");
	}

	@Test
	public void testCreateStudentDao() {
		try {
			studentDao.createStudent(student);
			Student lastAddStudent = studentDao.getLastStudent();
			assertTrue(student.equals(lastAddStudent));
			studentDao.removeStudent(lastAddStudent);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRemoveStudentDao() {
		try {
			studentDao.createStudent(student);
			Student lastAddStudent = studentDao.getLastStudent();
			int student_id = lastAddStudent.getId();
			assertNotNull(studentDao.getStudentById(student_id));
			studentDao.removeStudent(lastAddStudent);
			assertNull(studentDao.getStudentById(student_id));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRenameStudentFirstName() {
		StudentDao studentsDao = new StudentDao();
		try {
			studentsDao.createStudent(student);
			Student lastAddStudent = studentsDao.getLastStudent();
			String newName = "newName";
			studentsDao.updateStudentFirstName(lastAddStudent, newName);
			lastAddStudent = studentsDao.getLastStudent();
			assertTrue(lastAddStudent.getFirstName().equals(newName));
			studentsDao.removeStudent(lastAddStudent);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRenameStudentLastName() {
		StudentDao studentsDao = new StudentDao();
		try {
			studentsDao.createStudent(student);
			Student lastAddStudent = studentsDao.getLastStudent();
			String newName = "newName";
			studentsDao.updateStudentLastName(lastAddStudent, newName);
			lastAddStudent = studentsDao.getLastStudent();
			assertTrue(lastAddStudent.getLastName().equals(newName));
			studentsDao.removeStudent(lastAddStudent);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

}
