<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="navbar.css" />
<title>Dashboard</title>
<style>


@charset "ISO-8859-1";
#club {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 50%;
}

#club td, #club th {
	border: 1px solid #ddd;
	padding: 7px;
}

#club tr:nth-child(even) {
	background-color: #f2f2f2;
}

#club tr:nth-child(odd) {
	background-color: #f2f2f2;
}

#club td {
	text-align: center;
}

#club tr:hover {
	background-color: #ddd;
}

#club th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #4d97a5;
	color: white;
}
</style>
</head>
<body>

	<!-- Top navigation -->
	<div class="topnav">

		<!-- Centered link -->
		<div class="topnav-centered">
			<a href="#home" class="active">Home</a>
		</div>

		<!-- Left-aligned links (default) -->
		<a href="#news"><i class="fa fa-car" aria-hidden="true"></i>My
			Rides</a> <a href="#contact"><i class="fa fa-fw fa-envelope"></i>Contact</a>

		<!-- Right-aligned links -->
		<div class="topnav-right">
			<a href="#search"><i class="fa fa-fw fa-search"></i>Search</a> <a
				href="#about"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>
		</div>
	</div>

	<div style="padding-left: 16px"></div>

	<div class="container">

		<div class="row">
			<div class="col-md-6">
				<!-- Show Booking option -->
				
				
			</div>
			<div class="col-md-6">
				<!-- Show user details -->
				
				<table id="club">
				<tr>
				<th>Name</th>
				<td></td>
				</tr>
				
				<tr>
				<th>Email</th>
				<td></td>
				</tr>
				<tr>
				<th>Mobile</th>
				<td></td>
				</tr>
				
				</table>
				
			</div>
		</div>
	</div>

</body>
</html>