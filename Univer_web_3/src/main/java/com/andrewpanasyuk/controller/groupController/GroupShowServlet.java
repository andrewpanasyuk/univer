package com.andrewpanasyuk.controller.groupController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andrewpanasyuk.dao.DAOExeption;
import com.andrewpanasyuk.dao.GroupDao;
import com.andrewpanasyuk.university.Group;

@WebServlet("/GroupShowServlet")
public class GroupShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GroupShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}
	
	protected void procesRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		GroupDao groupDao = new GroupDao();
			List<Group> groups;
			try {
				groups = groupDao.getAllGroups();
				request.setAttribute("groups", groups);
			} catch (DAOExeption e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/groups.jsp");
			dispatcher.forward(request, response);
		
		
	}

}
