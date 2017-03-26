package com.andrewpanasyuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.service.TeacherService;
import com.andrewpanasyuk.university.Teacher;

public class TeacherDao implements TeacherService {
	public static final Logger log = Logger.getLogger(TeacherDao.class);

	@Override
	public void createTeacher(Teacher teacher) throws DAOException{
		log.info("Request to create Teacher");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO teachers (first_name, last_name) VALUES (?, ?)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, teacher.getFirstName());
			statement.setString(2, teacher.getLastName());
			statement.execute();
			log.info("Teacher was added");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}
		}
	}

	
	@Override
	public void removeTeacher(Teacher teacher) throws DAOException{
		log.info("Request to remove Teacher. ID = " + teacher.getId());
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM teachers WHERE teacher_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, teacher.getId());
			statement.execute();
			log.info("Teacher with ID = " + teacher.getId() + ", was removed");
		} catch (SQLException e) {
			log.error(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}
		}
	}

	@Override
	public void updateTeacherFirstName(Teacher teacher, String newFirstName) throws DAOException {
		log.info("Request to rename teacher with ID = " + teacher.getId());
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE teachers SET first_name = ? WHERE teacher_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newFirstName);
			statement.setInt(2, teacher.getId());
			statement.execute();
			log.info("Teacher with ID = " + teacher.getId() + ", was renamed");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}
		}
	}

	@Override
	public void updateTeacherLastName(Teacher teacher, String newLastName) throws DAOException {
		log.info("Request to change teacher's last name for Teacher with ID = "
				+ teacher.getId());
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE teachers SET last_name = ? WHERE teacher_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newLastName);
			statement.setInt(2, teacher.getId());
			statement.execute();
			log.info("Last name was updated");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}
		}
	}

	@Override
	public List<Teacher> getAllTeachers() throws DAOException{
		log.info("Request for getting list of all teachers");
		List<Teacher> teachers = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM teachers";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Create result table");
			while (result.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(result.getInt("teacher_id"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teachers.add(teacher);
				log.trace("Teacher added into a list");
			}
			log.info("List of all teachers was created");
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}

		}
		log.info("Returned list of teachers");
		return teachers;
	}

	public Teacher getLastTeacher() throws DAOException{
		log.info("Request for getting a last instance from the list of all teachers");
		Teacher teacher = new Teacher();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM teachers WHERE teacher_id = (SELECT max(teacher_id) from teachers)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Create result table");
			while (result.next()) {
				teacher.setId(result.getInt("teacher_id"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				log.trace("Create teacher");
			}
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}

		}
		log.info("returned last teacher");
		return teacher;
	}

	@Override
	public Teacher getTeacherByID(int teacher_id) throws DAOException{
		log.info("request for getting Teacher by ID. ID = " + teacher_id);
		Teacher teacher = null;
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, teacher_id);
			statement.execute();
			result = statement.getResultSet();
			log.trace("Created result table");
			while (result.next()) {
				teacher = new Teacher();
				teacher.setId(result.getInt("teacher_id"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				log.trace("Teacher was created");
			}
		} catch (SQLException e) {
			log.warn(e.toString());
			throw new DAOException("Data base error: " + e.getMessage());
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
				throw new DAOException("Error with closing the database: " + e.getMessage());
			}
		}
		log.info("Teacher by ID was returned");
		return teacher;
	}

}
