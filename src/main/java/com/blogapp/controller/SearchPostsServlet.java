package com.blogapp.controller;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/searchPostsServlet")
public class SearchPostsServlet extends HttpServlet {
    public SearchPostsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("searchTitle");
        String dateStr = request.getParameter("searchDate");
        int userId = Integer.parseInt(request.getParameter("userId"));
        Date date = null; // Convert dateStr to Date object if needed
        
        // Call DAO to fetch filtered posts based on title and/or date
        BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
        
//        List<BlogPost> filteredPosts = objBImp.searchPosts(title, date,userId);
        List<BlogPost> filteredPosts = objBImp.searchPosts(title,userId);

        request.setAttribute("adminAllPosts", filteredPosts);
        
        request.getRequestDispatcher("admin_posts.jsp").forward(request, response);
	}


}
