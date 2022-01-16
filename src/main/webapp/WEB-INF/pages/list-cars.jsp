<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Автомобили</title>
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
        <caption>Таблица автомобилей</caption>
        <thead>
        <tr>
            <th class="text-center" scope="col">#</th>
            <th class="text-center" scope="col">Марка</th>
            <th class="text-center" scope="col">Модель</th>
            <th class="text-center" scope="col">Год выпуска</th>
            <th class="text-center" scope="col">Тип кузова</th>
            <th class="text-center" scope="col">Тип двигателя</th>
            <th class="text-center" scope="col">Объем двигателя</th>
            <th class="text-center" scope="col">Цвет</th>
            <th class="text-center" scope="col">Оценка</th>
            <th class="text-center" scope="col">Комментарий</th>

        </tr>
        </thead>
        <c:forEach var="car" items="${requestScope.listCars}" varStatus="st">
            <tr>
                <th class="text-center align-middle" scope="row">${st.index}</th>
                <td class="text-center align-middle">${car.brand}</td>
                <td class="text-center align-middle">${car.model}</td>
                <td class="text-center align-middle">${car.yearOfManufacture}</td>
                <td class="text-center align-middle">${car.bodyType}</td>
                <td class="text-center align-middle">${car.engineType}</td>
                <td class="text-center align-middle">${car.engineVolume}</td>
                <td class="text-center align-middle">${car.color}</td>
                <td class="text-center align-middle">${car.mark}</td>
                <td class="text-center align-middle">${car.comment}</td>


            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>