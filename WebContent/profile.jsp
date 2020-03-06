<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" type="text/css" href="table.css" />
<title>User Profile</title>
</head>

<style>
</style>

<body>
	<!-- Top navigation -->
	<div class="topnav">

		<!-- Centered link -->
		<div class="topnav-centered">
			<a href="dashboard" class="active">Home</a>
		</div>

		<!-- Left-aligned links (default) -->
		<a href="myrides"><i class="fa fa-car" aria-hidden="true"></i>My
			Rides</a> <a href="contactus.html"><i class="fa fa-fw fa-envelope"></i>Contact
			US</a> <a href="myprofile"><i class="fa fa-fw fa-user"></i>My Profile</a>

		<!-- Right-aligned links -->
		<div class="topnav-right">
			<a href="#search"><i class="fa fa-fw fa-search"></i>Search</a> <a
				href="index.html"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>
		</div>
	</div>
	<div style="padding-left: 16px"></div>
	<div class="container">

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
			<h3>Your Profile</h3>
				<br>
				<table id="club">
					<tr>
						<th>Name</th>
						<td>${user.name}</td>
					</tr>

					<tr>
						<th>Email</th>
						<td>${user.email}</td>
					</tr>
					<tr>
						<th>Mobile</th>
						<td>${user.phone}</td>
					</tr>

				</table>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

</body>
</html>