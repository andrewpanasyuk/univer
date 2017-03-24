package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupRemoveServlet")
public class GroupRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		GroupDao groupDao = new GroupDao();
		Group group;
		try {
			group = groupDao.getGroupById(id);
			groupDao.removeGroup(group);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");
	}

}
