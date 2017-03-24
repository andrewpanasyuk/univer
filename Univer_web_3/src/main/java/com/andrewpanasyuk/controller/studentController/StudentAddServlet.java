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

@WebServlet("/StudentAddServlet")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = new StudentDao();
	private Student student = new Student();
	private GroupDao groupDao = new GroupDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Group> groups = groupDao.getAllGroups();
			request.setAttribute("groups", groups);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/students/studentCreate.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		int groupId = Integer.valueOf(request.getParameter("group"));
		student.setFirstName(firstName);
		student.setLastName(lastName);
		try {
			studentDao.createStudent(student);
			Student newStudent = studentDao.getLastStudent();
			if (groupId != 0) {
				Group group = groupDao.getGroupById(groupId);
				groupDao.addStudent(group, newStudent);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
