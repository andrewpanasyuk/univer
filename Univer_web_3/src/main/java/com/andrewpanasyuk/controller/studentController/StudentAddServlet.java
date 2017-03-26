package com.andrewpanasyuk.controller.studentController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.GroupService;
import com.andrewpanasyuk.service.StudentService;
import com.andrewpanasyuk.service.serviceIF.GroupServiceIF;
import com.andrewpanasyuk.service.serviceIF.StudentServiceIF;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

@WebServlet("/StudentAddServlet")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StudentAddServlet.class);
	private StudentServiceIF studentService = new StudentService();
	private GroupServiceIF groupService = new GroupService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Group> groups = groupService.getAllGroups();
			request.setAttribute("groups", groups);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/students/studentCreate.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		try {
			studentService.createStudent(firstName, lastName);
			String groupId = request.getParameter("group");
			if (!groupId.equals("0")) {
				Student newStudent = studentService.getLastStudent();
				groupService.addStudent(groupId, newStudent);
			}
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
