package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOExeption;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleShowServlet")
public class ScheduleShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao schedueDao = new ScheduleDao();
		List<Lesson> lessons;
		try {
			lessons = schedueDao.getAllLessons();
			request.setAttribute("lessons", lessons);

		} catch (DAOExeption e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/schedules.jsp");
		dispatcher.forward(request, response);

	}

}
