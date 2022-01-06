<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        <%@ include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@ include file="../fragments/navigation.jsp" %>
<div class="container">
    <h2 class="text-center">Register Form:</h2>
    <div class="row justify-content-center">
        <form class="col-6" action="update" method="post">
            <input type="hidden" name="id" value="${requestScope.existedUser.id}">
            <div class="mb-3">
                <label for="inputName" class="form-label">Имя</label>
                <input type="text" class="form-control" name="name" value="${requestScope.existedUser.name}" id="inputName"
                       required>
            </div>
            <div class="mb-3">
                <label for="inputLastName" class="form-label">Фамилия</label>
                <input type="text" class="form-control" name="lastName" value="${requestScope.existedUser.lastName}"
                       id="inputLastName" required>
            </div>
            <div class="mb-3">
                <label for="inputAddress" class="form-label">Город</label>
                <input type="text" class="form-control" name="city" value="${requestScope.existedUser.city}"
                       id="inputAddress" required>
            </div>
            <div class="mb-3">
                <label for="inputLogin" class="form-label">Логин</label>
                <input type="text" class="form-control" name="login" value="${requestScope.existedUser.login}" id="inputLogin"
                       required>
            </div>
            <div class="mb-3">
                <label for="inputPassword" class="form-label">Пароль</label>
                <input type="password" class="form-control" name="password" value="${requestScope.existedUser.password}"
                       id="inputPassword" required>
            </div>
            <div class="mb-3">
                <label for="inputAge" class="form-label">Возраст</label>
                <input type="text" class="form-control" name="age" value="${requestScope.existedUser.age}"
                       id="inputAge" required>

            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <button type="reset" class="btn btn-secondary">Очистить форму</button>
        </form>
    </div>
</div>
</body>
</html>
