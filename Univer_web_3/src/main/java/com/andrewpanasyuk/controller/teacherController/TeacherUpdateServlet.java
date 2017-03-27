package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.serviceImpl.TeacherServiceImpl;
import com.andrewpanasyuk.service.services.TeacherService;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/Teacher/update")
public class TeacherUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TeacherUpdateServlet.class);
	private TeacherService teacherService = new TeacherServiceImpl();


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Teacher teacher = teacherService.getTeacherByID(id);
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
		String id = request.getParameter("id");
		String newFirstName = request.getParameter("first name");
		String newLastName = request.getParameter("last name");
		try {
			teacherService.updateTeacherFirstName(id, newFirstName);
			teacherService.updateTeacherLastName(id, newLastName);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/Teachers");

	}

}
