<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>    
<%@ page import="javax.servlet.http.HttpSession" %>
<%
	HttpSession sesion = request.getSession(false);
	  if (sesion == null || !"admin".equals(sesion.getAttribute("role"))) {
	      response.sendRedirect("login_user.jsp");
	      return;
	  }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Post | Blog App</title>
    <link rel="stylesheet" href="create_post.css">
</head>
<body>
    <div class="container">
        <div class="newPost">
            <h1>Create New Post</h1>
			<a style="color:white; padding-left:500px" href="/logout">Logout</a>

            
        </div>

        <div class="hero">
        <!--  know encrypt ??-->
            <form action="createPostServlet" method="post" enctype="multipart/form-data" onsubmit="return confirm('Post Created successfully ?');">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required pattern="[a-zA-Z]+" title="Username can only contain letters (a-z, A-Z)"><br>
                <label for="content">Content:</label>
                <textarea id="content" name="content" required pattern="[a-zA-Z0-9,\.]+" title="Username can only contain letters (a-z, A-Z,0-9)"></textarea><br>
                <label for="image">Image:</label>
                <input type="file" id="image" name="imageFile"><br>
                <input type="submit" value="Create" id="create">
            </form>
        </div>
    </div>
</body>
</html>