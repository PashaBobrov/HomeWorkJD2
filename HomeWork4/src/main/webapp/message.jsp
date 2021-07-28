<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<%!
    String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }
 %>
<html>
 <head>
   <title>!DOCTYPE</title>
   <meta charset="utf-8">
 </head>
 <body>
  <p>
        <%= getFormattedDate() %>
  </p>
  <form action="message" method="POST">
  <p>
      To user: <input type="text" name="user" />
  </p>
  <p>
        Text message: <input type="text" name="messageText" />
  <p>
  <p>
        <input type="submit" name = "send" value="send" />
  </p>
  </form>
 </body>
</html>