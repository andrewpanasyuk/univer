package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/ScheduleAddLesson")
public class ScheduleAddLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TeacherDao teacherDao = new TeacherDao();
		GroupDao groupDao = new GroupDao();
		try {
			List<Group> groups = groupDao.getAllGroups();
			List<Teacher> teachers = teacherDao.getAllTeachers();
			request.setAttribute("groups", groups);
			request.setAttribute("teachers", teachers);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/lessons/ScheduleCreateLesson.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao scheduleDao = new ScheduleDao();
		Lesson lesson = new Lesson();
		lesson.setName(request.getParameter("subject"));
		int auditorium = Integer.valueOf(request.getParameter("auditorium"));
		lesson.setAuditorium(auditorium);
		GroupDao groupDao = new GroupDao();
		int group_id = Integer.valueOf(request.getParameter("group"));
		Group group;
		TeacherDao teacherDao = new TeacherDao();
		int teacher_id = Integer.valueOf(request.getParameter("teacher"));
		Teacher teacher;
		try {
			group = groupDao.getGroupById(group_id);
			teacher = teacherDao.getTeacherByID(teacher_id);
			lesson.setGroup(group);
			lesson.setTeacher(teacher);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			scheduleDao.addLesson(lesson);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

}
