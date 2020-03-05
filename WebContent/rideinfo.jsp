<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>Ride Info</title>
<style>
</style>
</head>
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

	<table id="club" align="left">
		<c:forEach items="${driver}" var="r">
			<tr>
				<th>Name</th>
				<td>${r.name}</td>
			</tr>
		</c:forEach>
	</table>   

</body>
</html>