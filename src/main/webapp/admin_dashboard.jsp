<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.blogapp.model.BlogPost, java.util.*, com.blogapp.daoImp.BlogPostBoImp" %>
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
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="admin_dashb.css">
</head>
<body >
    <div class="container">
        <div class="admin">
            <a href="create_post.jsp">Create New Post</a>
            <a href="logout" onclick="return confirm('Are you sure you want to Logout ?');">Logout</a>
            
        </div>
        <div class="hero">
            <table style="width:50%">
            <tr>
            	<td colspan="3"><h1> Admin Dashboard</h1></td>
            </tr>
                <tr>
                    <th>Title</th>
                    <th>Post Id</th>
                    <th>Actions</th>
                </tr>
                <%
                    List<BlogPost> posts = (List<BlogPost>) request.getAttribute("adminPosts");
                    if (posts != null) {
                        for (BlogPost post : posts) {
                %>
                <tr>
                    <td><%= post.getPostTitle() %></td>
                    <td><%= post.getPostId() %></td>
                    <td>
                        <a href="editPostServlet?postId=<%= post.getPostId() %>">Edit |</a>
                        <a href="deletePostServlet?postId=<%= post.getPostId() %>"onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><img src="display?imageId=<%= post.getImageId() %>" height="400px" width="400px" alt="Image" /></td>
                </tr>
                <tr>
                    <td colspan="3">Content:  <%= post.getPostContent() %></td>
                
                </tr>
                <tr style="ba;">
    				<td colspan="3">-----</td>
				</tr>

                <%
                        }
                    } else {
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
