package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupCreateServlet")
public class GroupCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GroupDao groupDao = new GroupDao();
		Group group = new Group();
		String name = request.getParameter("group name");
		group.setName(name);
		try {
			groupDao.addGroup(group);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");

	}

}
