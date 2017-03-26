package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleAddLesson")
public class ScheduleAddLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleAddLesson.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Group> groups = Controller.groupService.getAllGroups();
			List<Teacher> teachers = Controller.teacherService.getAllTeachers();
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
		Lesson lesson = new Lesson();
		lesson.setName(request.getParameter("subject"));
		int auditorium = Integer.valueOf(request.getParameter("auditorium"));
		lesson.setAuditorium(auditorium);
		int group_id = Integer.valueOf(request.getParameter("group"));
		int teacher_id = Integer.valueOf(request.getParameter("teacher"));
		try {
			Group group = Controller.groupService.getGroupById(group_id);
			Teacher teacher = Controller.teacherService.getTeacherByID(teacher_id);
			lesson.setGroup(group);
			lesson.setTeacher(teacher);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("MM/dd/yyyy hh:mm");
		System.out.println(request.getParameter("date") + " "
				+ request.getParameter("time"));
		String time = request.getParameter("date") + " "
				+ request.getParameter("time");
		try {
			Date docDate = format.parse(time);
			lesson.setDate(docDate);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		try {
			Controller.scheduleService.addLesson(lesson);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

}
