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

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.serviceImpl.GroupServiceImpl;
import com.andrewpanasyuk.service.serviceImpl.ScheduleServiceImpl;
import com.andrewpanasyuk.service.serviceImpl.TeacherServiceImpl;
import com.andrewpanasyuk.service.services.GroupService;
import com.andrewpanasyuk.service.services.ScheduleService;
import com.andrewpanasyuk.service.services.TeacherService;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/Lesson/updateTime")
public class ScheduleUpdateTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleUpdateTimeServlet.class);
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private GroupService groupService = new GroupServiceImpl();
	private TeacherService teacherService = new TeacherServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/lessons/LessonUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			String time = request.getParameter("date") + " "
					+ request.getParameter("time");
			scheduleService.updateDate(id, time);
			List<Lesson> lessons = scheduleService.getAllLessons();
			request.setAttribute("lessons", lessons);

		} catch (DAOException e1) {
			log.error(e1.getMessage());
		}

		procesRequest(request, response);
		response.sendRedirect(request.getContextPath() + "/Lessons");
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		try {
			Lesson lesson = scheduleService.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = groupService.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher> teachers = teacherService.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}
}
