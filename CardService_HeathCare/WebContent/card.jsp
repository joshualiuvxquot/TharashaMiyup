
<%@page import="com.paf.controller.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Card Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/card.js"></script>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<div class="col-6">
			<br>
				<h2 style="text-align:center">Customer Card Details </h2>
				<h4 style="text-align:center">Online Payment </h4>
				<br>
						You can save your card details from here. </h6>
						<h6>You can't save your cvv number and  expiration date of your card. </h6>
	<br>

		<form id="formCard" name="formCard" >
				
					Customer ID: 
					<input id="customerID" name="customerID" type="text" class="form-control form-control-sm"> <br> 
					Card Number:
					<input id="cardNo" name="cardNo" type="text" class="form-control form-control-sm"> <br> 
					Name of the Card: 
					<input id="cardName" name="cardName" type="text" class="form-control form-control-sm"> <br> 
					Card Type: 
					<input id="cardType" name="cardType" type="text" class="form-control form-control-sm"> <br> 
					
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" > 
					<input type="hidden" id="hidCardIDSave" name="hidCardIDSave" >
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divCardsGrid" >
				<% BillDao Object = new BillDao();
						out.print(Object.readcardDetail());
						%>
				</div>


			</div>
		</div>
	</div>
</body>
</html>
    