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
import com.andrewpanasyuk.service.ScheduleService;
import com.andrewpanasyuk.service.TeacherService;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.service.serviceIF.TeacherServiceIF;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/LessonsByDateForTeacher")
public class SchedulePeriodForTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SchedulePeriodForTeacherServlet.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
	private TeacherServiceIF teacherService = new TeacherService();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String teacherId = request.getParameter("id");
		try {
			Teacher teacher = teacherService.getTeacherByID(teacherId);
			String date = request.getParameter("date");
			List<Lesson> lessons = scheduleService.getTeacherLessonsBetweenDates(
					date, date, teacherId);
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
