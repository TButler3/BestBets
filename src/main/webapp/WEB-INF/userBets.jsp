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
<link rel="stylesheet" href="/css/games.css"/>
</head>
<body>
	<header>
		<img src="/img/bestBetsLogo.png" alt="Best Bets"  class="header-img" />
        <p><c:out value="${loggedInUser.username}"/></p>
    	<p>Balance: <c:out value="${loggedInUser.balance}"/></p>
		<nav>
			<ul class="nav_links">
				<li><a href="/games">Games</a></li>
				<li><a href="/bets/${loggedInUser.id}">My Bets</a></li>
                <li><a href="/logout" class="logout">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="body-container">
		<div class="table-container">
			<table class="table table border my-3">
				<c:forEach items="${myBets}" var="bet">
					<tr>
						<td><p><c:out value="${bet.teamPicked}"/></p></td>
						<td><p>Wager: <c:out value="${bet.wager}"/></p></td>
						<td><a href='/bet/edit/${bet.id}' class="bet">Change Bet</a></td>
						<td>
							<form action="/bet/delete/${bet.id}" method="post">
								<input type="hidden" name="_method" value="delete"/>
								<input type="submit" value="Delete" class="mx-2"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>