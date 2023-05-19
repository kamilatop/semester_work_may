<html>
<head>
    <title>Accounts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h1 class="mb-3">Задачи</h1>
    <div class="row mb-3">
    <div class="col-auto">
        <input id="taskText" type="text" class="form-control" placeholder="Новая задача">
    </div>
    <div class="col-auto">
        <button id="addTask" class="btn btn-primary">Добавить</button>
    </div>
    </div>
    <div id="tasks"></div>
</div>
<script src="js/tasks.js"></script>
</body>
</html>