<!doctype html>
<html lang="en">
<meta charset="UTF-8">
<head>
    <title>Личная страница</title>
    <link rel="stylesheet" href="css/profile.css">
    <style>
        .avatar {
            vertical-align: middle;
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
    </style>
    <#include "head.ftlh"/>
</head>
<br>
<#if account.role == 'ADMIN'>
    <div class="six">
        <a href="/accounts">Перейти в настройки - Администратор</a>
    </div>
    <div class="six">
        <a href="/tasks">Задачи</a>
    </div>
</#if>
<br>
<div class="six">
    <a href="/posts">Все посты</a>
    <br>
    <br>
    <a href="/classes">Занятия</a>
    <br>
    <br>
    <a href="profile/update">Изменить данные</a>
    <br>
    <br>
    <a href="/logout">Выход</a>
</div>
<br>
<body>
<div class="center">
<br>
<td><p class="lead">Добро пожаловать, ${account.firstName} ${account.lastName}!</p></td>
<br>
<td><p class="lead">Ваш факультет: ${account.faculty_name}</p></td>
<br>
<#if account.avatar ?? >
    <img src="http://localhost:8081/profile/files/${account.avatar}" alt="image" class="avatar">
</#if>
<form action="/profile/${account.id}/post" method="post">

<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
<br>
    <td><p class="lead">Добавить пост</p></td>
    <br>
    <input type="text" name="title" placeholder=" Название">
    <input type="text" name="text" placeholder=" Текст">
    <input type="submit" value="Добавить">
</form>
<br>
<td><p class="lead">Загрузить аватар</p></td>
<br>
    <form action="/profile/files/${account.id}/upload" method="post" enctype="multipart/form-data">
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    <input type="file" name="file">
    <input type="submit" value="Загрузить">
</form>
</div>
</body>
</html>
