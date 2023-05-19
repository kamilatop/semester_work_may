<html>
<head>
    <title>Занятия</title>
</head>
<body>
<table id="classes_table">
    <tr>
        <th>Название</th>
        <th>Время проведения</th>
    </tr>
    <#list classes as class>
        <tr>
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
            <td>${class.name}</td>
            <td>${class.time}</td>
            <td></td>
        </tr>
    </#list>
</table>
<br>
<td><p class="lead">
        <a href="/profile">Назад</a></p></td>
</body>
</html>