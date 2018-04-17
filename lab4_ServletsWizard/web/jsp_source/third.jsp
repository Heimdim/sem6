<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css_source/default.css" type="text/css" />
</head>
<body>
    <form name="checkForm" action=${pageContext.request.contextPath}/thirdStep method="get">
        <p><b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> Выберите необходимые параметры Крестового Похода</b><br>
            <b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> Отпустить грехи</b><br>
        <input type="checkbox" <c:if test="${sessionScope.crusade.isHaveIndulgence() == 'true'}">checked</c:if>
               name="haveIndulgency"><br>
            <b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> AVE MARIA</b><br>
        <input type="checkbox" <c:if test="${sessionScope.crusade.isAve() == 'true'}">checked</c:if>
               name="isAve"><br>
            <b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> DEUS VULT</b><br>
        <input type="checkbox" <c:if test="${sessionScope.crusade.isDeus() == 'true'}">checked</c:if>
               name="isDeus"><br>
        </p>
        <p>
            <input class="btn2"  type="submit" name="button3"   value="back">
            <input class="btn2"  type="submit" name="button3"   value="next">
        </p>
    </form>
</body>
</html>
