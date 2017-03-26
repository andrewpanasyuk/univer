package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupRemoveServlet")
public class GroupRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupRemoveServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Group group = Controller.groupService.getGroupById(id);
			Controller.groupService.removeGroup(group);
			log.info("Group removed");
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");
	}

}
