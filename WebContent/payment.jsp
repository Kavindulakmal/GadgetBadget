<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Add Payment </title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Payment.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">

			 <h1 class="m-3">Payment Management</h1>

  <form method="post" action="payment.jsp" id="formpayment" name="formpayment"> 
 
 
			 <div class="input-group input-group-sm mb-3">
			 <div class="input-group-prepend">
			 <span class="input-group-text" id="lbluserID">UserID: </span>
			 </div>
			 <input type="text" id="UserID" name="UserID">
			 </div>
 
			 <div class="input-group input-group-sm mb-3">
			 <div class="input-group-prepend">
			 <span class="input-group-text" id="lblUsername">UserName: </span>
			 </div>
			 <input type="text" id="payusername" name="payusername">
			 </div>
			 
			 <div class="input-group input-group-sm mb-3">
			 <div class="input-group-prepend">
			 <span class="input-group-text" id="lblproID"> Product ID: </span>
			 </div>
			 <input type="text" id="productid" name="productid">
			 </div>
			 
			 <div class="input-group input-group-sm mb-3">
			 <div class="input-group-prepend">
			 <span class="input-group-text" id="lblamount">Amount: </span>
			 </div>
			 <input type="text" id="payamount" name="payamount">
			 </div>
			 
			
			 <div class="input-group input-group-sm mb-3">
			 <div class="input-group-prepend">
			 <span class="input-group-text" id="lblmethod"> Method : </span>
			 </div>
			 <input type="text" id="paymethod" name="paymethod">
			 </div>
			 
		<!-- <input name="btnSubmit" type="submit" value="Save"  class="btn btn-primary"> -->
		
		 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 	
		 <input type="hidden" id="hidPayIDSave" name="hidPayIDSave" value=" ">
			 
		
			</div>
		</div>
	</div>
</form>
			 
			<br>

				<div id="alertSuccess" name="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" name="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divpayGrid">
				<%
				Payment paymentObjRead = new Payment();
				out.print(paymentObjRead.ReadPayment());
				%>
				</div>


</body>
</html>

