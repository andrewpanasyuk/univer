package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;

@WebServlet("/GroupCreateServlet")
public class GroupCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupCreateServlet.class);
	private GroupServiceIF groupService = new GroupService();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("request for create Group");
		String name = request.getParameter("group name");
		try {
			groupService.addGroup(name);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
			response.sendRedirect(request.getContextPath() + "/GroupShowServlet");

	}

}
