package com.andrewpanasyuk.university;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class Lesson {
	private static final Logger log = Logger.getLogger(Group.class);
	private int id;
	private String name;
	private Date date;
	private WeekDay weekDay;
	private int auditorium;
	private Teacher teacher;
	private Group group;
	
	public Lesson(){
		log.trace("created lesson");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
		setWeekDay(date);
	}
	public WeekDay getWeekDay() {
		return weekDay;
	}
	
	private void setWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		this.weekDay = WeekDay.values()[day];
	}
	
	public int getAuditorium() {
		return auditorium;
	}
	public void setAuditorium(int auditorium) {
		this.auditorium = auditorium;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + auditorium;
		result = prime * result + date.getHours();
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		result = prime * result + ((weekDay == null) ? 0 : weekDay.hashCode());
		return result;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (auditorium != other.auditorium)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (date.getHours() != other.date.getHours())
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		if (weekDay != other.weekDay)
			return false;
		return true;
	}
	
	
	
}


