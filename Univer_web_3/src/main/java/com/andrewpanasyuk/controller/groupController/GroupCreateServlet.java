package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupCreateServlet")
public class GroupCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupCreateServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("request for create Group");
		Group group = new Group();
		String name = request.getParameter("group name");
		group.setName(name);
		try {
			Controller.groupService.addGroup(group);
			log.info("sent data for create Group");
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/GroupShowServlet");
			
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");

	}

}
