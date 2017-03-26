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

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleDateLessonForGroup")
public class ScheduleDateLessonForGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ScheduleDateLessonForGroupServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int groupId = Integer.valueOf(request.getParameter("id"));
		try {
			Group group = Controller.groupService.getGroupById(groupId);
			String date = request.getParameter("date");
			List<Lesson> lessons = Controller.scheduleService.getGroupLessonsBetweenDates(
					date, date, group);
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
