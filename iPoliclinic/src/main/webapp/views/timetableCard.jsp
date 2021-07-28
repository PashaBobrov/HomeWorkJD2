<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <div class="container">

        <form class="form-group" method="post" action="${pageContext.request.contextPath}/timetableCard">
            <div class="row">
                <div class="col">
                    <h1>Doctor</h1>
                </div>
                <div class="col">
                    <h1>${doctor.fio}</h1>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h1>Specialisation</h1>
                </div>
                <div class="col">
                    <h1>${doctor.specialisation}</h1>
                </div>
            </div>
            <div class="row">
                <div class="col">
                   <h1>Date-time</h1>
                </div>
                <div class="col">
                    <input type="datetime-local" id="dateTime" name="dateTime" class="form-control" required>
                </div>
            </div>
            <div class="row">
                 <div class="col">

                 </div>
                 <div class="col">
                     <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
                 </div>
            </div>
            <input type="hidden" name = "doctorId" value = ${doctor.id}>
        </form>
        </div>
        <footer>
            <button class="btn btn-lg btn-primary btn-block" onclick="location.href='${pageContext.request.contextPath}/timetable';" type="submit">Back</button>
        </footer>
        <script src="https://cdnjsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    </body>
</html>
