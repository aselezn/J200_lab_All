<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/login_form.css">
</head>
<body>
<div class="form">

    <h1>Login</h1>

    <h3 class="error-message">
        ${requestScope.errorReason}
    </h3>

    <form action="auth" method="post">
        <div class="input-form">
            <input type="text" name="username" placeholder="Username">
        </div>
        <div class="input-form">
            <input type="password" name="password" placeholder="Password">
        </div>
        <div class="input-form">
            <button type="submit">Login</button>
        </div>
    </form>

</div>
</body>
</html>