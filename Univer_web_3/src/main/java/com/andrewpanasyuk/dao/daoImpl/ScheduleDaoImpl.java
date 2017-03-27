package com.andrewpanasyuk.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.ConnectionFactory;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.daoService.ScheduleDao;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

public class ScheduleDaoImpl implements ScheduleDao{
	private static final Logger log = Logger.getLogger(ScheduleDaoImpl.class);

	@Override
	public void addLesson(Lesson lesson) throws DAOException {
		log.info("Request to add lesson");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO schedule (lesson_name, date, weekDay, auditorium, teacher_id, group_id) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, lesson.getName());
			statement.setTimestamp(2, new java.sql.Timestamp(lesson.getDate()
					.getTime()));
			statement.setString(3, lesson.getWeekDay().toString());
			statement.setInt(4, lesson.getAuditorium());
			statement.setInt(5, lesson.getTeacher().getId());
			statement.setInt(6, lesson.getGroup().getId());
			statement.execute();
			log.info("Lesson was added");
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
				throw new DAOException("Error with closing the database: "
						+ e.getMessage());
			}
		}
	}

	@Override
	public List<Lesson> getGroupLessons(Group group) throws DAOException {
		log.info("Request to get schedule for group with ID " + group.getId());
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id WHERE groups.group_id = ?";
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
				Teacher teacher = new Teacher();
				Lesson lesson = new Lesson();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Seted data");
				} catch (ParseException e) {
					log.warn(e.toString());
					throw new DAOException("Data base error: " + e.getMessage());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setId(result.getInt("teacher_id"));
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return schedule for group");
		return lessons;
	}
	
	@Override
	public List<Lesson> getGroupLessonsBetweenDates(String dateFromString, String dateToString, Group group) throws DAOException {
		SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");
		
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id "
				+ "INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id "
				+ "WHERE groups.group_id = ? AND schedule.date BETWEEN ?  AND  ?";
		try {
			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = timeFormatForTime.parse(dateFromString + " 00:00");
				dateTo = timeFormatForTime.parse(dateToString + " 23:59");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, group.getId());
			statement.setTimestamp(2, new java.sql.Timestamp(dateFrom.getTime()));
			statement.setTimestamp(3, new java.sql.Timestamp(dateTo.getTime()));
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				Teacher teacher = new Teacher();
				Lesson lesson = new Lesson();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Seted data");
				} catch (ParseException e) {
					log.warn(e.toString());
					throw new DAOException("Data base error: " + e.getMessage());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setId(result.getInt("teacher_id"));
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return schedule for group");
		return lessons;
	}
	
	@Override
	public List<Lesson> getTeacherLessonsBetweenDates(String dateFromString, String dateToString, Teacher teacher) throws DAOException {
		SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");
		
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id "
				+ "INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id "
				+ "WHERE teachers.teacher_id = ? AND schedule.date BETWEEN ?  AND  ?";
		try {
			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = timeFormatForTime.parse(dateFromString + " 00:00");
				dateTo = timeFormatForTime.parse(dateToString + " 23:59");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, teacher.getId());
			statement.setTimestamp(2, new java.sql.Timestamp(dateFrom.getTime()));
			statement.setTimestamp(3, new java.sql.Timestamp(dateTo.getTime()));
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				Group group = new Group();
				Lesson lesson = new Lesson();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Seted data");
				} catch (ParseException e) {
					log.warn(e.toString());
					throw new DAOException("Data base error: " + e.getMessage());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return schedule for group");
		return lessons;
	}

	@Override
	public List<Lesson> getScheduleBetweenDates(String dateFromString, String dateToString) throws DAOException {
		SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");
		
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id "
				+ "INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id "
				+ "WHERE schedule.date BETWEEN ?  AND  ?";
		try {
			Date dateFrom = null;
			Date dateTo = null;
			try {
				dateFrom = timeFormatForTime.parse(dateFromString + " 00:00");
				dateTo = timeFormatForTime.parse(dateToString + " 23:59");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setTimestamp(1, new java.sql.Timestamp(dateFrom.getTime()));
			statement.setTimestamp(2, new java.sql.Timestamp(dateTo.getTime()));
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				Group group = new Group();
				Lesson lesson = new Lesson();
				Teacher teacher = new Teacher();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Seted data");
				} catch (ParseException e) {
					log.warn(e.toString());
					throw new DAOException("Data base error: " + e.getMessage());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setId(result.getInt("teacher_id"));
				lesson.setTeacher(teacher);
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return schedule for group");
		return lessons;
	}
	
	@Override
	public List<Lesson> getTeacherLessons(Teacher teacher) throws DAOException {
		log.info("Request schedule for teacher");
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id WHERE teachers.teacher_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, teacher.getId());
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				Group group = new Group();
				Lesson lesson = new Lesson();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Seted data");
				} catch (ParseException e) {
					log.warn(e.toString());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return schedule for teacher");
		return lessons;
	}

	@Override
	public void updateAuditorium(Lesson lesson, int newAuditoriumNumber)
			throws DAOException {
		log.info("Request to update auditorium number");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE schedule SET auditorium = ? WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, newAuditoriumNumber);
			statement.setInt(2, lesson.getId());
			statement.execute();
			log.info("Auditorium number was updated");
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
	public void updateTeacher(Lesson lesson, Teacher newTeacher)
			throws DAOException {
		log.info("Request to update teacher");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE schedule SET teacher_id = ? WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, newTeacher.getId());
			statement.setInt(2, lesson.getId());
			statement.execute();
			log.info("Teacher was update");
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
	public void updateGroup(Lesson lesson, Group newGroup) throws DAOException {
		log.info("Request to update group");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE schedule SET group_id = ? WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, newGroup.getId());
			statement.setInt(2, lesson.getId());
			statement.execute();
			log.info("Group was update");
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
	public void updateName(Lesson lesson, String newLessonName)
			throws DAOException {
		log.info("Request to update lesson name");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE schedule SET lesson_name = ? WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setString(1, newLessonName);
			statement.setInt(2, lesson.getId());
			statement.execute();
			log.info("Lesson name was updated");
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
	public void updateDate(Lesson lesson, String newDate) throws DAOException {
		SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");
		Date date = null;
		try {
			date = timeFormatForTime.parse(newDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		log.info("Request to update date");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "UPDATE schedule SET date = ? WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement
					.setTimestamp(1, new java.sql.Timestamp(date.getTime()));
			statement.setInt(2, lesson.getId());
			statement.execute();
			log.info("Date was updated");
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
	public void removeLesson(Lesson lesson) throws DAOException {
		log.info("Request to remove lesson");
		Connection con = null;
		PreparedStatement statement = null;
		String sql = "DELETE FROM schedule WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, lesson.getId());
			statement.execute();
			log.info("Lesson was removed");
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
	public List<Lesson> getAllLessons() throws DAOException {
		log.info("Request to get all lessons");
		List<Lesson> lessons = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create statement");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			log.trace("Create result table");
			while (result.next()) {
				Teacher teacher = new Teacher();
				Lesson lesson = new Lesson();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Date seted");
				} catch (ParseException e) {
					log.warn(e.toString());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setId(result.getInt("teacher_id"));
				lesson.setTeacher(teacher);
				log.trace("teacher was added into lesson");
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				lesson.setGroup(group);
				log.trace("Group was added into lesson");
				lessons.add(lesson);
				log.trace("Lesson was added to list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return list of lessons");
		return lessons;
	}

	public Lesson getLastLesson() throws DAOException {
		log.info("Request to get lesson");
		Lesson lesson = new Lesson();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id "
				+ "WHERE lesson_id = (SELECT max(lesson_id) from schedule)";
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
				lesson.setTeacher(teacher);
				log.trace("teacher was added into lesson");
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Date seted");
				} catch (ParseException e) {
					log.warn(e.toString());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				log.trace("lesson was added into lesson");
				lesson.setGroup(group);
				log.trace("Lesson was added into list of lessons");
			}
			log.trace("Created list of lessons");
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
		log.info("Return list of lessons");
		return lesson;
	}

	@Override
	public Lesson getLessonByID(int lesson_id) throws DAOException {
		log.info("Request lesson by ID");
		Lesson lesson = null;
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM schedule INNER JOIN groups ON schedule.group_id = groups.group_id INNER JOIN teachers ON schedule.teacher_id = teachers.teacher_id WHERE lesson_id = ?";
		try {
			log.trace("Open connect");
			con = ConnectionFactory.getConnect();
			log.trace("Create prepared statement");
			statement = con.prepareStatement(sql);
			statement.setInt(1, lesson_id);
			statement.execute();
			result = statement.getResultSet();
			log.trace("Create result table");
			while (result.next()) {
				lesson = new Lesson();
				Teacher teacher = new Teacher();
				lesson.setId(result.getInt("lesson_id"));
				lesson.setName(result.getString("lesson_name"));
				String dateS = result.getTimestamp("date").toString();
				SimpleDateFormat timeFormatForTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date date;
				try {
					date = timeFormatForTime.parse(dateS);
					lesson.setDate(date);
					log.trace("Date was added");
				} catch (ParseException e) {
					log.warn(e.toString());
				}
				lesson.setAuditorium(result.getInt("auditorium"));
				teacher.setFirstName(result.getString("first_name"));
				teacher.setLastName(result.getString("last_name"));
				teacher.setId(result.getInt("teacher_id"));
				lesson.setTeacher(teacher);
				log.trace("Teacher was added to lesson");
				Group group = new Group();
				group.setId(result.getInt("group_id"));
				group.setName(result.getString("name"));
				lesson.setGroup(group);
				log.trace("Group was added to lesson");
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
		log.info("Return lesson by ID");
		return lesson;
	}
}
