<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Meals</title>
</head>

<body>

<h3><a href="index.html">Home</a></h3>

<h2>Meals</h2>

<table class="tg" align="center">
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th width=10%></th>
        <th width=10%></th>
    </tr>

    <c:forEach items="${mealsList}" var="meal">
        <tr>
            <td align="center">${book.id}</td>
            <c:url value="/book/${book.id}" var="showbook">
                <c:param name="page" value="${page+0}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${showbook}"/>" class="link3">${book.title}</a></td>
            <td>${book.author}</td>
            <td align="center">${book.isbn}</td>
            <td align="center">${book.printYear}</td>
            <c:url value="/read/${book.id}" var="read">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${stay}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${read}"/>"><img src="/resources/man-reading.png"></a></td>
            <td align="center"><c:if test="${book.readAlready}"><img src="/resources/draw-check-mark.png"></c:if></td>
            <c:url value="/update/${book.id}" var="update">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${stay}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${update}"/>"><img src="/resources/pencil-edit-button.png"></a></td>
            <c:url value="/delete/${book.id}" var="delete">
                <c:param name="page" value="${page+0}"/>
                <c:param name="stay" value="${stay}"/>
            </c:url>
            <td align="center"><a href="<c:out value="${delete}"/>"><img src="/resources/delete.png"></a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
