package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherCreateServlet")
public class TeacherCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = new Teacher();
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		try {
			teacherDao.createTeacher(teacher);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/TeacherShowServlet");
	}

}
