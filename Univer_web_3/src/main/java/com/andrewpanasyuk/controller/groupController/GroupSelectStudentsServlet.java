package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.andrewpanasyuk.dao.*;
import com.andrewpanasyuk.university.*;

@WebServlet("/GroupSelectStudentsServlet")
public class GroupSelectStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		GroupDao groupDao = new GroupDao();
		try {
			Group group = groupDao.getGroupById(id);
			List<Student> students = groupDao.getAllStudents(group);
			group.setStudents(students);
			request.setAttribute("group", group);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/groups/GroupAllStudent.jsp");
		dispatcher.forward(request, response);

	}

}
