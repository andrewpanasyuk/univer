package com.andrewpanasyuk.controller.teacherController;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.Controller;
import com.andrewpanasyuk.controller.ControllerException;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherShowServlet")
public class TeacherShowServlet extends HttpServlet {

	private static final Logger log = Logger
			.getLogger(TeacherShowServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.error(e.toString());
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			procesRequest(request, response);
		} catch (ControllerException e) {
			log.error(e.toString());
		}
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		try {
			List<Teacher> teachers = Controller.teacherService.getAllTeachers();
			request.setAttribute("teachers", teachers);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/teachers.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.error(e.toString());
			throw new ControllerException();
		}

	}

}
