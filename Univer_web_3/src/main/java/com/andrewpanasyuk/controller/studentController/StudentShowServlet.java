package com.andrewpanasyuk.controller.studentController;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.controller.ControllerException;
import com.andrewpanasyuk.service.serviceImpl.StudentServiceImpl;
import com.andrewpanasyuk.service.services.StudentService;
import com.andrewpanasyuk.university.Student;

@WebServlet("/Students")
public class StudentShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(StudentShowServlet.class);
	private StudentService studentService = new StudentServiceImpl();

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
			List<Student> students = studentService.getAllStudents();
			request.setAttribute("students", students);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/views/students.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			log.error(e.toString());
			throw new ControllerException();
		}

	}

}
