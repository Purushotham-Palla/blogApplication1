<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession,com.blogapp.model.BlogPost" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || !"admin".equals(sesion.getAttribute("role"))) {
       response.sendRedirect("login_user.jsp");
        return;
    }
%>
    
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Document</title>
	    <link rel="stylesheet" type="text/css" href="edit_post.css">
	    <!-- <link rel="icon" type="image" href="man.png"> -->
	    <style type="text/css">
	    a{
	    margin-left:500px;
	    background-color:#4C7E7E;
	    color:white;
	    max-width:fit-content;
	    text-decoration:none;
	    }
	    </style>
	</head>
	<body >
		<%
		BlogPost bp =(BlogPost)request.getAttribute("onepost"); 
		
		%>
			
		    <div class="container">
	            
	            <div class="editPost">
	                <h1>Edit Post</h1>
					<a href="readAdminPostServlet">Admin DashBoard</a>
					<a href="logout">Logout</a>
	            </div>
		        <div class="hero">
	                <form action="updatePostServlet" method="post" enctype="multipart/form-data" onsubmit="return confirm('Post Edited successfully');" >
	                    
	                    <input type="number" style="display: none" name="postId" value="<%= bp.getPostId() %>">
	                    
	                    <label for="title">Title:</label>
	                    
	                    <input type="text" id="title" name="title" value="<%= bp.getPostTitle()%>" required pattern="[a-zA-Z ]+" title="Username can only contain letters (a-z, A-Z)"><br>
	                    
	                    <label for="content">Content:</label>
	                    
	                    <textarea id="content" name="content" required pattern="[a-zA-Z0-9,\.]+" title="Username can only contain letters (a-z, A-Z,0-9)"><%= bp.getPostContent() %> </textarea><br>
	                    
	                    <label for="image">Image:</label>
	                    
	                    <input type="file" id="image" name="imageFile" alt="Image" required/><br>
	                    
	                    <input type="submit" value="Update" id="submit">
	                </form>
		        </div>
		    </div>
	
	
	</body>
</html>