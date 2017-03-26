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

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StudentUpdateServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Student student = Controller.studentService.getStudentById(id);
			List<Group>groups = Controller.groupService.getAllGroups();
			request.setAttribute("student", student);
			request.setAttribute("groups", groups);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/students/StudentUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String newFirstName = request.getParameter("first name");
		String newLastName = request.getParameter("last name");
		int groupId = Integer.valueOf(request.getParameter("group"));
		try {
			Student student = Controller.studentService.getStudentById(id);
			Group group = Controller.groupService.getGroupById(groupId);
			Controller.studentService.updateStudentFirstName(student, newFirstName);
			Controller.studentService.updateStudentLastName(student, newLastName);
			Controller.studentService.updateGroup(student, group);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
