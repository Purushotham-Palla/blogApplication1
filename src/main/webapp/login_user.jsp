<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Login | Blog App</title>
	    <link rel="stylesheet" href="login_user.css">
	    <style type="text/css">
	    a{
	    background-color:#4C7E7E;
	    color:white;
	    text-decoration:none;
	    }
	    </style>
	</head>
	<body>
    <div class="login-details">
        <div class="details">
            <a href="index.jsp">Home</a>
            <p style="font-size:15px">Check your Login Details twice</p>
            <p id="login">Login</p>
            <form action="loginUserCheck" method ="post">                
                <input type="email" name=email id="text-box" required placeholder="Email" ><br><br>
                <input type="password" name="password" id="text-box" required placeholder="Password"><br><br>
                
                <input type="submit" id="submit" >
              </form>
        </div>
        <br>
        
    </div>
</body>
</html>