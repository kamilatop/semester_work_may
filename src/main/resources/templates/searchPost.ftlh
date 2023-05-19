<html>
<head>
    <title>Посты</title>
</head>
<body>
<script>
    function showResult(title) {
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange=function() {
            if (this.readyState===4 && this.status===200) {
                let html =
                                '<tr>' +
                                '<th>ID</th>' +
                                '<th>Название</th>' +
                                '<th>Содержание</th>' +
                                '<th>Дата</th>' +
                                '</tr>'

                            let response = JSON.parse(this.response);

                            for (let i = 0; i < response.length; i++) {
                                html += '<tr>';
                                html += '<td>' + response[i]['id'] + '</td>';
                                html += '<td>' + response[i]['title'] + '</td>';
                                html += '<td>' + response[i]['text'] + '</td>';
                                html += '<td>' + response[i]['createdAt'] + '</td>';
                                html += '</tr>';
                            }
                document.getElementById('posts_table').innerHTML = html;
            }
        }
        xmlhttp.open("GET","/posts/search/" + title, true);
        xmlhttp.send();
    }
</script>
<h1>Поиск постов</h1>
<h3>Учитывать регистр</h3>
<form>
    <p>
        Поиск по названию:<br>
        <input name="title" type="text" onkeyup="showResult(this.value)"/><br>
    </p>
</form>
<table id="posts_table">
</table>
</body>
</html>
