package com.andrewpanasyuk.service;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

public interface ScheduleService {

	void addLesson(Lesson lesson) throws DAOException;

	void removeLesson(Lesson lesson) throws DAOException;

	void updateAuditorium(Lesson lesson, int auditoriumNumber)
			throws DAOException;

	void updateDate(Lesson lesson, String newDate) throws DAOException;

	void updateGroup(Lesson lesson, Group group) throws DAOException;

	void updateName(Lesson lesson, String newName) throws DAOException;

	void updateTeacher(Lesson lesson, Teacher teacher) throws DAOException;

	Lesson getLessonByID(int lessonId) throws DAOException;

	List<Lesson> getAllLessons() throws DAOException;

	List<Lesson> getTeacherLessons(Teacher teacher) throws DAOException;

	List<Lesson> getGroupLessons(Group group) throws DAOException;

	List<Lesson> getGroupLessonsBetweenDates(String dateFrom, String dateTo,
			Group group) throws DAOException;

	List<Lesson> getTeacherLessonsBetweenDates(String dateFrom, String dateTo,
			Teacher teacher) throws DAOException;

	List<Lesson> getScheduleBetweenDates(String dateFrom, String dateTo)
			throws DAOException;

}
