<html>
<meta charset="UTF-8">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница</title>
    <link rel="stylesheet" href="css/index.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
        <div class="container">
            <div class="navbar">
                <nav>
                    <ul>
                        <li><a href="/profile">Личный кабинет</a> </li>
                        <li><a href="/signIn">Вход</a></li>
                        <li><a href="/signUp">Регистрация</a></li>
                        <li><a href="">Контакты</a></li>
                    </ul>
                </nav>
                <img src="images/menu.png" class="menu-icon">
            </div>
            <div class="row">
                <div class="col">
                    <h1>Институт имени Камилы</h1>
                    <p></p>
                </div>
                <div class="col">
                    <a href="">
                        <div class="card card1">
                            <h5>Мероприятия</h5>
                            <p></p>
                        </div>
                    </a>
                    <a href="">
                        <div class="card card2">
                            <h5>Институты</h5>
                            <p></p>
                        </div>
                    </a>
                    <a href="">
                        <div class="card card3">
                            <h5>Абитуриент</h5>
                            <p></p>
                        </div>
                    </a>
                    <div class="card card4">
                        <h5>История университета</h5>
                        <p></p>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
</html>
