<html>
<head>
    <title>Посты</title>
</head>
<body>
<table id="posts_table">
    <tr>
        <th>Заголовок</th>
        <th>Текст</th>
        <th>Дата</th>
        <th>Автор</th>
    </tr>
    <#list posts as post>
        <tr>
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
            <td>${post.title}</td>
            <td>${post.text}</td>
            <td>${post.createdAt}</td>
            <td>${post.accountEmail}</td>
            <td></td>
        </tr>
    </#list>
</table>
<td><p class="lead"><a href="/posts/search">Поиск</a></p></td>
</body>
</html>