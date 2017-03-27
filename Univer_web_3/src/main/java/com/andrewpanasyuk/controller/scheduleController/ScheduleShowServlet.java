package com.andrewpanasyuk.controller.scheduleController;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.ControllerException;
import com.andrewpanasyuk.service.serviceImpl.ScheduleServiceImpl;
import com.andrewpanasyuk.service.services.ScheduleService;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/Lessons")
public class ScheduleShowServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(ScheduleShowServlet.class);
	private static final long serialVersionUID = 1L;
	private ScheduleService scheduleService = new ScheduleServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.error(e.toString());
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.error(e.toString());
		}
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		try {
			List<Lesson> lessons = scheduleService.getAllLessons();
			request.setAttribute("lessons", lessons);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/schedules.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.error(e.toString());
			throw new ControllerException();
		}
		

	}

}
