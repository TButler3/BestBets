<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
	<h1>Edit Bet</h1>
	<form:form action="/bet/${bet.id}" method="post" modelAttribute="bet">
		<input type="hidden" name="_method" value="put"/>
		<div class="form-group">
			<form:label path="teamPicked">Pick Your Team</form:label>
			<form:errors path="teamPicked" class="text-danger"/>
			<form:input path="teamPicked" class="form-control" style="width: 225px;"/>
				
		</div>
		<div class="form-group">
			<form:label path="wager">Wager</form:label>
			<form:errors path="wager" class="text-danger"/>
			<form:input path="wager" type="number" class="form-control" style="width: 100px;"/>
		</div>
		<form:hidden path="user" value="${loggedInUser.id}"/>		
		<button class="btn btn-primary my-3">Place Bet</button>
	</form:form>
	<form action="/delete/${bet.id}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" value="delete" class="mx-2"/>
	</form>
</body>
</html>