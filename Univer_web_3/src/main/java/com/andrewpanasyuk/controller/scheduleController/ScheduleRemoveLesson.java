package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleRemoveLesson")
public class ScheduleRemoveLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ScheduleDao scheduleDao = new ScheduleDao();
		try {
			Lesson lesson = scheduleDao.getLessonByID(id);
			scheduleDao.removeLesson(lesson);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

}
