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
	<h1>test</h1>
	<c:forEach items="${jResults}" var="game">
		<p><c:out value='${game.getJSONObject("teams").getJSONObject("away").getString("team")}'/></p>
		<p><c:out value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'/></p>
	</c:forEach>
	<c:set value="${jResults}" var="game"/>
	<p>test<c:out value='${game[0].getString("summary")}'/></p>
	<form:form action="/bet/create" method="post" modelAttribute="newBet">
		<div class="form-group">
			<form:label path="teamPicked">Pick Your Team</form:label>
			<form:errors path="teamPicked" class="text-danger"/>
			<form:select path="teamPicked">
				<c:forEach items="${jResults}" var="game">
					<form:option value='away'><c:out value='${game.getJSONObject("teams").getJSONObject("away").getString("team")}'/></form:option>
					<form:option value='home'><c:out value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'/></form:option>
				</c:forEach>
			</form:select>
		</div>
		<div class="form-group">
			<form:label path="wager">Wager</form:label>
			<form:errors path="wager" class="text-danger"/>
			<form:input path="wager" type="number" class="form-control" style="width: 100px;"/>
		</div>
		<c:if test="${teamPicked == away}">
			<c:set value="${jResults}" var="game"/>
			<form:hidden path="teamNotPicked" value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'/>
		</c:if>
		<form:hidden path="user" value="${loggedInUser.id}"/>
		<c:set value="${jResults}" var="game"/>
		<form:hidden path="game" value='${game[0].getString("summary")}'/>
		<button class="btn btn-primary my-3">Place Bet</button>
	</form:form>
</body>
</html>
	