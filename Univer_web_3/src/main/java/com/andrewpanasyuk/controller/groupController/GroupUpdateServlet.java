package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupUpdateServlet")
public class GroupUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupDao groupDao = new GroupDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Group group = groupDao.getGroupById(id);
			request.setAttribute("group", group);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupUpdate.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String newName = request.getParameter("name");
		Group group;
		try {
			group = groupDao.getGroupById(id);
			groupDao.renameGroup(group, newName);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");

	}

}
