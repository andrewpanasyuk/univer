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
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.ScheduleService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.service.serviceIF.ScheduleServiceIF;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleForGroupServlet")
public class ScheduleForGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleForGroupServlet.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
	private GroupServiceIF groupService = new GroupService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Group group = groupService.getGroupById(id);
			List<Lesson> lessons = scheduleService.getGroupLessons(id);
			request.setAttribute("lessons", lessons);
			request.setAttribute("group", group);

		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupLessons.jsp");
		dispatcher.forward(request, response);
	}

}
