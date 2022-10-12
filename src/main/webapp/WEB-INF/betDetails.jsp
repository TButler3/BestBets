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
<link rel="stylesheet" href="/css/dashboard.css"/>
</head>
<body>
	<div class="bet-container">
		<c:set value="${jResults}" var="game"/>
		<p><c:out value='${game[0].getString("summary")}'/></p>
		<form:form action="/bet/create" method="post" modelAttribute="newBet">
			<div class="form-group">
				<form:label path="teamPicked">Pick Your Team</form:label>
				<form:errors path="teamPicked" class="text-danger"/>
				<form:select path="teamPicked">
					<c:forEach items="${jResults}" var="game">
						<form:option value='${game.getJSONObject("teams").getJSONObject("away").getString("team")}'><c:out value='${game.getJSONObject("teams").getJSONObject("away").getString("team")}'/></form:option>
						<form:option value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'><c:out value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'/></form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<form:label path="wager">Wager</form:label>
				<form:errors path="wager" class="text-danger"/>
				<form:input path="wager" type="number" class="form-control" style="width: 100px;"/>
			</div>
			<form:hidden path="user" value="${loggedInUser.id}"/>
			<c:set value="${jResults}" var="game"/>
			<form:hidden path="game" value='${game[0].getString("summary")}'/>
			
			<button class="btn btn-primary my-3">Place Bet</button>
		</form:form>
	</div>
</body>
</html>
	