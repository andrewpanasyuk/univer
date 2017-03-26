package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherCreateServlet")
public class TeacherCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TeacherCreateServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Teacher teacher = new Teacher();
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		try {
			Controller.teacherService.createTeacher(teacher);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/TeacherShowServlet");
	}

}
