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

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.ScheduleService;
import com.andrewpanasyuk.service.TeacherService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.service.serviceIF.TeacherServiceIF;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleUpdateServlet")
public class ScheduleUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleUpdateServlet.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
	private GroupServiceIF groupService = new GroupService();
	private TeacherServiceIF teacherService = new TeacherService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/lessons/LessonUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			String newLessonName = request.getParameter("subject");
			scheduleService.updateName(id, newLessonName);
			
			String auditorium = request.getParameter("auditorium");
			scheduleService.updateAuditorium(id, auditorium);
			
			String teacherId = request.getParameter("teacher");
			scheduleService.updateTeacher(id, teacherId);
			
			String groupId = request.getParameter("group");
			scheduleService.updateGroup(id, groupId);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}
	
	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		try {
			Lesson lesson = scheduleService.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = groupService.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher>teachers = teacherService.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

}
