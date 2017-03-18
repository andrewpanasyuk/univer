package com.andrewpanasyuk.controller.teacherController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOExeption;
import com.andrewpanasyuk.dao.TeacherDao;
import com.andrewpanasyuk.university.Teacher;

@WebServlet("/TeacherShowController")
public class TeacherShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	procesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	procesRequest(request, response);
	}
	
	protected void procesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teachers;
		try {
			teachers = teacherDao.getAllTeachers();
			request.setAttribute("teachers", teachers);
		} catch (DAOExeption e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/teachers.jsp");
		dispatcher.forward(request, response);
		
	}

}
