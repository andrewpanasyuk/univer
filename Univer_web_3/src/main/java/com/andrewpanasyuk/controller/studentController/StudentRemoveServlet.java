package com.andrewpanasyuk.controller.studentController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.StudentDao;
import com.andrewpanasyuk.university.Student;

@WebServlet("/StudentRemoveServlet")
public class StudentRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StudentDao studentDao = new StudentDao();
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Student student = studentDao.getStudentById(id);
			studentDao.removeStudent(student);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
