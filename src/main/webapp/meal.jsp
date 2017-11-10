<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Meal</title>
</head>

<body>

<h3><a href="index.html">Home</a></h3>

<h2>Meal</h2>

<form method="POST" action='meals'>
    Дата/Время : <input type="text" name="dateTime"
                        value="<c:out value="${localDateTimeFormat.parse(meal.dateTime)}" />" /> <br />
    Описание : <input type="text" name="description"
                      value="<c:out value="${meal.description}" />" /> <br />
    Калории : <input type="text" name="calories"
                     value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>

</body>
</html>
