package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.ScheduleService;
import com.andrewpanasyuk.service.TeacherService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.service.serviceIF.TeacherServiceIF;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleAddLesson")
public class ScheduleAddLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleAddLesson.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
	private GroupServiceIF groupService = new GroupService();
	private TeacherServiceIF teacherService = new TeacherService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Group> groups = groupService.getAllGroups();
			List<Teacher> teachers = teacherService.getAllTeachers();
			request.setAttribute("groups", groups);
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/lessons/ScheduleCreateLesson.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TreeMap<String, String> lesson = new TreeMap<String, String>();
		try {
			String lessonName = request.getParameter("subject");
			lesson.put("lessonName", lessonName);
			String auditorium = request.getParameter("auditorium");
			lesson.put("auditorium", auditorium);
			String group_id = request.getParameter("group");
			lesson.put("group_id", group_id);
			String teacher_id = request.getParameter("teacher");
			lesson.put("teacher_id", teacher_id);
			String time = request.getParameter("date") + " "
					+ request.getParameter("time");
			lesson.put("time", time);
			scheduleService.addLesson(lesson);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

}
