package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOException;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherUpdateServlet")
public class TeacherUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao teacherDao = new TeacherDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Teacher teacher = teacherDao.getTeacherByID(id);
			request.setAttribute("teacher", teacher);
		} catch (DAOException e) {
			e.printStackTrace();
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
			Teacher teacher = teacherDao.getTeacherByID(id);
			teacherDao.updateTeacherFirstName(teacher, newFirstName);
			teacherDao.updateTeacherLastName(teacher, newLastName);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/TeacherShowServlet");

	}

}
