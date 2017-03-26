package com.andrewpanasyuk.controller.studentController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.service.StudentService;
import com.andrewpanasyuk.service.serviceIF.StudentServiceIF;

@WebServlet("/StudentRemoveServlet")
public class StudentRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StudentRemoveServlet.class);
	private StudentServiceIF studentService = new StudentService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			studentService.removeStudent(id);
		} catch (DAOException e) {
			log.error(e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/StudentShowServlet");
	}

}
