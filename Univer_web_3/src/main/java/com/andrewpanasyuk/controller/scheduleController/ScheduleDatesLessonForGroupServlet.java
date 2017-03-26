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

@WebServlet("/ScheduleDatesLessonForGroupServlet")
public class ScheduleDatesLessonForGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleDatesLessonForGroupServlet.class);
	private ScheduleServiceIF scheduleService = new ScheduleService();
	private GroupServiceIF groupService = new GroupService();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String groupId = request.getParameter("id");
		try {
			Group group = groupService.getGroupById(groupId);
			String dateFrom = request.getParameter("dateFrom");
			String dateTo = request.getParameter("dateTo");
			List<Lesson> lessons = scheduleService.getGroupLessonsBetweenDates(
					dateFrom, dateTo, groupId);
			request.setAttribute("lessons", lessons);
			request.setAttribute("group", group);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/groups/GroupLessons.jsp");
			dispatcher.forward(request, response);

		} catch (DAOException e) {
			log.error(e.getMessage());
		}
	}

}
