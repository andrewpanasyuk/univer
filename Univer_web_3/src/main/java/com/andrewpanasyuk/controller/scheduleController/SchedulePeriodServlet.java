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
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/SchedulePeriodServlet")
public class SchedulePeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SchedulePeriodServlet.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String dateFrom = request.getParameter("dateFrom");
			String dateTo = request.getParameter("dateTo");
			List<Lesson> lessons = scheduleService.getScheduleBetweenDates(dateFrom, dateTo);
			request.setAttribute("lessons", lessons);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/schedules.jsp");
			dispatcher.forward(request, response);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

}
