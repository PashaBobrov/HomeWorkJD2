<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<html>
 <head>
   <title>!DOCTYPE</title>
   <meta charset="utf-8">
 </head>
 <body>
  <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/airports';" value="Airports" /></p>
  <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/flights';" value="Flights" /></p>
 </body>
</html>