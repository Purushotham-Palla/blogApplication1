package com.blogapp.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/readAdminPostServlet")
public class ReadAdminPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadAdminPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
		HttpSession session = request.getSession(false);
		int userId =(int)session.getAttribute("userId");
		
		List<BlogPost> adminAllPost = objBImp.getAllPost(userId);
		
		request.setAttribute("adminPosts", adminAllPost);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
		rd.include(request, response);
		
		
	}

}

