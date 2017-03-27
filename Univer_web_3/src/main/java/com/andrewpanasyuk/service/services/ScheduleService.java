package com.andrewpanasyuk.service.services;

import java.util.List;
import java.util.TreeMap;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Lesson;

public interface ScheduleService {

	void addLesson(TreeMap<String, String> lesson) throws DAOException;

	void removeLesson(String lessonID) throws DAOException;

	void updateAuditorium(String lessonID, String auditoriumNumber)
			throws DAOException;

	void updateDate(String lessonID, String newDate) throws DAOException;

	void updateGroup(String lessonID, String groupID) throws DAOException;

	void updateName(String lessonID, String newName) throws DAOException;

	void updateTeacher(String lessonID, String teacherID) throws DAOException;

	Lesson getLessonByID(String lessonID) throws DAOException;
	
	Lesson getLastLesson() throws DAOException;

	List<Lesson> getAllLessons() throws DAOException;

	List<Lesson> getTeacherLessons(String teacherID) throws DAOException;

	List<Lesson> getGroupLessons(String groupID) throws DAOException;

	List<Lesson> getGroupLessonsBetweenDates(String dateFrom, String dateTo,
			String groupID) throws DAOException;

	List<Lesson> getTeacherLessonsBetweenDates(String dateFrom, String dateTo,
			String teacherID) throws DAOException;

	List<Lesson> getScheduleBetweenDates(String dateFrom, String dateTo)
			throws DAOException;

}
