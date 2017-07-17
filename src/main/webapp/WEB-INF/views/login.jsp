<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <div>

            <form method="POST" action="/database/login">
                <h2>Log in</h2>

                <div class="${error != null ? 'has-error' : ''}">

                    <br>
                    <input name="email" type="text" placeholder="Email"
                           autofocus="true"/>
                    <br>
                    <input name="password" type="password"  placeholder="Password"/>
                    <span>${error}</span>
                    <br>
                    <button type="submit">Log In</button>

                    <h4 ><a href="/database/registration">Create an account</a></h4>
                    <br>
                    <span>${message}</span>

                </div>

            </form>

        </div>
    </body>
</html>