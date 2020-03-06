<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="navbar.css" />
<title>All driver details</title>
</head>
<body>

	<div class="topnav">
		<!-- Centered link -->
		<div class="topnav-centered">
			<a href="admin.html" class="active">Admin Home</a>
		</div>

		<!-- Left-aligned links (default) -->
		<a href="viewallrides"><i class="fa fa-car" aria-hidden="true"></i>Show
			all Rides</a> <a href="viewalldrivers"><i class="fa fa-fw fa-user"></i>Show
			All Drivers</a>

		<!-- Right-aligned links -->
		<div class="topnav-right">
			<a href="#search"><i class="fa fa-fw fa-search"></i>Search</a> <a
				href="index.html"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>
		</div>
	</div>


	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<h3>All Drivers info</h3>
			<table id="route" class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th>Email</th>
					<th>Phone</th>
					<th>DLID</th>
					<th>Car Model</th>
					<th>Vechile No</th>
					<th>Address</th>
					<th>Status</th>
					<th>Update</th>

				</tr>
				<tr>
					<c:forEach items="${drivers}" var="d">
						<tr>
							<td>${d.name}</td>
							<td>${d.age}</td>
							<td>${d.email}</td>
							<td>${d.phone}</td>
							<td>${d.dlid}</td>
							<td>${d.carname}</td>
							<td>${d.vechileno}</td>
							<td>${d.address}</td>
							<td>${d.status}</td>
							<td><a class="btn btn-primary"
								href="reviewstatus?dlid=${d.dlid}">Update</a></td>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>