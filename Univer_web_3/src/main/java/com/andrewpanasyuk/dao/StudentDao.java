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

public class StudentDao {
	public static final Logger log = Logger.getLogger(StudentDao.class);

	public void createStudent(Student student) throws DAOExeption {
		log.info("request to create Student");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.execute();
			log.info("Student was created");
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

	public void removeStudent(Student student) throws DAOExeption {
		log.info("Request to remove student with ID = " + student.getId());
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM students WHERE student_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, student.getId());
			statement.execute();
			log.info("Student was removed");
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

	public void updateStudentFirstName(Student student, String newFirstName) throws DAOExeption {
		log.info("Request to update Student's first name");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE students SET first_name = ? WHERE student_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newFirstName);
			statement.setInt(2, student.getId());
			statement.execute();
			log.info("First name was updated");
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

	public void updateStudentLastName(Student student, String newLastName) throws DAOExeption {
		log.info("Request to update Student's last name");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE students SET last_name = ? WHERE student_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newLastName);
			statement.setInt(2, student.getId());
			statement.execute();
			log.info("Last name was updated");
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

	public List<Student> getAllStudents() throws DAOExeption{
		log.info("Request to get a list for all students");
		List<Student> students = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM students";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Created result table");
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getInt("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				student.setGroup(group);
				log.trace("create Student");
				students.add(student);
				log.trace("Student was added to list");
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
		log.info("Returned the list of students");
		return students;
	}

	public Student getLastStudent() throws DAOExeption {
		log.info("Request to get last added student");
		Student student = new Student();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM students WHERE student_id = (SELECT max(student_id) from students)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Created result table");
			while (result.next()) {
				student.setId(result.getInt("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
				log.info("Created student");
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
		log.info("Returned last added student");
		return student;
	}

	public Student getStudentById(int id) throws DAOExeption {
		log.info("Request to getting student by ID. ID = " + id);
		Student student = null;
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM students FULL OUTER JOIN groups ON students.group_id = groups.group_id WHERE students.student_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			result = statement.getResultSet();
			log.trace("Created result table");
			while (result.next()) {
				student = new Student();
				student.setId(result.getInt("student_id"));
				student.setFirstName(result.getString("first_name"));
				student.setLastName(result.getString("last_name"));
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				log.trace("Created Group");
				student.setGroup(group);
				log.trace("Created Student");
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
		log.info("Was returned a student by ID");
		return student;
	}
}
