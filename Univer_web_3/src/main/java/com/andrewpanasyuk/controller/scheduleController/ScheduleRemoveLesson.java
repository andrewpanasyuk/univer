package com.andrewpanasyuk.controller.scheduleController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.ScheduleService;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;

@WebServlet("/ScheduleRemoveLesson")
public class ScheduleRemoveLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleRemoveLesson.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			scheduleService.removeLesson(id);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/ScheduleShowServlet");
	}

}
