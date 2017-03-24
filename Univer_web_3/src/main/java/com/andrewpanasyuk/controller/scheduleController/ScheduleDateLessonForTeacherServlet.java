package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Lesson;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/LessonsByPeriodForTeacher")
public class ScheduleDateLessonForTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao scheduleDao = new ScheduleDao();
		TeacherDao teacherDao = new TeacherDao();
		int teacherId = Integer.valueOf(request.getParameter("id"));
		try {
			Teacher teacher = teacherDao.getTeacherByID(teacherId);
			String dateFrom = request.getParameter("dateFrom");
			String dateTo = request.getParameter("dateTo");
			List<Lesson> lessons = scheduleDao.getTeacherLessonsBetweenDates(
					dateFrom, dateTo, teacher);
			request.setAttribute("lessons", lessons);
			request.setAttribute("teacher", teacher);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/teachers/TeacherLessons.jsp");
			dispatcher.forward(request, response);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
