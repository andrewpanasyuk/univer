package com.andrewpanasyuk.service.services;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public interface GroupService {

	void addGroup(String groupName) throws DAOException;

	void addStudent(String groupID, Student student) throws DAOException;

	void removeGroup(String groupID)throws DAOException;

	void renameGroup(String groupID, String newName) throws DAOException;

	Group getGroupById(String groupId) throws DAOException;

	List<Group> getAllGroups() throws DAOException;

	List<Student> getAllStudents(String groupID) throws DAOException;

}
