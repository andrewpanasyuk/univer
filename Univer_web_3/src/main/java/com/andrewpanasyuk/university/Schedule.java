package com.andrewpanasyuk.university;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Schedule {
	private static final Logger log = Logger.getLogger(Group.class);
	private static List<Lesson> lessons = new ArrayList<>();

	public static List<Lesson> getScheduleGroup(Group group) {
		log.trace("request Schedule for group " + group.getName() + ", ID = " + group.getId());
		List<Lesson> result = new ArrayList<>();
		
		for (Lesson lesson : lessons) {
			if (lesson.getGroup().equals(group)) {
				result.add(lesson);
			}
		}
		log.info("created schedule for group '" + group.getName() + "', ID: " + group.getId());
		return result;
	}

	public static List<Lesson> getScheduleTeacher(Teacher teacher) {
		log.trace("request Schedule for teacher " + teacher.getLastName() + ", ID = " + teacher.getId());
		List<Lesson> result = new ArrayList<>();
		for (Lesson lesson : lessons) {
			if (lesson.getTeacher().equals(teacher)) {
				result.add(lesson);
			}
		}
		log.info("created schedule for teacher '" + teacher.getLastName() + "', ID: " + teacher.getId());
		return result;
	}

	public static void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}

}
