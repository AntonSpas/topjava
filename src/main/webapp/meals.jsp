<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Meals</title>
    <link href="/resources/mycss.css" rel="stylesheet">
</head>

<body>

<h3><a href="index.html">Home</a></h3>

<h2>Meals</h2>

<table class="tg" align="center" width="90%">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th width=10%></th>
        <th width=10%></th>
    </tr>

    <c:forEach items="${mealsList}" var="meal">
        <tr>
            <td align="center">${localDateTimeFormat.parse(meal.dateTime)}</td>
            <td align="center">${meal.description}</td>
            <td align="center">${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="meals?action=add">Add User</a></p>

</body>
</html>
