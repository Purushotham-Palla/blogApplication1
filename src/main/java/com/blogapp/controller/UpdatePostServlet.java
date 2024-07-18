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
import javax.servlet.http.Part;

import com.blogapp.daoImp.BlogPostBoImp;
import com.blogapp.model.BlogPost;

@WebServlet("/updatePostServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UpdatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePostServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int postId =  Integer.parseInt(request.getParameter("postId"));
	
	String title = request.getParameter("title");
	
	String content = request.getParameter("content");
	
	Part filePart = request.getPart("imageFile");
	InputStream inputStream =null;
	if(filePart !=null)
	{
		inputStream	= filePart.getInputStream();
		
	}
	
	boolean edited =true;
	
//	String stat=request.getParameter("status");
	
	BlogPost blogPost = new BlogPost(postId,title,content,edited);
	BlogPostBoImp objBImp = BlogPostBoImp.objBlogPostBoImp();
	int rows = objBImp.updatePost(blogPost,inputStream);
	PrintWriter out = response.getWriter();
	
	if(rows>0)
	{
		response.sendRedirect("readAdminPostServlet");
	}
	else {
		out.println("Records Not Updated.....");
		
	}
	
	}


}
