<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.grizzly.pojo.InventoryPojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Links -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
		href=" https://cdn.jsdelivr.net/npm/pretty-checkbox@3.0/dist/pretty-checkbox.min.css">
<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.5.95/css/materialdesignicons.css">

	

<!-- Links -->

<title>Home</title>

<style>
th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.td-options {
	border-right: 1px solid #ddd;
}

tr:hover {
	background-color: #f5f5f5;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 4px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 60px;
}

.btn-grizzly{
background-color:#d1d1e0;
color:black;
opacity:0.7;
}

.btn-grizzly:hover{
opacity:1.4;
}

.btn-content {
	background-color: #e7e7e7;
	color: black;
}

.form-control {
	border-radius: 70px;
}

.btn {
	border-radius: 70px;
	background-color: #d1d1e0;
}

.btn-icon {
	color: balck;
	padding: 3px;
	background-color: transparent;
}

/* ADD PRODUCT Form*/
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 10px;
}
input[type=text],input[type=number] {
  width: 100%;
  padding: 12px 20px;
   background: #e0e0eb;
    border: none;
 } 
   
 
    

/* ADD PRODUCT Form*/

</style>
</style>
</head>



<header> <!--Navigation Bar--> 
<nav class="navbar navbar-expand-lg bg-default ">
<div class="container">
	<div class="navbar-header">
		<img src="img/logo.png" class="navbar-brand"  width="300" height="150">
	</div>

	<form class="navbar-form navbar-left" action="/action_page.php">
		<div class="input-group" >
			<input type="text" class="form-control" placeholder="Search"
				name="search">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit" disabled>
					<i class="glyphicon glyphicon-search" style="font-size: 20px"></i>
				</button>
			</div>
		</div>
	</form>
	<form class="navbar-form">
		<button type="button" class="btn btn-basic btn-icon">
			<i class="fa fa-bell" style="font-size: 24px"></i> <span
				class="badge badge-light">2</span>
		</button>
	</form>


	<form class="navbar-form navbar-right" align="right">
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#logoutModal">Logout</button>
	</form>
</div>
</nav> <!--Navigation Bar--> </header>


<body>
	<h2>Product Details</h2>




	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4" style="background-color: 	 #b3b3cc;"><h2 align="justify">Profile</h2>
			</div>
			<div class="col-md-8">
				<div class="btn-group btn-group-justified">
					<a href="FetchServlet" class="btn btn-grizzly"><strong>PRODUCTS</strong></a> 
					<a href="FetchServlet?inventory=true" class="btn btn-grizzly" ><strong>VENDORS</strong></a> 
					<a href="#" class="btn btn-grizzly" disabled><strong>PROFILE</strong></a>
				</div>

				<div >
					<p align="right">Hi <%= session.getAttribute("username") %>,</p>
				</div>

				<div>
<%
ArrayList<InventoryPojo> inventoryList = (ArrayList<InventoryPojo>) request.getAttribute("inventoryList");
Iterator<InventoryPojo> itr = inventoryList.iterator();
while(itr.hasNext())
{
	InventoryPojo pojo=(InventoryPojo) itr.next();
%>

					<form >
					<h2>Update Stock </h2><hr>
					ProdutID: <input type="number" name="inventoryProductId" placeholder="<%= pojo.getProductId() %>" readonly/><hr>
					Buffer: <input type="number" name="inventoryStock" placeholder="<%=pojo.getInventoryStock() %>"/><hr>
					Stock: <input type="number" name="inventoryBuffer" placeholder="<%=pojo.getInventoryBuffer()%>"  readonly/><hr>
					<div align="right">
					<a href="AddDeleteProduct?update=true&productId=<%=pojo.getProductId()%>" class="btn btn-content" role="button" >Update</a>
					<input type="reset" class="btn btn-content" value="Cancel">
					</div>
					</form>	


<% }%>
				</div>


			</div>
		</div>
	</div>




	<!-- Table -->




	<!--Logout Modal-->
	<div class="modal fade" id="logoutModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Logout</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				
				<div class="modal-body">
					<hr>
					Are you Sure? <br>
					<hr>
					<form action="LoginLogout" method="post">
						<input type="submit" name="logout-option" value="Yes"
							class="btn btn-info btn-block" /> <br>

					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	<!--Logout Modal-->




</body>
</html>