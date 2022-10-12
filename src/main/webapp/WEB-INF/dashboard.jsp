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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/dashboard.css"/>
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
	<div class="marquee-container">
		<div class="marquee-row">
			<div class="marquee-img">
				<img src="/img/football_stadium.jpg" alt="1" />
                <div class="centered">
                    <h4>BET ON YOUR FAVORITE TEAM</h4>
                    <h5>You won't win real money otherwise I'd go broke.</h5>
                </div>
			</div>
			<div class="marquee-img">
				<img src="/img/gambler.jpg" alt="1" />
                <div class="centered">
                    <h4>EASY TO WIN!!!</h4>
                    <h5>No spread, just pick the winner. Like I said, no real money. Just meaningless points.</h5>
                </div>
			</div>
			<div class="marquee-img">
				<img src="/img/sauce.jpg" alt="1" />
                <div class="centered">
                    <h4>BRING HOME THE SACK!</h4>
                    <h5>Bet now</h5>
                </div>
			</div>
		</div>
	</div>
    <div class="body-container">
        <h3 class="scoreboard">Scoreboard</h3>
        <div class="grid-container">
            <c:forEach items="${jResults}" var="game">
            <div class="grid-item">
                <p class="score"><c:out value='${game.getJSONObject("teams").getJSONObject("away").getString("team")}'/> <c:out value='${game.getJSONObject("scoreboard").getJSONObject("score").getInt("away")}'/><p/>
                <p><c:out value='${game.getJSONObject("teams").getJSONObject("home").getString("team")}'/> <c:out value='${game.getJSONObject("scoreboard").getJSONObject("score").getInt("home")}'/><p/> 
            </div>  
            </c:forEach>
        </div>
    </div>
</body>
</html>
	
    
    
    