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
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleDateServlet")
public class ScheduleDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao scheduleDao = new ScheduleDao();
		try {
			String date = request.getParameter("date");
			List<Lesson> lessons = scheduleDao.getScheduleBetweenDates(date,
					date);
			request.setAttribute("lessons", lessons);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/schedules.jsp");
			dispatcher.forward(request, response);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
