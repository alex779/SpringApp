<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit page</title>
    </head>
    <body>
        <h1>Edit profile</h1>
        <form:form method="POST" modelAttribute="userForm">

            <h2>Create your account</h2>
            <form:input type="text" path="email" placeholder="email"></form:input>
            <span>${mailErr}</span>
            <br>
            <form:input type="password" path="password" placeholder="Password"></form:input>
            <span>${passErr}</span>
            <br>
            <form:input type="password" path="passConf" placeholder="Confirm your password"></form:input>
            <span>${passConfErr}</span>
            <br>
            <br>
            <button type="submit">Submit</button>

        </form:form>
        <a href="/database/user">Back</a>
    </body>
</html>
