package com.andrewpanasyuk.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;




import org.junit.Before;
import org.junit.Test;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoImpl.GroupDaoImpl;
import com.andrewpanasyuk.dao.daoImpl.ScheduleDaoImpl;
import com.andrewpanasyuk.dao.daoImpl.TeacherDaoImpl;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;
import com.andrewpanasyuk.university.WeekDay;

public class ScheduleDaoTest {
	private Group group = new Group();
	private Group group1 = new Group();
	private Lesson lesson = new Lesson();
	private Teacher teacher = new Teacher();
	private Teacher teacher1 = new Teacher();
	private GroupDaoImpl groupDao = new GroupDaoImpl();
	private ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl();
	private TeacherDaoImpl teacherDao = new TeacherDaoImpl();

	@Before
	public void createTestData() {
		group.setName("testGroup_1");
		group1.setName("testGroup_2");

		teacher.setFirstName("fn_teach1");
		teacher.setLastName("ln_teach1");

		teacher1.setFirstName("fn_teach2");
		teacher1.setLastName("ln_teach2");

		lesson.setAuditorium(22);
		lesson.setGroup(group);
		lesson.setName("TestLesson");
		lesson.setTeacher(teacher);
		Calendar cal = Calendar.getInstance();
		cal.set(2017, Calendar.MARCH, 25);
		cal.set(Calendar.HOUR_OF_DAY, 9);
		cal.set(Calendar.MINUTE, 30);
		Date date = cal.getTime();
		lesson.setDate(date);
	}
	
	@Test
	public void testRemoveLesson(){
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			lesson.setGroup(lastGroup);
			
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setTeacher(lastAddTeacher);
			
			scheduleDao.addLesson(lesson);
			Lesson lastAddLesson = scheduleDao.getLastLesson();
			int lastLesson_id = lastAddLesson.getId();
			assertNotNull(scheduleDao.getLessonByID(lastLesson_id));
			scheduleDao.removeLesson(lastAddLesson);
			assertNull(scheduleDao.getLessonByID(lastLesson_id));			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddAndGetLesson() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			lesson.setGroup(lastGroup);
			
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setTeacher(lastAddTeacher);
			
			scheduleDao.addLesson(lesson);
			Lesson lastAddLesson = scheduleDao.getLastLesson();
			assertTrue(lesson.equals(lastAddLesson));
			scheduleDao.removeLesson(lastAddLesson);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAuditorium() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			scheduleDao.updateAuditorium(lessonTest, 0);
			lessonTest = scheduleDao.getLastLesson();
			assertTrue((lessonTest.getAuditorium() == 0));
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTeacher() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			teacherDao.createTeacher(teacher1);
			Teacher newTeacher = teacherDao.getLastTeacher();
			Lesson lessonTest = scheduleDao.getLastLesson();
			scheduleDao.updateTeacher(lessonTest, newTeacher);
			lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getTeacher().getId() == newTeacher.getId());
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);
			teacherDao.removeTeacher(newTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateGroup() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			groupDao.addGroup(group1);
			Group newGroup = groupDao.getLastGroup();
			Lesson lessonTest = scheduleDao.getLastLesson();
			scheduleDao.updateGroup(lessonTest, newGroup);
			lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getGroup().getId() != (lesson.getGroup().getId()));
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			groupDao.removeGroup(newGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateLessonName() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();

			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			scheduleDao.updateName(lessonTest, "newTestName");
			lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getName().equals("newTestName"));
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateDate() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			String newDateString = "01-01-1900 10:00";
			scheduleDao.updateDate(lessonTest, newDateString);
			lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getDate().before(lesson.getDate()));
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateWeekDay() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();

			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getWeekDay().equals(WeekDay.SUNDAY));
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTechersLesson() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();

			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getTeacher().getId() == lastAddTeacher.getId());
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetGroupLesson() {
		try {
			groupDao.addGroup(group);
			Group lastGroup = groupDao.getLastGroup();
			teacherDao.createTeacher(teacher);
			Teacher lastAddTeacher = teacherDao.getLastTeacher();
			lesson.setGroup(lastGroup);
			lesson.setTeacher(lastAddTeacher);
			scheduleDao.addLesson(lesson);
			Lesson lessonTest = scheduleDao.getLastLesson();
			assertTrue(lessonTest.getGroup().getId() == lastGroup.getId());
			scheduleDao.removeLesson(lessonTest);
			groupDao.removeGroup(lastGroup);
			teacherDao.removeTeacher(lastAddTeacher);			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}


}
