<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register | Blog App</title>
    <link rel="stylesheet" href="register_user.css">
    <style>
        
    </style>
</head>
<body>
    <div class="details">
        <div class="form">
                <a href="index.jsp">Home</a>
            <form action="registerUser" method="post">
                <p><b>Register</b></p>
                <input type="text"name="name"id="text-box" required placeholder="User Name" pattern="[a-zA-Z]+" title="Username can only contain letters (a-z, A-Z)"><br><br>
                <input type="email" name=email id="text-box" required placeholder="Email" ><br><br>
                <input type="password" name="password"  id="text-box" required placeholder="Password"><br><br>
                <label for="">Role :</label>
                <select name="role" id="role">
                    <option value="Admin" >Admin</option>
                    <option value="Viewer">Viewer</option>
                </select><br><br>
                <input type="submit" id="submit" value="Register">
            </form>
         </div>
    </div>
</body>
</html>