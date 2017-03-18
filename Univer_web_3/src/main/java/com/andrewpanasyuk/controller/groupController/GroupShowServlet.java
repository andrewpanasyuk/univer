package com.andrewpanasyuk.controller.groupController;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.ControllerException;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupShowServlet")
public class GroupShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupShowServlet.class);

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
		log.info("procesRequest for Group");
		GroupDao groupDao = new GroupDao();
		List<Group> groups;
		try {
			groups = groupDao.getAllGroups();
			request.setAttribute("groups", groups);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/groups.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			log.error(e.toString());
			throw new ControllerException();
		}

	}

}
