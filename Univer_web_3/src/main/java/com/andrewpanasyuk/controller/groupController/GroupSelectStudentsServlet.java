package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.service.serviceImpl.GroupServiceImpl;
import com.andrewpanasyuk.service.serviceImpl.StudentServiceImpl;
import com.andrewpanasyuk.service.services.GroupService;
import com.andrewpanasyuk.service.services.StudentService;
import com.andrewpanasyuk.university.*;

@WebServlet("/GroupSelectStudents")
public class GroupSelectStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GroupSelectStudentsServlet.class);
	private static GroupService groupDB = new GroupServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Group group = groupDB.getGroupById(id);
			log.info("Group formed");
			request.setAttribute("group", group);
			List<Student> students = groupDB.getAllStudents(id);
			request.setAttribute("students", students);
			log.info("Group set to request");
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupAllStudent.jsp");
		dispatcher.forward(request, response);

	}

}
