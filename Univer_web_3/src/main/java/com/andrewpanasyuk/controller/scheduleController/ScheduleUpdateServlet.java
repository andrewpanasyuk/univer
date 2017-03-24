package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleUpdateServlet")
public class ScheduleUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ScheduleDao scheduleDao = new ScheduleDao();
	private GroupDao groupDao = new GroupDao();
	private TeacherDao teacherDao = new TeacherDao();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/lessons/LessonUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		scheduleDao = new ScheduleDao();
		teacherDao = new TeacherDao();
		groupDao = new GroupDao();
		try {
			Lesson lesson = scheduleDao.getLessonByID(id);
			String newLessonName = request.getParameter("subject");
			scheduleDao.updateName(lesson, newLessonName);
			
			int auditorium = Integer.valueOf(request.getParameter("auditorium"));
			scheduleDao.updateAuditorium(lesson, auditorium);
			
			int teacherId = Integer.valueOf(request.getParameter("teacher"));
			Teacher teacher = teacherDao.getTeacherByID(teacherId);
			scheduleDao.updateTeacher(lesson, teacher);
			
			int groupId = Integer.valueOf(request.getParameter("group"));
			Group group = groupDao.getGroupById(groupId);
			scheduleDao.updateGroup(lesson, group);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}
	
	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Lesson lesson = scheduleDao.getLessonByID(id);
			request.setAttribute("lesson", lesson);
			List<Group> groups = groupDao.getAllGroups();
			request.setAttribute("groups", groups);
			List<Teacher>teachers = teacherDao.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
