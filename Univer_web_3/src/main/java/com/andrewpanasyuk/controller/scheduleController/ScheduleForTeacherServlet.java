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

@WebServlet("/ScheduleForTeacherServlet")
public class ScheduleForTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ScheduleDao scheduleDao = new ScheduleDao();
		TeacherDao teacherDao = new TeacherDao();
		try {
			Teacher teacher = teacherDao.getTeacherByID(id);
			List<Lesson> lessons = scheduleDao.getTeacherLessons(teacher);
			request.setAttribute("lessons", lessons);
			request.setAttribute("teacher", teacher);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/teachers/TeacherLessons.jsp");
		dispatcher.forward(request, response);

	}

}
