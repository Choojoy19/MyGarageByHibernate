<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div style="background: black" >
    <img src="../../images/main-logo.jpg" alt="logo"
         width="100px" height="75px">
     <a href="login" style="color: blue; font-size: small">На главную</a>
</div>
<div style= "width:100%; height:100%; background: black; top: 70px">
    <form action="add" method="post" style="width:28%; height:300px; position: absolute; left: 530px">
        <div class="mt-2">
            <label for="chooseLogIn" class="form-label" style= "color:orange; font-size: large">Логин</label>
            <input type="text" class="form-control" name="login" id="chooseLogIn" required>
            <div id="emailHelp" class="form-text" style="font-size: small">Используйте только латинские буквы и цифры</div>
        </div>
        <div class="mb-2">
            <label for="choosePassword" class="form-label" style= "color:orange; font-size: large">Пароль</label>
            <input type="password" class="form-control" name="password" id="choosePassword" required>
        </div>
        <div class="mb-2">
            <label for="confirmPassword" class="form-label" style= "color:orange; font-size: large">Повторите пароль</label>
            <input type="password" class="form-control" name="password1" id="confirmPassword" required>
        </div>
        <div class="mb-2">
            <label for="chooseName" class="form-label" style= "color:orange; font-size: large">Имя</label>
            <input type="text" class="form-control" name="name" id="chooseName">
        </div>
        <div class="mb-2">
            <label for="lastName" class="form-label" style= "color:orange; font-size: large">Фамилия</label>
            <input type="text" class="form-control" name="lastName" id="lastName">
        </div>
        <div class="mb-2">
            <label for="city" class="form-label" style= "color:orange; font-size: large">Ваш город</label>
            <input type="text" class="form-control" name="city" id="city">
        </div>
        <div class="mb-2">
            <label for="age" class="form-label" style= "color:orange; font-size: large">Дата рождения</label>
            <input type="text" class="form-control" name="age" id="age">
        </div>

        <button type="submit" class="btn btn-dark">Регистрация</button>
        <button type="reset" class="btn btn-dark">Очистить форму</button>
        </br>
        <c:if test="${requestScope.invalidLoginOrPassword == true}">
            <p class="text-start text-danger">Такой логин существует! Попробуйте еще раз</p>
        </c:if>
    </form>
</div>
</body>
</html>
