<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Search Results</title>

<style>
body{
margin: 0 auto;
}

#container{
background: #dcddd4; /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover,  #fcfff4 0%, #dfe5d7 40%, #b3bead 100%); /* FF3.6+ */
background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%,#fcfff4), color-stop(40%,#dfe5d7), color-stop(100%,#b3bead)); /* Chrome,Safari4+ */
background: -webkit-radial-gradient(center, ellipse cover,  #fcfff4 0%,#dfe5d7 40%,#b3bead 100%); /* Chrome10+,Safari5.1+ */
background: -o-radial-gradient(center, ellipse cover,  #fcfff4 0%,#dfe5d7 40%,#b3bead 100%); /* Opera 12+ */
background: -ms-radial-gradient(center, ellipse cover,  #fcfff4 0%,#dfe5d7 40%,#b3bead 100%); /* IE10+ */
background: radial-gradient(ellipse at center,  #fcfff4 0%,#dfe5d7 40%,#b3bead 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fcfff4', endColorstr='#b3bead',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */

margin: 0 auto;
padding-left:50px;
border:solid 1px #eaeaea;
width:1300px;

box-shadow:2px 2px 4px rgba(0,0,0,0.4);
}

ul li{
list-style:square;
}
li #url{
color:blue;
font-size:24px;
font-family:times new roman;
}

#url a:hover{
color:black;
}

#url a{
text-decoration:none;
}

#title{
font-size:19px;
font-style:italic;
color:#daeeeee;
}

#desc{
font-size:18px;
color:#333;
}


li{
color:black;
}
ul li a{
font-weight:bold;
list-style:none;
color:33CCFF;
text-shadow:1px 1px 2px rgba(0,0,0,0.3);
}



</style>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body class="w3-container">

<div class="w3-container w3-pale-red w3-leftbar w3-rightbar w3-border-red">
    <ul>
        <c:if test="${not empty finalList}">
        
        <c:forEach items="${finalList}" var="res">
							  <li>
								 <p id="url"><a href="${res.key}">${res.key}</a> </p>
								 <p id="title"> ${res.value}</p>
							</li>
						</c:forEach>
        
       <%-- <c:forEach items="${finalList}" var ="result"> 
            <li>
                <p id="url"><a href="${result}">${result} </a> </p>
               
                <p id="title"> ${result.title} </p>
                
                <p id="desc"> ${result.description} </p>
                <br />           
            </li>
       
       </c:forEach> --%>
        </c:if>
        
        <c:if test="${empty finalList}">
        
            <p> No result found for given word ! </p>
        </c:if>
    </ul>
    </div>
</body>
</html>