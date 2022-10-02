<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
	<div class="hero-image">
		<div class="container">
			<h1 class="my-3 text-center" id="index_title">Best Bet</h1>
			<div class="row">
				<div class="col">
				<h1>Register</h1>
				
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="form-group">
						<form:label path="username">Username</form:label>
						<form:errors path="username" class="text-danger"/>
						<form:input path="username" class="form-control" style="width: 200px;"/>
					</div>
					<div class="form-group">
						<form:label path="email">Email</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" class="form-control" style="width: 200px;"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:password path="password" class="form-control" style="width: 200px;"/>
					</div>
					<div class="form-group">
						<form:label path="confirmPassword">Confirm Password</form:label>
						<form:errors path="confirmPassword" class="text-danger"/>
						<form:password path="confirmPassword" class="form-control" style="width: 200px;"/>
					</div>
					<button class="my-3 btn btn-primary">Register</button>
				</form:form>
				</div>
				<div class="col">
				<h1>Login</h1>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="form-group">
						<form:label path="email">Email</form:label>
						<form:errors path="email" class="text-danger"/>
						<form:input path="email" class="form-control" style="width: 200px;"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password</form:label>
						<form:errors path="password" class="text-danger"/>
						<form:password path="password" class="form-control" style="width: 200px;"/>
					</div>
					<button class="my-3 btn btn-primary">Login</button>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>