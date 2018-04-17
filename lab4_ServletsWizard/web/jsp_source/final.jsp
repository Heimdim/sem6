<%@ page import="entities.Crusade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css_source/default.css" type="text/css" />
</head>
<body>
    <form name="finalForm" action=${pageContext.request.contextPath}/thirdStep >
        <p style="background: url(/images/bulla.jpg); -webkit-background-size:100%;color: darkred">
            <b>Папская Булла:</b><br>
            <b >
                Крестовый Поход:<br>
                Имя Папы Римского: ${sessionScope.crusade.getPopeName()}<br>
                Причина похода: ${sessionScope.crusade.getGoal()}<br>
                Отпущены ли грехи: ${sessionScope.crusade.isHaveIndulgence()}<br>
                AVE MARIA: ${sessionScope.crusade.isAve()}<br>
                DEUS VULT: ${sessionScope.crusade.isDeus()}<br>
            </b>
        </p>
        <p>
            <input class="btn2"  type="submit" name="button4" value="back"></button>
        </p>
    </form>
</body>
</html>
