package com.blogapp.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/createPostServlet")
@MultipartConfig(maxFileSize = 16177215) 
public class CreatePostServlet extends HttpServlet {
    public CreatePostServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int userId =(int) session.getAttribute("userId");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Part filePart = request.getPart("imageFile");
		
		InputStream inputStream =null;
		if(filePart !=null)
		{
			inputStream	= filePart.getInputStream();
			
		}
		BlogPost bp = new BlogPost(userId,title, content);
		
		BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
		
		int row = objBImp.savePost(bp,inputStream);
		
		if( row > 0)
		{
			response.sendRedirect("readAdminPostServlet");
		}
		else{
			out.println("UnSucessfully....");
		}
		
		
	
	}


}

