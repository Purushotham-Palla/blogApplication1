<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.blogapp.model.User,java.util.*" %>   
    
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || !"viewer".equals(sesion.getAttribute("role"))) {
        response.sendRedirect("login_user.jsp");
        return;
    }
%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Viewer Dashboard</title>
	    <link rel="stylesheet" href="viewer_dashboard.css">
	</head>
	<body>
		<div class="container">
            <div class="admin">
                
            </div>
            <div class="hero">
            <table style="width:50%">
            <tr>
            	<td style="height:20px" colspan="3"><a href="logout" onclick="return confirm('Are you sure you want to Logout ?');" >Logout</a></td>
            </tr>
            	
            <tr>
            	<td colspan="3"><h1>Viewer Dashboard</h1></td>
            </tr>
	        <tr>
	            <th>Admin</th>
	            <th>Action</th>
	        </tr>
	         <%
	         	        List<User> admins =(List<User>)request.getAttribute("adminsAll");
	         	        
	                         for (User ad : admins) {
	         %> 
            <tr>
                 <td><%= ad.getName()%></td>
                 
                <td>
                   <a href="viewAdminPosts?adminId=<%= ad.getUserId() %>" target="_blank" >View</a>

                </td>
            </tr>
            <% 
                } 
            %> 
	        
	    </table>
	
                
            </div>
        </div>
            
            
            
	    
	</body>
</html>
