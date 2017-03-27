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
import com.andrewpanasyuk.service.serviceImpl.GroupServiceImpl;
import com.andrewpanasyuk.service.serviceImpl.StudentServiceImpl;
import com.andrewpanasyuk.service.services.GroupService;
import com.andrewpanasyuk.service.services.StudentService;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

@WebServlet("/Student/update")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StudentUpdateServlet.class);
	private StudentService studentService = new StudentServiceImpl();
	private GroupService groupService = new GroupServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Student student = studentService.getStudentById(id);
			List<Group>groups = groupService.getAllGroups();
			request.setAttribute("student", student);
			request.setAttribute("groups", groups);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/students/StudentUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("id");
		String newFirstName = request.getParameter("first name");
		String newLastName = request.getParameter("last name");
		String groupId = request.getParameter("group");
		try {
			studentService.updateStudentFirstName(studentId, newFirstName);
			studentService.updateStudentLastName(studentId, newLastName);
			studentService.updateGroup(studentId, groupId);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/Students");
	}

}
