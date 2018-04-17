
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css_source/default.css" type="text/css" />
</head>
<body>
    <form class="form1" name="popeNameForm" action=/firstStep method="GET">
        <p><b style="background: url(/images/blood.png); -webkit-background-size:100%;color: #a08d00 "> Укажите ваше имя, Папа:</b><br>
       <input type="text"  name="popeNameField" value=${sessionScope.crusade.popeName}>
        </p>
        <p><br>
            <input class="btn2"  type="submit" name="button1"  value="back">
            <input class="btn2" type="submit" name="button1"  value="next">
        </p>
    </form>
</body>
</html>
