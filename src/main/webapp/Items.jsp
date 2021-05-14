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

body {
 background-image: url("g.png");
 background-color: #cccccc;
}
</style>
<meta charset="ISO-8859-1">
<title>user Management</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Items.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#news">News</a></li>
  <li><a href="#contact">Contact</a></li>
  <li style="float:right"><a href="user_reg.jsp">login user registration</a></li>
</ul>
	<div class="container">
		<div class="row">
			<div class="col">
			


				<h1 style="text-align:center">User Management</h1>
				<form onsubmit ="return validateForm()" method='post' action='Items.jsp' id='formItem' name='formItem' >
					 User Name: <input id='UserName' name='UserName' type='text' class='form-control col-md-3'><br> 
					 User Email: <input id='Email' name='Email' type='text' class='form-control col-md-3'><br> 
					 User password: <input type="password" id='Password' name='Password' type='text' class='form-control col-md-3'><br>
					 User Address: <input id='Address' name='Address' type='text' class='form-control col-md-3'><br> 
					<input id='btnSave' name='btnSave' type='submit' value='save' class='btn btn-primary'> 
					<input type='hidden' id='hidItemIDSave' name='hidItemIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				<br>
				<div id="divItemsGrid">
				<%
				Item itemObjRead = new Item();
				out.print(itemObjRead.readItems());
				%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>