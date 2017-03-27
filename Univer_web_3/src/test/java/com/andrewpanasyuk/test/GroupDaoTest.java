package com.andrewpanasyuk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoImpl.GroupDaoImpl;
import com.andrewpanasyuk.dao.daoImpl.StudentDaoImpl;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public class GroupDaoTest {
	private Group group;
	private Student student;
	private GroupDaoImpl groupDao = new GroupDaoImpl();
	private StudentDaoImpl studentDao = new StudentDaoImpl();

	
	@Before
	public void createTestData() {
		group = new Group();
		group.setName("testGroup_1");
		student = new Student();
		student.setFirstName("fn_st1");
		student.setLastName("ln_st1");
	}

	
	@Test
	public void testAddGroupDao() {
		try {
			groupDao.addGroup(group);
			Group lastAddGroup = groupDao.getLastGroup();
			assertTrue(group.equals(lastAddGroup));
			groupDao.removeGroup(lastAddGroup);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testRemoveGroupDao() {
		try {
			groupDao.addGroup(group);
			Group lastGroupBeforeRemove = groupDao.getLastGroup();
			int group_id = lastGroupBeforeRemove.getId();
			assertNotNull(groupDao.getGroupById(group_id));
			groupDao.removeGroup(lastGroupBeforeRemove);
			assertNull(groupDao.getGroupById(group_id));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRenameGroupDao() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			String newName = "newName";
			groupDao.renameGroup(lastGroup, newName);
			lastGroup = groupDao.getLastGroup();
			assertTrue(lastGroup.getName().equals(newName));
			groupDao.removeGroup(lastGroup);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddStudentDao() {
		try {
			studentDao.createStudent(student);
			Student lastAddStudent = studentDao.getLastStudent();
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			groupDao.addStudent(lastGroup, lastAddStudent);
			lastAddStudent = studentDao.getStudentById(lastAddStudent.getId());
			assertTrue(lastAddStudent.getGroup().getId() == lastGroup.getId());
			studentDao.removeStudent(lastAddStudent);
			groupDao.removeGroup(lastGroup);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	

}
