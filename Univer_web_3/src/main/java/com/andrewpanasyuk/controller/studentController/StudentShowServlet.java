package com.andrewpanasyuk.controller.studentController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOExeption;
import com.andrewpanasyuk.dao.StudentDao;
import com.andrewpanasyuk.university.Student;

@WebServlet("/StudentShowServlet")
public class StudentShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	procesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	protected void procesRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		StudentDao studentDao = new StudentDao();
		List<Student> students;
		try {
			students = studentDao.getAllStudents();
			request.setAttribute("students", students);
		} catch (DAOExeption e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/views/students.jsp");
		dispatcher.forward(request, response);

	}

}
