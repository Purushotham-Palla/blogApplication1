package com.blogapp.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImp.UserBoImp;
import com.blogapp.model.User;

@WebServlet("/readviewerBrowsePosts")
public class ReadViewerBrowsePosts extends HttpServlet {
       
    public ReadViewerBrowsePosts() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession(false);
//		session.
		
//		int userId =Integer.parseInt(request.getParameter("adminId"));
		
		UserBoImp objUImp = UserBoImp.objUserBoImp();
		
		List<User> adminAll = objUImp.getAllAdmins();
		
		request.setAttribute("adminsAll", adminAll);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewer_dashboard.jsp");
		rd.forward(request, response);
	
	}


}
