package com.andrewpanasyuk.university;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Teacher {
	public static final Logger log = Logger.getLogger(Student.class);
	private int id;
	private String firstName;
	private String lastName;
	private List<Lesson> lessons = new ArrayList<>();

	public Teacher(String name) {
		log.trace("created Teacher, name = " + name);
		this.firstName = name;
		
	}

	public Teacher() {
		log.trace("created Teacher");
	}

	
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}


}
