package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/GroupSelectStudentsServlet")
public class GroupSelectStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupSelectStudentsServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Group group = Controller.groupService.getGroupById(id);
			List<Student> students = Controller.groupService.getAllStudents(group);
			group.setStudents(students);
			log.info("Group formed");
			request.setAttribute("group", group);
			log.info("Group set to request");
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupAllStudent.jsp");
		dispatcher.forward(request, response);

	}

}
