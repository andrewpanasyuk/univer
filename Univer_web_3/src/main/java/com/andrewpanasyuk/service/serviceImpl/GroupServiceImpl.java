package com.andrewpanasyuk.service.serviceImpl;

import java.util.List;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoImpl.GroupDaoImpl;
import com.andrewpanasyuk.dao.daoService.GroupDao;
import com.andrewpanasyuk.service.services.GroupService;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public class GroupServiceImpl implements GroupService {
	private GroupDao groupDB = new GroupDaoImpl();

	@Override
	public void addGroup(String groupName) throws DAOException {
		Group group = new Group();
		group.setName(groupName);
		groupDB.addGroup(group);
		
	}

	@Override
	public void addStudent(String groupID, Student student) throws DAOException {
		int id = Integer.valueOf(groupID);
		Group group = groupDB.getGroupById(id);
		groupDB.addStudent(group, student);
		
	}

	@Override
	public void removeGroup(String groupID) throws DAOException {
		int id = Integer.valueOf(groupID);
		Group group = groupDB.getGroupById(id);
		groupDB.removeGroup(group);
		
	}

	@Override
	public void renameGroup(String groupID, String newName) throws DAOException {
		int id = Integer.valueOf(groupID);
		Group group = groupDB.getGroupById(id);
		groupDB.renameGroup(group, newName);
		
	}

	@Override
	public Group getGroupById(String groupId) throws DAOException {
		int id = Integer.valueOf(groupId);
		Group group = groupDB.getGroupById(id);
		return group;
	}

	@Override
	public List<Group> getAllGroups() throws DAOException {
		List<Group> groups = groupDB.getAllGroups();
		return groups;
	}

	@Override
	public List<Student> getAllStudents(String groupId) throws DAOException {
		int id = Integer.valueOf(groupId);
		Group group = groupDB.getGroupById(id);
		List<Student> students = groupDB.getAllStudents(group);
		return students;
	}


}
