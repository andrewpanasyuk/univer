package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupUpdateServlet")
public class GroupUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupUpdateServlet.class);
	private static GroupServiceIF groupDB = new GroupService();


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Group group = groupDB.getGroupById(id);
			request.setAttribute("group", group);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupUpdate.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String newName = request.getParameter("name");
		try {
			groupDB.renameGroup(id, newName);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");

	}

}
