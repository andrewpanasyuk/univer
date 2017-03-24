package com.andrewpanasyuk.controller.studentController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.dao.StudentDao;
import com.andrewpanasyuk.university.Group;
import com.andrewpanasyuk.university.Student;

@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = new StudentDao();
	private GroupDao groupDao = new GroupDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Student student = studentDao.getStudentById(id);
			List<Group>groups = groupDao.getAllGroups();
			request.setAttribute("student", student);
			request.setAttribute("groups", groups);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Student student = studentDao.getStudentById(id);
			Group group = groupDao.getGroupById(groupId);
			studentDao.updateStudentFirstName(student, newFirstName);
			studentDao.updateStudentLastName(student, newLastName);
			studentDao.updateGroup(student, group);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
