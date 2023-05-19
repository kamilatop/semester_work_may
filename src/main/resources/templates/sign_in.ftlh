<!doctype html>
<html lang="en">
<head>
    <title>Sign In</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/signIn.css">
    <#include "head.ftlh"/>
</head>
<body>

<div class="center">
<h1>Вход</h1>
<form action="/signIn" method="post">

<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

    <div class="txt_field">
    <input type="email" name="email" placeholder="Почта">
    </div>

    <div class="txt_field">
    <input type="password" name="password" placeholder="Пароль">
    </div>

    <div class="signup_link">Запомнить меня?
        <input type="checkbox" name="rememberMe">
    </div>

    <input type="submit" value="войти">
</form>
</div>
</body>
</html>
