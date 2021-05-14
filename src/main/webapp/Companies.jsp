<%@ page import="com.paf10.Company"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company Management</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Items.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">


				<h3>Add Your Research Details here to Select a Company for your Funds.</h3>
				
				<form method='post' action='Items.jsp' id='formItem' name='formItem'>
					Register Code: <input id='cliCode' name='cliCode' type='text' class='form-control col-md-3'><br> 
					Register Email: <input id='cliEmail' name='cliEmail' type='text' class='form-control col-md-3'><br> 
					Research Budget: <input id='cliBudget' name='cliBudget' type='text' class='form-control col-md-3'><br> 
					Research Description: <input id='cliDesc' name='cliDesc' type='text' class='form-control col-md-3'><br> 
					Company: <input id='cliCom' name='cliCom' type='text' class='form-control col-md-3'><br>
					<input id='btnSave' name='btnSave' type='button' value='Save' class='btn btn-primary'> 
					<input type='hidden' id='hidItemIDSave' name='hidItemIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				<br>
				<div id="divItemsGrid">
				<%
					Company itemObjRead = new Company();
						out.print(itemObjRead.readItems());
				%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>