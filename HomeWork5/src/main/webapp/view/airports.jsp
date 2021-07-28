<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>!DOCTYPE</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<main>

<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
            <td>Code</td>
            <td>Name</td>
            <td>City</td>
            <td>Timezone</td>
            <td>Coordinates</td>
       </tr>

       <c:forEach items="${listAirports}" var="item">
          <tr>
            <td>${item.airportCode}</td>
            <td>${item.airportName}</td>
            <td>${item.city}</td>
            <td>${item.timezone}</td>
            <td>${item.coordinates}</td>
          </tr>
       </c:forEach>
    </table>
    </main>

    <footer>
    <hr>
    <p align="center">
        <small>
        <time>04-2021</time> © Bobrov Pavel
       </small>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/flights';" value="Flights" /></p>
    </p>
    </footer>

</body>
</html>