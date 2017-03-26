package com.andrewpanasyuk.dao.daoIF;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public interface GroupDaoIF {

	void addGroup(Group group) throws DAOException;

	void addStudent(Group group, Student student) throws DAOException;

	void removeGroup(Group group)throws DAOException;

	void renameGroup(Group group, String newName) throws DAOException;

	Group getGroupById(int groupId) throws DAOException;

	List<Group> getAllGroups() throws DAOException;

	List<Student> getAllStudents(Group group) throws DAOException;

}
