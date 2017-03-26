package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherUpdateServlet")
public class TeacherUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TeacherUpdateServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Teacher teacher = Controller.teacherService.getTeacherByID(id);
			request.setAttribute("teacher", teacher);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/teachers/TeacherUpdate.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String newFirstName = request.getParameter("first name");
		String newLastName = request.getParameter("last name");
		try {
			Teacher teacher = Controller.teacherService.getTeacherByID(id);
			Controller.teacherService.updateTeacherFirstName(teacher, newFirstName);
			Controller.teacherService.updateTeacherLastName(teacher, newLastName);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/TeacherShowServlet");

	}

}
