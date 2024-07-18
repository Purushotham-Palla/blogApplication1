package com.blogapp.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImp.BlogPostBoImp;

@WebServlet("/deletePostServlet")
public class DeletePostServlet extends HttpServlet {
    public DeletePostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
		int i = objBImp.deletePost(postId);
		PrintWriter out = response.getWriter();
		if( i > 0)
		{
			response.sendRedirect("readAdminPostServlet");
		}
		else {
			out.println("Something Wrong...!");
		}
	}

}

