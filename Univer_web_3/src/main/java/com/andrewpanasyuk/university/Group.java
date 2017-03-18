package com.andrewpanasyuk.university;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Group {
	private static final Logger log = Logger.getLogger(Group.class);
	private int id;
	private String name;
	private List<Student> students = new ArrayList<>();
	private List<Lesson> lessons = new ArrayList<>();;
	
	public Group(String name){
		log.trace("create Group: " + name);
		this.name = name;
	}
	
	public Group(){
		log.trace("create 'Group'");
	}
	
	public void addStudent(Student student){
		log.info("add Student to Group " + name);
		students.add(student);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		log.trace("Group's name: " + name + " was seted");
		this.name = name;
	}

	public void setLessons(List<Lesson> lessons) {
		log.info("List lessons's was seted");
		this.lessons = lessons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Group other = (Group) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}




}
