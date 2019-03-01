<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.grizzly.pojo.ProductDetailsPojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Links -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
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
/* Check Box */
.round label {
	background-color: #fff;
	border: 1px solid black;
	border-radius: 50%;
	cursor: pointer;
	height: 20px;
	width: 20px;
	left: 0;
	position: absolute;
	top: 0;
}

.round label:after {
	border: 2px solid #fff;
	border-top: none;
	border-right: none;
	content: "";
	height: 4px;
	left: 4px;
	opacity: 0;
	position: absolute;
	top: 7px;
	transform: rotate(-45deg);
	width: 10px;
}

.round input[type="checkbox"] {
	visibility: hidden;
}

.round input[type="checkbox"]:checked+label {
	background-color: black;
	border-color: white;
}

.round input[type="checkbox"]:checked+label:after {
	opacity: 1;
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


	<!-- Table -->


	<%
		ArrayList<ProductDetailsPojo> productsViewAdmin = (ArrayList<ProductDetailsPojo>) request.getAttribute("productsViewAdmin");
		Iterator<ProductDetailsPojo> itr = productsViewAdmin.iterator();
	%>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4" style="background-color: 	 #b3b3cc;"><h2 align="justify">Profile</h2>
			</div>
			<div class="col-md-8">
				<div class="btn-group btn-group-justified">
					<a href="#" class="btn btn-grizzly">Products</a> <a href="#"
						class="btn btn-grizzly" disabled>Vendors</a> <a href="#"
						class="btn btn-grizzly" disabled></a>
				</div>

				<div >
					<p align="right">Hi <%= session.getAttribute("username") %>,</p>
				</div>

				<div>
					<table>
						<tr>
							<th></th>
							<th class="td-options">Products List</th>
							<th class="td-options">Brand</th>
							<th class="td-options">Category</th>
							<th class="td-options">Rating</th>
							<th></th>
						</tr>
						<%
	while(itr.hasNext())
	{
		ProductDetailsPojo pojo=(ProductDetailsPojo)itr.next();
	
%>
						<tr>
							<td><div class="pretty p-icon p-round p-jelly">
									<input type="checkbox" />
									<div class="state p-primary">
										<i class="icon mdi mdi-check"></i> <label>Select</label>
									</div>
								</div></td>
							<td class="td-options"><%=pojo.getProductName() %></td>
							<td class="td-options"><%=pojo.getProductBrand() %></td>
							<td class="td-options"><%=pojo.getProductCategory() %></td>
							<td class="td-options"><%=pojo.getProductRating() %></td>
							<td><button class="button btn-content" disabled>View</button></td>
							<td><button class="button btn-content"disabled>Update</button></td>
							<td><button class="button btn-content" >
									<a href="DeleteServlet?productId=<%= pojo.getProductId() %>">Delete
								</button></td>
						</tr>
						<% } %>
					</table>


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
				<!--Update Modal-->
				<div class="modal-body">
					<hr>
					Are you Sure? <br>
					<hr>
					<form action="Logout" method="get">
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