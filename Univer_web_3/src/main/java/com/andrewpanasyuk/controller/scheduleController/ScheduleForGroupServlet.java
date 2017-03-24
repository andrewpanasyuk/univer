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
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.dao.ScheduleDao;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Lesson;

@WebServlet("/ScheduleForGroupServlet")
public class ScheduleForGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ScheduleDao scheduleDao = new ScheduleDao();
		GroupDao groupDao = new GroupDao();
		try {
			Group group = groupDao.getGroupById(id);
			List<Lesson> lessons = scheduleDao.getGroupLessons(group);
			request.setAttribute("lessons", lessons);
			request.setAttribute("group", group);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupLessons.jsp");
		dispatcher.forward(request, response);
	}

}
