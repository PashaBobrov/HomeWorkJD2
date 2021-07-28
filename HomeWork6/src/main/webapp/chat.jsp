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
          <th>User from</th>
          <th>Date</th>
          <th>Message</th>
       </tr>
       <c:forEach items="${listMessage}" var="message">
          <tr>
             <td>${message.userFrom}</td>
             <td>${message.date}</td>
             <td>${message.text}</td>
          </tr>
       </c:forEach>
    </table>
    </main>

    <footer>
    <hr>
    <p align="center">
        <small>
        <time>03-2021</time> © Bobrov Pavel
       </small>
       <form action="selectPage" method="GET">
         <input type="submit" name = "return" value="Return" />
        </form>
    </p>
    </footer>

</body>
</html>