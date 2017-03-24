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

@WebServlet("/TeacherRemoveServlet")
public class TeacherRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher;
		try {
			teacher = teacherDao.getTeacherByID(id);
			teacherDao.removeTeachers(teacher);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/TeacherShowServlet");
	}

}
