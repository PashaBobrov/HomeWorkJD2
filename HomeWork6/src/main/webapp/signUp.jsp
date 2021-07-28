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
  <form action="signUp" method="POST">
  <p>
      Login: <input type="text" name="login" />
  </p>
  <p>
        Password: <input type="text" name="password" />
  <p>
  <p>
        FIO: <input type="text" name="fio" />
  <p>
  <p>
        Date of birth: <input type="date" name="dateOfBirth" />
   </p>
  <p>
        <input type="submit" value="Submit" />
  </p>
  </form>
 </body>
</html>