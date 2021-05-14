<%@ page import="com.user_management.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #345;
}

li {
  float: left;
  border-right:1px solid #bbb;
   background-color: red;
}

li:last-child {
  border-right: none;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}




</style>

<meta charset="ISO-8859-1">
<title>user_managemant</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Items.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
<style type="text/css">


<script>
function validateForm() {
    //collect form data in JavaScript variables
    var pw1 = document.getElementById("Password").value;
    var pw2 = document.getElementById("Password2").value;
   
  
    //check empty password field
    if(pw1 == "") {
      document.getElementById("message1").innerHTML = "**Fill the password please!";
      return false;
    }
  
    //check empty confirm password field
    if(pw2 == "") {
      document.getElementById("message2").innerHTML = "**Enter the password please!";
      return false;
    } 
   
    //minimum password length validation
    if(pw1.length < 4) {
      document.getElementById("message1").innerHTML = "**Password length should be atleast 4 characters";
      return false;
    }

    //maximum length of password validation
    if(pw1.length > 15) {
      document.getElementById("message1").innerHTML = "**Password length not exceed 15 characters";
      return false;
    }
  
    if(pw1 != pw2) {
      document.getElementById("message2").innerHTML = "**Passwords are not same";
      return false;
    } else {
      alert ("Your password created successfully");
      document.write("JavaScript form has been submitted successfully");
    }
 }
</script>
</head>
<body>
<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#contact">Contact</a></li>
  <li style="float:right"><a href="register.jsp">login user registration</a></li>
</ul>
	<div class="container">
		<div class="row">
			<div class="col">


				<h1 style="text-align:center;">User Registration</h1>
				<h3 style="text-align:center;">welcome</h3>
				
				<form onsubmit ="return validateForm()" method='post' action='Items.jsp' id='formItem' name='formItem' >
					 User Name: <input id='UserName' name='UserName' type='text' class='form-control col-md-3'><br> 
					 User Email: <input id='Email' name='Email' type='text' class='form-control col-md-3'><br> 
					 User password: <input type="password" id='Password' name='Password' type='text' class='form-control col-md-3'><br>
					 <span id = "message1" style="color:red"> </span></br>
					 User Confirm password: <input type="password" id='Password2' name='Password2' type='text' class='form-control col-md-3'><br> 
					  <span id = "message2" style="color:red"> </span> </br>
					 User Address: <input id='Address' name='Address' type='text' class='form-control col-md-3'><br> 
					<input id='btnSave' name='btnSave' type='submit' value='Register' class='btn btn-primary'> 
					<input type='hidden' id='hidItemIDSave' name='hidItemIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				
			</div>
		</div>
	</div>
</body>
</html>