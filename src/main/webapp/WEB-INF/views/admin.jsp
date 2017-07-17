<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
    </head>
    <body>
        <h1>User list</h1>
        <table>
            <tr>
                <th>User ID</th>
                <th>User email</th>
                <th>User role</th>
                <th>User name</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.roleUser}</td>
                    <td>${user.username}</td>
                    <td><a href="<c:url value='/admin/edit/${user.id}' />" >Edit</a></td>
                    <td><a href="<c:url value='/remove/${user.id}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="<c:url value='/admin/add' />" >Add new user</a>
        <br>
        <a href="/database/logout">Logout</a>
    </body>
</html>
