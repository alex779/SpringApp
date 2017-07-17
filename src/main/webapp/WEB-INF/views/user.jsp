
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile page</title>
    </head>
    <body>
        <h1>Hello ${user.username}!</h1>

        <br>
        <a href="<c:url value='user/edit/${user.id}' />" >Edit</a>
        <br>
        <a href="/database/logout">Logout</a>
    </body>

</html>
