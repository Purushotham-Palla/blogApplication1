package com.blogapp.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.blogapp.daoImp.UserBoImp;
import com.blogapp.model.User;

@WebServlet("/loginUserCheck")

public class LoginUserServlet extends HttpServlet {
    public LoginUserServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//from user input
		String user_email = request.getParameter("email");
		String user_password = request.getParameter("password");
		
		String salt = BCrypt.gensalt();
		
		
//		String hashedEmail1 = BCrypt.hashpw(user_email, salt);
		String hashedPassword1 = BCrypt.hashpw(user_password, salt);
		
		
		//Fecthing hash data
		
		UserBoImp loginBoImp = new UserBoImp();
		
		User login = loginBoImp.userLogin(user_email);
//		User login = loginBoImp.userLogin(hashedEmail1, hashedPassword1);
		
		if(login !=null)
		{
			String hashedEmail2 = login.getEmail();
			String hashedPassword2 = login.getPassword();
			
			String role = login.getRole().toLowerCase();
			
			int userId = login.getUserId();
			
			if(BCrypt.checkpw(user_password, hashedPassword2))
			{
					HttpSession session = request.getSession();
					session.setAttribute("role", role);
					session.setAttribute("userId", userId);
					
					 if ("admin".equals(role)) {
		                 response.sendRedirect("readAdminPostServlet");
		             } else {
		                 response.sendRedirect("readviewerBrowsePosts");
		             }
			}
			else {
				response.sendRedirect("login_user.jsp");
				
			}
	        
		}
		
		else {
			response.sendRedirect("login_user.jsp");
	////			out.println("Login Fail.......Please Try again");
	//			
		}
		
	
		
		
		
		
	
	}
}

