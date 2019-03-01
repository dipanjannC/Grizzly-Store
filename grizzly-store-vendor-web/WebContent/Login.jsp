<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<style>
body, html {
  height: 100%;
  font-family: Arial, Helvetica, sans-serif;
}
.form-font
{
	font-size:14px;
}

* {
  box-sizing: border-box;
}

.bg-img {
  /* The image used */
   background-image:url("img/Home-login.jpg");

  min-height: 100%;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

 hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 20px;
}

/* Add styles to the form container */


.container {
  position: relative;
  top:540px;
  
  max-width: 270px;
  padding: 12px;
  background-color: transparent;
  border-radius: 4px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 2px;
  margin-bottom: 2px;
  text-align: center;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px;
  margin: 3px 0 10px 0;
  border: none;
  background: #f1f1f1;
  border-radius: 50px;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit button */
.btn {
  background-color: #9494b8;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.8;
  border-radius: 50px;
}

.btn:hover {
  opacity: 1.5;
}
/* Set a style for the submit button */
</style>
</head>
<body>


<div class="bg-img">
  
  <form action="Login" class="container" method="post" >
    <p><%
    
    String msg=(String)request.getAttribute("error");
    if(msg!=null)
    	out.println(msg);
		
    %></p>
    <input type="text" placeholder="Enter Username" name="username" required>
    <input type="password" placeholder="Enter Password" name="password" required>
    
    
    <hr>
	<button type="submit" class="btn">Login</button>

  </form>
  
</div>


</body>
</html>
