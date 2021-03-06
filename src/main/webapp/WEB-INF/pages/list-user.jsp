<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Управление данными пользователей</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        <%@ include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@ include file="../fragments/navigation.jsp" %>
<div class="container">
    <table class="table table-hover caption-top justify-content-center">
        <caption>Таблица пользователей</caption>
        <thead>
        <tr>
            <th class="text-center" scope="col">#</th>
            <th class="text-center" scope="col">ID</th>
            <th class="text-center" scope="col">Имя</th>
            <th class="text-center" scope="col">Фамилия</th>
            <th class="text-center" scope="col">Город</th>
            <th class="text-center" scope="col">Возраст</th>
            <th class="text-center" scope="col">Логин</th>
            <th class="text-center" scope="col">Пароль</th>
            <th class="text-center" scope="col">Действие</th>
        </tr>
        </thead>
        <c:forEach var="user" items="${requestScope.listUser}" varStatus="st">
            <tr>
                <th class="text-center align-middle" scope="row">${st.index}</th>
                <td class="text-center align-middle">${user.id}</td>
                <td class="text-center align-middle">${user.name}</td>
                <td class="text-center align-middle">${user.lastName}</td>
                <td class="text-center align-middle">${user.city}</td>
                <td class="text-center align-middle">${user.age}</td>
                <td class="text-center align-middle">${user.login}</td>
                <td class="text-center align-middle">${user.password}</td>
               <td class="text-center align-middle">
                   <div class="btn-group" role="group" aria-label="Basic example">
                       <a href="edit?id=${user.id}" class="btn btn-primary" role="button">Редактировать</a>
                       <a href="delete?id=${user.id}" class="btn btn-secondary" role="button">Удалить</a>
                   </div>
               </td>
       </tr>
   </c:forEach>
</table>
</div>
</body>
</html>