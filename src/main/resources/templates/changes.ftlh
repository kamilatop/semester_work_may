<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        .errors {
            color: red;
        }
    </style>
</head>
<body>
<form action="/profile/update" method="post">
    <@spring.bind path="accountDto"/>
    <p class="errors">${spring.status.errorMessage}</p>

<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

    <label for="firstName"><@spring.message 'account.form.label.enterFirstName'/></label>
    <#assign i18Tooltip><@spring.message code="account.form.placeholder.firstName"/></#assign>
    <@spring.formInput "accountDto.firstName" "placeholder=${i18Tooltip}"/>
    <@spring.showErrors "<br>" "errors"/>
    <br>
    <label for="lastName"><@spring.message 'account.form.label.enterLastName'/></label>
    <#assign i18Tooltip><@spring.message code="account.form.placeholder.lastName"/></#assign>
    <@spring.formInput "accountDto.lastName" "placeholder=${i18Tooltip}"/>
    <@spring.showErrors "<br>" "errors"/>
    <br>
    <label for="password"><@spring.message 'account.form.label.enterPassword'/></label>
    <#assign i18Tooltip><@spring.message code="account.form.placeholder.password"/></#assign>
    <@spring.formPasswordInput "accountDto.password" "placeholder=${i18Tooltip}"/>
    <@spring.showErrors "<br>" "errors"/>
    <br>
    <input type="submit" value="Сохранить" class="submit">
</form>
</body>
</html>
