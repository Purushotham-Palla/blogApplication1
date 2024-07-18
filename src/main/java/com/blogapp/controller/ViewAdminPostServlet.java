package com.blogapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/viewAdminPosts")
public class ViewAdminPostServlet extends HttpServlet {
    public ViewAdminPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
		int userId = Integer.parseInt(request.getParameter("adminId"));;
		
		List<BlogPost> adminAllPosts;
		String sortBy = request.getParameter("sortBy");
		
		 if ("title".equals(sortBy)) {
			 adminAllPosts = objBImp.getPostsSortedByTitle(sortBy,userId);
	        } else if ("date".equals(sortBy)) {
	        	adminAllPosts = objBImp.getPostsSortedByDate(sortBy,userId);
	        } else {
	            // Default behavior, fetch posts as usual
	        	adminAllPosts = objBImp.getAllPost(userId);
	        }
		
		request.setAttribute("adminAllPosts", adminAllPosts);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin_posts.jsp?userId="+userId);
		rd.forward(request, response);
		
		
	}


}
