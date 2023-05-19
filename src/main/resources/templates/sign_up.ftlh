<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'signUp.page.title'/></title>
    <link rel="stylesheet" href="css/signUp.css">
    <#include "head.ftlh"/>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="center">
<h1><@spring.message 'signUp.page.header'/></h1>
        <form action="/signUp" method="post">
            <@spring.bind path="signUpForm"/>
            <p class="error">${spring.status.errorMessage}</p>

<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

            <div class="txt_field">
            <#assign i18Tooltip><@spring.message code="signUp.form.placeholder.firstName"/></#assign>
            <@spring.formInput "signUpForm.firstName" "placeholder=${i18Tooltip}"/>
            </div>
            <@spring.showErrors "<br>" "error"/>

            <div class="txt_field">
            <#assign i18Tooltip><@spring.message code="signUp.form.placeholder.lastName"/></#assign>
            <@spring.formInput "signUpForm.lastName" "placeholder=${i18Tooltip}"/>
            </div>
            <@spring.showErrors "<br>" "error"/>

            <div class="txt_field">
            <#assign i18Tooltip><@spring.message code="signUp.form.placeholder.email"/></#assign>
            <@spring.formInput "signUpForm.email" "placeholder=${i18Tooltip}"/>
            </div>
            <@spring.showErrors "<br>" "error"/>

            <div class="txt_field">
            <#assign i18Tooltip><@spring.message code="signUp.form.placeholder.password"/></#assign>
            <@spring.formPasswordInput "signUpForm.password" "placeholder=${i18Tooltip}"/>
            <br>
            </div>
            <@spring.showErrors "<br>" "error"/>

            <div class="txt_field">
            <#assign i18Tooltip><@spring.message code="signUp.form.placeholder.faculty_name"/></#assign>
            <@spring.formInput "signUpForm.faculty_name" "placeholder=${i18Tooltip}"/>
            </div>
            <@spring.showErrors "<br>" "error"/>

            <input type="submit" value="Зарегистрироваться" class="submit">
        </form>
</div>
</body>
</html>
