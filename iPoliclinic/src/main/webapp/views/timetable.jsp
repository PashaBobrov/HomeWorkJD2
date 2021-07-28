<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Расписание</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    </head>
    <body>
        <script>
        function sendDelete(id){
           $.ajax({
               url: "${pageContext.request.contextPath}/timetable",
               method: "DELETE",
               contentType: "application/json",
               data: JSON.stringify({ id: id }),
               success: function(result) {
                       window.location.href = result.redirect;
               },
               error: function(request,msg,error) {
                        alert( msg);
               }
           });
           return true;
        }
        function sendDoSome(id,doSome){
                   $.ajax({
                       url: "${pageContext.request.contextPath}/timetable",
                       method: "PATCH",
                       contentType: "application/json",
                       data: JSON.stringify({ id: id, do : doSome }),
                       success: function(result) {
                               window.location.href = result.redirect;
                       },
                       error: function(request,msg,error) {
                                alert( msg);
                       }
                   });
                   return true;
                }

        </script>
        <div class="dropdown">
              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
              ${specialisation == null ? 'Specialisation' : specialisation.name}
             </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
             <c:forEach items="${requestScope.listSpecialisation}" var="record">
                <tr>
                    <li><a class="dropdown-item" value = ${record.id} href='${pageContext.request.contextPath}/timetable/sp/${record.id}'>${record.name}</a></li>
                </tr>
             </c:forEach>
             </ul>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Specialisation</th>
              <th scope="col">Doctor</th>
              <th scope="col">Date-time</th>
              <th scope="col">Patient</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${requestScope.listTimeTable}"
                                     var="record">
                              <tr>
                                  <td width="20%">${record.doctor.specialisation}  </td>
                                  <td width="20%">${record.doctor}  </td>
                                     <td width="20%">${record.datetime}
                                      <c:if test="${record.patient == null}">
                                         <p><input class="btn btn-primary" type="button" value="Book time" onclick = "sendDoSome(${record.id},'book')" /></p>
                                      </c:if>
                                     <c:if test="${!roleDoctor}">
                                     </c:if>
                                      <c:if test="${record.patient != null}">
                                          <p><input class="btn btn-primary" type="button" value="Empty" onclick = "sendDoSome(${record.id},'empty')" /></p>
                                      </c:if>
                                    </td>
                                  <td width="20%">${record.patient}</td>
                                  <td>
                                     <c:if test="${roleDoctor}">
                                         <p><input class="btn btn-primary" type="button" value="Delete" onclick = "sendDelete(${record.id})" /></p>
                                     </c:if>
                                  </td>

                              </tr>
                          </c:forEach>
          </tbody>
        </table>
         <c:if test="${roleDoctor}">
             <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/timetableCard';" value="Add" /></p>
         </c:if>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="index" /></p>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    </body>
</html>