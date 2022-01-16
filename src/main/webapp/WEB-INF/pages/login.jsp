<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Вход в профиль</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
<div class="ui-icon">
    <img src="https://gofrag.ru/images/100/my-garage-1.jpg" alt="logo"
         width="100%" height="20%">
</div>

<ul class="nav" style="background: rgb(20,0,0)">
    <li class="nav-item"  >
        <a class="nav-link active" aria-current="page" href="registration">Регистрация</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="listcar">Рейтинг автомобилей/Отзывы</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="info">Информация</a>
    </li>

</ul>

<div style= "width:100%; height:100%; background: black">
    <form action="loginAction" method="post" style="width:30%; height:300px; position: absolute; top:230px; left: 530px">
        <div class="mt-3">
            <label for="inputLogIn" class="form-label" style= "color:orange; font-size: large">Логин</label>
            <input type="text" class="form-control" name="login" id="inputLogIn" required>

        </div>
        <div class="mb-3">
            <label for="inputPassword" class="form-label" style= "color:orange; font-size: large">Пароль</label>
            <input type="password" class="form-control" name="password" id="inputPassword" required>
        </div>

        <button type="submit" class="btn btn-dark">Вход</button>
        <button type="reset" class="btn btn-dark">Очистить</button>
        </br>
        <c:if test="${requestScope.invalidLoginOrPassword == true}">
            <p class="text-start text-danger">Неверный логин или пароль! Попробуйте еще раз</p>
        </c:if>
    </form>
    <script>
        <%@ include file="../../JS/validation.js" %>
    </script>

</div>

</body>
</html>
