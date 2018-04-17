<%@ page import="entities.Crusade" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css_source/default.css" type="text/css" />
</head>
<body>
    <form name="goalsForm" action=${pageContext.request.contextPath}/secondStep method="get">
        <p><b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> Выберите причину Крестового Похода</b><br>
            <select name="goal">
                <option name="1" <c:if test="${sessionScope.crusade.goal == 'Еретики совсем обнаглели'}">selected</c:if>
                                value="Еретики совсем обнаглели"> Еретики совсем обнаглели</option>
                <option name="2" <c:if test="${sessionScope.crusade.goal == 'Нам срочно нужно захватить Иерусалим'}">selected</c:if>
                        value="Нам срочно нужно захватить Иерусалим">Нам срочно нужно захватить Иерусалим</option>
                <option name="3" <c:if test="${sessionScope.crusade.goal == 'Наша казна пустеет, милорд'}">selected</c:if>
                        value="Наша казна пустеет, милорд"> Наша казна пустеет, милорд</option>
            </select><br><br>
        <p>
            <input class="btn2"  type="submit" name="button2"  value="back">
            <input class="btn2"  type="submit" name="button2"  value="next">
        </p>
    </form>
</body>
</html>
