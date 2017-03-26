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
import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleUpdateServlet")
public class ScheduleUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleUpdateServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/lessons/LessonUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Lesson lesson = Controller.scheduleService.getLessonByID(id);
			String newLessonName = request.getParameter("subject");
			Controller.scheduleService.updateName(lesson, newLessonName);
			
			int auditorium = Integer.valueOf(request.getParameter("auditorium"));
			Controller.scheduleService.updateAuditorium(lesson, auditorium);
			
			int teacherId = Integer.valueOf(request.getParameter("teacher"));
			Teacher teacher = Controller.teacherService.getTeacherByID(teacherId);
			Controller.scheduleService.updateTeacher(lesson, teacher);
			
			int groupId = Integer.valueOf(request.getParameter("group"));
			Group group = Controller.groupService.getGroupById(groupId);
			Controller.scheduleService.updateGroup(lesson, group);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}
	
	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Lesson lesson = Controller.scheduleService.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = Controller.groupService.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher>teachers = Controller.teacherService.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

}
