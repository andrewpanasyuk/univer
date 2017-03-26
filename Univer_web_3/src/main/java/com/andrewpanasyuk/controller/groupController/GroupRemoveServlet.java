package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;

@WebServlet("/GroupRemoveServlet")
public class GroupRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupRemoveServlet.class);
	private static GroupServiceIF groupDB = new GroupService();



	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			groupDB.removeGroup(id);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/GroupShowServlet");
	}

}
