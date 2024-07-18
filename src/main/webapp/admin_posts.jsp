<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.blogapp.model.BlogPost,java.util.*,com.blogapp.daoImp.BlogPostBoImp" %>

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
	    <title>Admin Posts</title>
	    <link rel="stylesheet" href="admin_posts.css">
	    <style type="text/css">
	    a{
	    background-color:#4C7E7E;
	    color:white;
	    text-decoration:none;
	    }
	    </style>
	</head>
	<body>
		<div class="container">
            
            <div class="admin">
            
                
            </div>
			
            <div class="hero">
            <table style="width:50%">
            <tr>
            	<td colspan="2"><a href="readviewerBrowsePosts">Viewer DashBoard</a></td>
            	<td><a href="logout" onclick="return confirm('Are you sure you want to Logout ?');">Logout</a></td>
          	</tr>
            <tr>
            	<td colspan="3"><h1> Admin Posts</h1></td>
			<tr>
            	<td colspan="3">
            	  <%
                                int userId = 0;
                                if (request.getParameter("userId") != null) {
                                    userId = (Integer.parseInt(request.getParameter("userId")));
                                }
                            %>
                   <form action="searchPostsServlet" method="get">
				    <input style="display: none" type="number" id="userId" value="<%=userId %>" name="userId">
	            
				    <label for="searchTitle">Search by Title:</label>
				    <input type="text" id="searchTitle" name="searchTitle">
				    <br>
				    
				    <button type="submit">Search</button>
				</form>
				</td>
			
			</tr>
            </tr>
            
                <tr>
                    <th>Title</th>
                    <th>Post Id</th>
                </tr>
                <%
                    List<BlogPost> posts = (List<BlogPost>) request.getAttribute("adminAllPosts");
                    if (posts != null) {
                        for (BlogPost post : posts) {
                %>
                <tr>
                    <td><% userId = post.getUserId(); %></td>
                    <td><%= post.getPostTitle() %></td>
                    <td><%= post.getPostId() %></td>
                </tr>
                <tr>
                    <td colspan="3"><img src="display?imageId=<%= post.getImageId() %>" height="500px" width="500px" alt="Image" /></td>
                </tr>
                <tr>
                    <td colspan="3">Content:  <%= post.getPostContent() %></td>
                
                </tr>
                <tr >
    				<td style="border:none"  colspan="3">_____________________________</td>
				</tr>

                <%
                    }
                    %>
                        <tr >
        				<td style="border:none"  colspan="3">No posts available</td>
    				</tr>
    				<%
                    }
                    else {
                %>
                <tr>
                    <td colspan="3">No posts available</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        </div>
            
            
            
	    
	</body>
</html>