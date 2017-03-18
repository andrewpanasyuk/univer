package com.andrewpanasyuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

public class GroupDao {
	private static final Logger log = Logger.getLogger(GroupDao.class);

	public void addGroup(Group group) throws DAOExeption{
		log.info("Request to add group");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO groups (name) VALUES (?)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, group.getName());
			statement.execute();
			log.trace("Group was added");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
	}

	public void removeGroup(Group group) throws DAOExeption {
		log.info("Request to remove group");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM groups WHERE name = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, group.getName());
			statement.execute();
			log.info("Group was removed");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
	}

	public void renameGroup(Group group, String newName) throws DAOExeption{
		log.info("Request to rename group");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE groups SET name = ? WHERE name = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newName);
			statement.setString(2, group.getName());
			statement.execute();
			log.info("Group was renamed");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
	}

	public void addStudent(Group group, Student student) throws DAOExeption{
		log.info("Request to add student into group");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE students SET group_id = (SELECT group_id FROM groups WHERE name = ?) WHERE student_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, group.getName());
			statement.setInt(2, student.getId());
			statement.execute();
			log.info("Student was added");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
	}

	public List<Student> getAllStudents(Group group) throws DAOExeption {
		log.info("Request to get all student from group with ID " + group.getId());
		List<Student> students = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM students WHERE group_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, group.getId());
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getInt("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
				log.trace("Create student");
				students.add(student);
				log.trace("Student was added");
			}
			log.info("List of students was created");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (result != null) {
					result.close();
					log.trace("Result was closed");
				}
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
		log.info("Return list of students");
		return students;
	}
	
	public Group getGroupById(int group_id) throws DAOExeption{
		log.info("Request to get group by ID " + group_id);
		Group group = null;
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM groups WHERE group_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, group_id);
			statement.execute();
			result = statement.getResultSet();
			log.trace("Created result table");
			while (result.next()) {
				group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				log.trace("Create group by ID");
			}

		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (result != null) {
					result.close();
					log.trace("Result was closed");
				}
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}
		}
		log.info("Return group by ID");
		return group;
	}

	public List<Group> getAllGroups() throws DAOExeption{
		log.info("Request to get list of all groups");
		List<Group> groups = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM groups";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Created result table");
			while (result.next()) {
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				log.trace("Create Group");
				groups.add(group);
				log.trace("Group added to list");
			}
			log.info("Created list of groups");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (result != null) {
					result.close();
					log.trace("Result was closed");
				}
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}

		}
		log.info("Return the list of grups");
		return groups;
	}
	
	public Group getLastGroup() throws DAOExeption {
		log.info("Request to get last added group");
		Group group = new Group();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM groups WHERE group_id = (SELECT max(group_id) from groups);";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Created result table");
			while (result.next()) {
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				log.trace("Create group");
			}
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOExeption("Data base error: " + e.getMessage());
		} finally {
			try {
				if (result != null) {
					result.close();
					log.trace("Result was closed");
				}
				if (statement != null) {
					statement.close();
					log.trace("Statement was closed");
				}
				if (con != null) {
					con.close();
					log.trace("Connection was closed");
				}
			} catch (SQLException e) {
				log.warn(e.toString());
				throw new DAOExeption("Error with closing the database: " + e.getMessage());
			}

		}
		log.info("Return last group");
		return group;
	}
}
