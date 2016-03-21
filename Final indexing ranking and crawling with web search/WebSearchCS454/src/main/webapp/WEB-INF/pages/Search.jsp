<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/shift.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/bootstrap.css">
<link rel="stylesheet" href="src/main/webapp/WEB-INF/pages/Main.css" type="text/css">
<title>Web Search</title>
<style>
.nav a {
    color: #5a5a5a;
    font-size: 11px;
    font-weight: bold;
    padding: 14px 10px;
    text-transform: uppercase;
}

.nav li {
    display: inline;
}

.jumbotron {
    background-image: url('home.jpg');
    background-repeat: no-repeat;
    height: 600px;
    background-color:#8c6a9a;
    webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}

.jumbotron .container {
    position: relative;
    top: 60px;
}

#s1 {
    border: solid 1px #fff;
    background-color: grey;
}

#search {
    color: #000000;
}

.jumbotron h1 {
    color: #fff;
    font-size: 48px;
    font-family: 'Shift', sans-serif;
    font-weight: bold;
}

.jumbotron p {
    font-size: 20px;
    color: #fff;
}

.learn-more {
    background-color: #f7f7f7;
}

.learn-more h3 {
    font-family: 'Shift', sans-serif;
    font-size: 18px;
    font-weight: bold;
}

.learn-more a {
    color: #00b0ff;
}

.neighborhood-guides {
    background-color: #efefef;
    border-bottom: 1px solid #dbdbdb;
}

.neighborhood-guides h2 {
    color: #393c3d;
    font-size: 24px;
}

body
    {
        background:url(/pages/home.jpg) no-repeat center center fixed;
        background-size: cover;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        margin: 0;
        padding: 0;
    }

.neighborhood-guides p {
    font-size: 15px;
    margin-bottom: 13px;

</style>
</head>

<body>
	<div class="nav">
		<div class="container">
			<ul class="pull-left" style="width: 453px; ">
				<li><a href="#">Images</a></li>
				<li><a href="#">Videos</a></li>
				<li><a href="#">Map</a></li>
				<li><a href="#">News</a></li>
				<li><a href="#">Sign in</a></li>
			</ul>
		</div>
	</div>

	<div class="jumbotron" style="background-image:url('home.jpg')">
		<div class="container">
			<h1>Search..</h1>

			<form action="search.html" method="post" style="text-align: left"
				margin-top=100px font color="red">
				<p>
					<input id="search" name="word" type="text" align="center"
						class="forminput" size="20" /> <input name="search" type="submit"
						class="subbutton" id="s1" value="GO" />
				</p>
			</form>

		</div>
	</div>

	<div class="learn-more">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
						<a href="#">Privacy & Cookies</a><br>
						<a href="#">Legal</a><br>
						<a href="#">Help</a><br>
						<a href="#">Feedback</a><br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>