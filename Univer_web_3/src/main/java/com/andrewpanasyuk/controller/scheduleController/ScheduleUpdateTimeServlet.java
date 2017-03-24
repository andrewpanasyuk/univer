package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/ScheduleUpdateTimeServlet")
public class ScheduleUpdateTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleDao scheduleDao = new ScheduleDao();
	private GroupDao groupDao = new GroupDao();
	private TeacherDao teacherDao = new TeacherDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/lessons/LessonUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ScheduleDao scheduleDao = new ScheduleDao();
		Lesson lesson;
		try {
			lesson = scheduleDao.getLessonByID(id);
			String time = request.getParameter("date") + " "
					+ request.getParameter("time");
			scheduleDao.updateDate(lesson, time);
			List<Lesson> lessons = scheduleDao.getAllLessons();
			request.setAttribute("lessons", lessons);

		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		procesRequest(request, response);
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Lesson lesson = scheduleDao.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = groupDao.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher> teachers = teacherDao.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
