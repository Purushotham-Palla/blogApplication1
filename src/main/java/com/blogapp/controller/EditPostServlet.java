package com.blogapp.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/editPostServlet")
public class EditPostServlet extends HttpServlet {
    public EditPostServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
		BlogPost onePost = objBImp.getOnePost(postId);
		
		request.setAttribute("onepost", onePost);
		
		RequestDispatcher rd = request.getRequestDispatcher("edit_post.jsp");

		rd.forward(request, response);
		
		
		
	}
	


}

