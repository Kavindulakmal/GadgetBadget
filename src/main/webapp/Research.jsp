<%@ page import="com.paf10.Research"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Research Details</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Items.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">


				<h1>Research Details Upload</h1>
				<form method='post' action='Research.jsp' id='ResearchItem' name='ResearchItem'>
					Researcher Name: <input id='userName' name='userName' type='text' class='form-control col-md-3'><br> 
					Researcher Email: <input id='email' name='email' type='text' class='form-control col-md-3'><br> 
					Title: <input id='title' name='title' type='text' class='form-control col-md-3'><br> 
					Description: <input id='descrip' name='descrip' type='text' class='form-control col-md-3'><br>
					Price: <input id='price' name='price' type='text' class='form-control col-md-3'><br> 
					<input id='btnSave' name='btnSave' type='button' value='Save' class='btn btn-primary'> 
					<input type='hidden' id='hidItemIDSave' name='hidItemIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				<br>
				<div id="divItemsGrid">
				<%
				Research itemObjRead = new Research();
				out.print(itemObjRead.readResearch());
				%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>