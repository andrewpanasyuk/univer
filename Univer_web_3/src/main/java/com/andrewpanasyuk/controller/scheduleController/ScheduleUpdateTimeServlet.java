package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/ScheduleUpdateTimeServlet")
public class ScheduleUpdateTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleUpdateTimeServlet.class);

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
		try {
			Lesson lesson = Controller.scheduleService.getLessonByID(id);
			String time = request.getParameter("date") + " "
					+ request.getParameter("time");
			Controller.scheduleService.updateDate(lesson, time);
			List<Lesson> lessons = Controller.scheduleService.getAllLessons();
			request.setAttribute("lessons", lessons);

		} catch (DAOException e1) {
			log.error(e1.getMessage());
		}

		procesRequest(request, response);
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Lesson lesson = Controller.scheduleService.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = Controller.groupService.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher> teachers = Controller.teacherService.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}
}
