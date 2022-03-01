<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Расходы по Вашим aвтомобилям </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        <%@ include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@ include file="../fragments/navigation.jsp" %>

<ul class="nav" style="background: rgb(20,0,0)">
    <li class="nav-item"  >
        <a class="nav-link active" aria-current="page" href="cabinet">Назад</a>
    </li>

</ul>

<div class="container" style="background: antiquewhite">
    <table class="table table-hover caption-top justify-content-center">
        <caption>Таблица расходов по Вашим автомобилям</caption>
        <thead>
        <tr>
            <th class="text-center" scope="col">№/Сортировка</th>
            <th class="text-center" scope="col">Дата</th>
            <th class="text-center" scope="col">Марка</th>
            <th class="text-center" scope="col">Модель</th>
            <th class="text-center" scope="col">Тип траты</th>
            <th class="text-center" scope="col">Стоимость</th>
            <th class="text-center" scope="col">Комментарий</th>
        </tr>
        </thead>
        <c:forEach var="expenses" items="${requestScope.listUserExpenses}" varStatus="st">
            <tr>
                <th class="text-center align-middle" scope="row">${st.index}</th>
                <td class="text-center align-middle">${expenses.date}</td>
                <td class="text-center align-middle">${expenses.car.brand}</td>
                <td class="text-center align-middle">${expenses.car.model}</td>
                <td class="text-center align-middle">${expenses.typeOfExpense}</td>
                <td class="text-center align-middle">${expenses.price}</td>
                <td class="text-center align-middle">${expenses.commentExp}</td>


            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>