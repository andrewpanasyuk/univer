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
import com.andrewpanasyuk.service.serviceImpl.ScheduleServiceImpl;
import com.andrewpanasyuk.service.serviceImpl.TeacherServiceImpl;
import com.andrewpanasyuk.service.services.ScheduleService;
import com.andrewpanasyuk.service.services.TeacherService;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/Lessons/Teacher/period")
public class SchedulePeriodForTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SchedulePeriodForTeacherServlet.class);
	private ScheduleService scheduleService = new ScheduleServiceImpl();
	private TeacherService teacherService = new TeacherServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String teacherId = request.getParameter("id");
		try {
			Teacher teacher = teacherService.getTeacherByID(teacherId);
			String dateFrom = request.getParameter("dateFrom");
			String dateTo = request.getParameter("dateTo");
			List<Lesson> lessons = scheduleService.getTeacherLessonsBetweenDates(
					dateFrom, dateTo, teacherId);
			request.setAttribute("lessons", lessons);
			request.setAttribute("teacher", teacher);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/teachers/TeacherLessons.jsp");
			dispatcher.forward(request, response);

		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

}
