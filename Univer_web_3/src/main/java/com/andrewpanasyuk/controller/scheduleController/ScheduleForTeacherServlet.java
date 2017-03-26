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
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/ScheduleForTeacherServlet")
public class ScheduleForTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleForTeacherServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Teacher teacher = Controller.teacherService.getTeacherByID(id);
			List<Lesson> lessons = Controller.scheduleService.getTeacherLessons(teacher);
			request.setAttribute("lessons", lessons);
			request.setAttribute("teacher", teacher);

		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/teachers/TeacherLessons.jsp");
		dispatcher.forward(request, response);

	}

}
