<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.grizzly.pojo.InventoryDetailsPojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Links -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
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

.btn-grizzly {
	background-color: #d1d1e0;
	color: black;
	opacity: 0.7;
}

.btn-grizzly:hover {
	opacity: 1.4;
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



<header> <!--Navigation Bar--> <nav
	class="navbar navbar-expand-lg bg-default ">
<div class="container">
	<div class="navbar-header">
		<img src="img/logo.png" class="navbar-brand" width="300" height="150">
	</div>

	<form class="navbar-form navbar-left" action="/action_page.php">
		<div class="input-group">
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
		<button type="button" class="btn btn-basic btn-icon"
			data-toggle="modal" data-target="#notificationModal">
			<i class="fa fa-bell" style="font-size: 24px"></i> <span
				class="badge badge-light">2</span>
		</button>
	</form>


	<form class="navbar-form navbar-right" align="right">
		<button type="button" class="btn btn-default" data-toggle="modal"
			data-target="#logoutModal">Logout</button>
	</form>
</div>
</nav> <!--Navigation Bar--> </header>


<body>
	<h2>Product Details</h2>


	<!-- Table -->


	<%
		ArrayList<InventoryDetailsPojo> inventoryView = (ArrayList<InventoryDetailsPojo>) request.getAttribute("inventoryView");
		Iterator<InventoryDetailsPojo> itr = inventoryView.iterator();
	%>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4" style="background-color: #b3b3cc;">
				<h2 align="justify">Profile</h2>
			</div>
			<div class="col-md-8">
				<div class="btn-group btn-group-justified">
					<a href="FetchServlet" class="btn btn-grizzly"><strong>PRODUCTS </strong></a>
					<a href="FetchServlet?inventory=true" class="btn btn-grizzly"><strong>INVENTORY</strong></a>
					 <a href="#" class="btn btn-grizzly" disabled><strong>PROFILE</strong></a>
				</div>

				<div>
					<p align="right">
						Hi
						<%=session.getAttribute("username")%>,
					</p>
				</div>

<%
if(request.getParameter("inventory")==null)
{

%>
				<div>
					<table>
						<tr>
							<th></th>
							<th class="td-options">Products List</th>
							<th class="td-options">ID</th>
							<th class="td-options">Brand</th>
							<th class="td-options">Category</th>
							<th class="td-options">Offer</th>
							<th></th>
						</tr>
						<%
							while (itr.hasNext()) {
								InventoryDetailsPojo pojo = (InventoryDetailsPojo) itr.next();
						%>
						<tr>
							<td><div class="pretty p-icon p-round p-jelly">
									<input type="checkbox" />
									<div class="state p-primary">
										<i class="icon mdi mdi-check"></i> <label>Select</label>
									</div>
								</div></td>
							<td class="td-options"><%=pojo.getProductName()%></td>
							<td class="td-options"><%=pojo.getProductId()%></td>
							<td class="td-options"><%=pojo.getProductBrand()%></td>
							<td class="td-options"><%=pojo.getProductCategory()%></td>
							<td class="td-options">0</td>
							<td><a href="FetchServlet?manage=ture&inventoryProductId=<%=pojo.getProductId()%>" class="btn btn-content" role="button" >Manage</a></td>
							<td><a href="AddDeleteProduct?delete=true&productId=<%=pojo.getProductId()%>" class="btn btn-content" role="button" disabled>Delete</a></td>
						</tr>
						<%
							}
						%>
					</table>


				</div>
<%
}
else
{
%>
<div>
					<table>
						<tr>
							<th></th>
							<th class="td-options">Products List</th>
							<th class="td-options">ID</th>
							<th class="td-options">In Stock</th>
							<th class="td-options">Buffer</th>
							<th class="td-options">Price/Items</th>
							<th class="td-options">Rating</th>
							<th></th>
						</tr>
						<%
							while (itr.hasNext()) {
								InventoryDetailsPojo pojo = (InventoryDetailsPojo) itr.next();
						%>
						<tr>
							<td><div class="pretty p-icon p-round p-jelly">
									<input type="checkbox" />
									<div class="state p-primary">
										<i class="icon mdi mdi-check"></i> <label>Select</label>
									</div>
								</div></td>
							<td class="td-options"><%=pojo.getProductName()%></td>
							<td class="td-options"><%=pojo.getProductId()%></td>
							<td class="td-options"><%=pojo.getInventoryStock()%></td>
							<td class="td-options"><%=pojo.getInventoryBuffer()%></td>
							<td class="td-options"><%=pojo.getProductPrice()%></td>
							<td class="td-options"><%=pojo.getProductRating()%></td>
							<td><a href="FetchServlet?manage=true&inventoryProductId=<%=pojo.getProductId()%>" class="btn btn-content" role="button" >Manage</a></td>
						
						</tr>
						<%
							}
						%>
					</table>


				</div>
<%
}
%>
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

	<!--Notification Modal-->
	<div class="modal fade" id="notificationModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Notifications</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<!--Update Modal-->
				<div class="modal-body">
					<hr>
					There are no notifications yet. <br>
					<hr>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	<!--Notification Modal-->






</body>
</html>