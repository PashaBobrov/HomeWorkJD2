<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   <head>
      <title>!DOCTYPE</title>
      <meta charset="utf-8">
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
         $( function() {
           $( "#datepicker1" ).datepicker();
           $( "#datepicker2" ).datepicker();
         } );
      </script>
      <script>
         function setPage(pageLink)
         {
             document.getElementById("page").value = pageLink;
             document.getElementById("my_post").submit();
             return false;
         }
      </script>
   </head>
   <body>
      <c:set var="pageVal" value="${currentPage}"/>
      <form action="${pageContext.request.contextPath}/flights" id="my_post" method="POST">
         <input type="hidden" id="page" name="page" value="${pageVal}" />
         <c:set var="airport_code" value="airport_code"/>
         <td>Airport departure</td>
         <select name ="airportDeparture">
            <c:forEach var="item" items="${listAirports}">
               <option value=${item[airport_code]}
               <c:if test="${item[airport_code] == airportDeparture}"> selected </c:if>
               >${item["city"]} - ${item[airport_code]}</option>
            </c:forEach>
         </select>
         <td>Date departure: <input type="text" id="datepicker1" name="dateDeparture"</td>
         <td>Airport arrival</td>
         <select name="airportArrival">
            <c:forEach var="item" items="${listAirports}">
               <option value=${item["airport_code"]}
               <c:if test="${item[airport_code] == airportArrival}"> selected </c:if>
               >${item["city"]} - ${item["airport_code"]}</option>
            </c:forEach>
         </select>
         <td>Date arrival: <input type="text" id="datepicker2" name="dateArrival"></td>
         <p><input type="submit" value="Search" />
         <td><input type="button" onclick="location.href='${pageContext.request.contextPath}/airports';" value="Airports" /></td>
         </p>
      </form>
      <main>
         <c:if test="${countPages > 0}">
            <table border="1" cellpadding="5" cellspacing="1" >
               <tr>
                  <c:forEach items="${listFlights[0]}" var="entry">
                     <th>${entry.key}</th>
                  </c:forEach>
               </tr>
               <c:forEach items="${listFlights}" var="item">
                  <tr>
                     <c:forEach items="${item}" var="entry">
                        <td>${entry.value}</td>
                     </c:forEach>
                  </tr>
               </c:forEach>
            </table>
         </c:if>
      </main>
      <footer>
         <hr>
         <c:if test="${countPages > 0}">
            <table align="center" border="1" cellpadding="5" cellspacing="5">
               <c:if test="${currentPage > 1}">
                  <td><a href="#" onClick="setPage(${pageVal - 1});">Prev</a></td>
               </c:if>
                  <c:forEach begin="1" end="${countPages}" var="i">
                     <c:choose>
                        <c:when test="${currentPage eq i}">
               <td>${i}</td>
               </c:when>
               <c:otherwise>
               <td><a value=${i} href="#" onClick="setPage(${i});">${i}</a></td>
               </c:otherwise>
               </c:choose>
               </c:forEach>
               </td>
               <c:if test="${currentPage lt countPages}">
                  <td><a href="#" onClick="setPage(${pageVal + 1});">Next</a></td>
               </c:if>
            </table>
         </c:if>
         </p>
         <small>
            <p align="center">
               <time>04-2021</time> © Bobrov Pavel
            </p>
         </small>
      </footer>
   </body>
</html>