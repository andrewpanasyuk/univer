package com.andrewpanasyuk.controller.groupController;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.ControllerException;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupShowServlet")
public class GroupShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupShowServlet.class);
	private static GroupServiceIF groupDB = new GroupService();

	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.error(e.toString());
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.equals(e.toString());

		}
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		log.info("proces Request for Group");
		try {
			List<Group> groups = groupDB.getAllGroups();
			request.setAttribute("groups", groups);
			log.info("Set list 'Groups' to request");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/groups.jsp");
			log.info("Sent request to groups.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			log.error(e.toString());
			throw new ControllerException();
		}

	}

}
