<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit page</title>
    </head>
    <body>

        <form:form method="POST" modelAttribute="userForm">
            <h2>Create your account</h2>
            <form:input type="text" path="username" placeholder="Username" autofocus="true"></form:input>
            <span>${error}</span>
            <br>
            <p>Role:</p>
            <form:radiobutton path="roleUser" value="ROLE_USER" checked="checked"/>User
            <form:radiobutton path="roleUser" value="ROLE_ADMIN"/>Admin
            <br>
            <button type="submit">Submit</button>

        </form:form>
        <a href="/database/admin">Back</a>
    </body>
</html>