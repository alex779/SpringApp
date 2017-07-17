<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account registration</title>
    </head>
    <body>
        <h1>Create new account</h1>

        <form:form method="POST" modelAttribute="userForm">

            <h2>Account registration</h2>

            <spring:bind path="username">
                <form:input type="text" path="username" placeholder="Username" autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </spring:bind>
            <br>

            <spring:bind path="email">
                <form:input type="text" path="email" placeholder="email"></form:input>
                <form:errors path="email"></form:errors>
            </spring:bind>
            <br>

            <p>Role:</p>
            <form:radiobutton path="roleUser" value="ROLE_USER" checked="checked"/>User
            <form:radiobutton path="roleUser" value="ROLE_ADMIN"/>Admin
            <br>

            <spring:bind path="password">
                <form:input type="password" path="password" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </spring:bind>
            <br>

            <spring:bind path="passConf">
                <form:input type="password" path="passConf" placeholder="Confirm your password"></form:input>
                <form:errors path="passConf"></form:errors>
            </spring:bind>
            <br>

            <button type="submit">Submit</button>

        </form:form>

        <br>
        <a href="/database/admin">Back</a>
    </body>
</html>