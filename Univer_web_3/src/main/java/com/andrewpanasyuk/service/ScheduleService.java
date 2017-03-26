package com.andrewpanasyuk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.util.TreeMap;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.dao.daoIF.GroupDaoIF;
import com.andrewpanasyuk.dao.daoIF.ScheduleDaoIF;
import com.andrewpanasyuk.dao.daoIF.TeacherDaoIF;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

public class ScheduleService implements ScheduleServiceIF {
	private ScheduleDaoIF scheduleDB = new ScheduleDao();
	private GroupDaoIF groupDB = new GroupDao();
	private TeacherDaoIF teacherDB = new TeacherDao();

	@Override
	public void addLesson(TreeMap<String, String> lessonData) throws DAOException {
		Lesson lesson = new Lesson();
		lesson.setName(lessonData.get("lessonName"));
		int auditorium = Integer.valueOf(lessonData.get("auditorium"));
		lesson.setAuditorium(auditorium);
		int group_id = Integer.valueOf(lessonData.get("group_id"));
		Group group = groupDB.getGroupById(group_id);
		lesson.setGroup(group);
		int teacher_id = Integer.valueOf(lessonData.get("teacher_id"));
		Teacher teacher = teacherDB.getTeacherByID(teacher_id);
		lesson.setTeacher(teacher);
		
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("MM/dd/yyyy hh:mm");
		String time = lessonData.get("time");
			Date docDate;
			try {
				docDate = format.parse(time);
				lesson.setDate(docDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		scheduleDB.addLesson(lesson);
	}

	@Override
	public void removeLesson(String lessonID) throws DAOException {
		int id = Integer.valueOf(lessonID);
		Lesson lesson = scheduleDB.getLessonByID(id);
		scheduleDB.removeLesson(lesson);
		
	}

	@Override
	public void updateAuditorium(String lessonID, String auditoriumNumber)
			throws DAOException {
		int id = Integer.valueOf(lessonID);
		int auditorium = Integer.valueOf(auditoriumNumber);
		Lesson lesson = scheduleDB.getLessonByID(id);
		scheduleDB.updateAuditorium(lesson, auditorium);
		
	}

	@Override
	public void updateDate(String lessonID, String newDate) throws DAOException {
		int id = Integer.valueOf(lessonID);
		Lesson lesson = scheduleDB.getLessonByID(id);
		scheduleDB.updateDate(lesson, newDate);
		
	}

	@Override
	public void updateGroup(String lessonID, String groupID) throws DAOException {
		int lesson_id = Integer.valueOf(lessonID);
		int group_id = Integer.valueOf(groupID);
		Lesson lesson = scheduleDB.getLessonByID(lesson_id);
		Group group = groupDB.getGroupById(group_id);
		scheduleDB.updateGroup(lesson, group);
		
	}

	@Override
	public void updateName(String lessonID, String newName) throws DAOException {
		int lesson_id = Integer.valueOf(lessonID);
		Lesson lesson = scheduleDB.getLessonByID(lesson_id);
		scheduleDB.updateName(lesson, newName);
		
	}

	@Override
	public void updateTeacher(String lessonID, String teacherID)
			throws DAOException {
		int lesson_id = Integer.valueOf(lessonID);
		int teacher_id = Integer.valueOf(teacherID);
		Lesson lesson = scheduleDB.getLessonByID(lesson_id);
		Teacher teacher = teacherDB.getTeacherByID(teacher_id);
		scheduleDB.updateTeacher(lesson, teacher);
	}

	@Override
	public Lesson getLessonByID(String lessonID) throws DAOException {
		int lesson_id = Integer.valueOf(lessonID);
		Lesson lesson = scheduleDB.getLessonByID(lesson_id);
		return lesson;
	}

	@Override
	public List<Lesson> getAllLessons() throws DAOException {
		List<Lesson> lessons = scheduleDB.getAllLessons();
		return lessons;
	}

	@Override
	public List<Lesson> getTeacherLessons(String teacherID) throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		List<Lesson> lessons = scheduleDB.getTeacherLessons(teacher);
		return lessons;
	}

	@Override
	public List<Lesson> getGroupLessons(String groupID) throws DAOException {
		int id = Integer.valueOf(groupID);
		Group group = groupDB.getGroupById(id);
		List<Lesson> lessons = scheduleDB.getGroupLessons(group);
		return lessons;
	}

	@Override
	public List<Lesson> getGroupLessonsBetweenDates(String dateFrom,
			String dateTo, String groupID) throws DAOException {
		int id = Integer.valueOf(groupID);
		Group group = groupDB.getGroupById(id);
		List<Lesson> lessons = scheduleDB.getGroupLessonsBetweenDates(dateFrom, dateTo, group);
		return lessons;
	}

	@Override
	public List<Lesson> getTeacherLessonsBetweenDates(String dateFrom,
			String dateTo, String teacherID) throws DAOException {
		int id = Integer.valueOf(teacherID);
		Teacher teacher = teacherDB.getTeacherByID(id);
		List<Lesson> lessons = scheduleDB.getTeacherLessonsBetweenDates(dateFrom, dateTo, teacher);
		return lessons;
	}

	@Override
	public List<Lesson> getScheduleBetweenDates(String dateFrom, String dateTo)
			throws DAOException {
		List<Lesson> lessons = scheduleDB.getScheduleBetweenDates(dateFrom, dateTo);
		return lessons;
	}

	@Override
	public Lesson getLastLesson() throws DAOException {
		Lesson lesson = scheduleDB.getLastLesson();
		return lesson;
	}


}
