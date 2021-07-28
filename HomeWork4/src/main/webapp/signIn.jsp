<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<html>
 <head>
   <title>!DOCTYPE</title>
   <meta charset="utf-8">
 </head>
 <body>
  <form action="signIn" method="POST">
  <p>
      Login: <input type="text" name="login" />
  </p>
  <p>
        Password: <input type="text" name="password" />
  <p>
  <p>
        <input type="submit" name = "loginInto" value="Log into account" />
  </p>
  <p>
          <input type="submit" name = "registration" value="Registration" />
    </p>
  </form>
 </body>
</html>