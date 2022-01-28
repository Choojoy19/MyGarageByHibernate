<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit car ${car.brand} ${car.model}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        <%@ include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@ include file="../fragments/navigation.jsp" %>
<div class="container">
    <h2 class="text-center">Отредактируйте нужные поля:</h2>
    <div class="row justify-content-center">
        <form class="col-6" action="updatecar" method="post">
            <input type="hidden" name="id" value="${requestScope.existedCar.id}">
            <div class="mb-3">
                <label for="inputComment" class="form-label">Комментарий</label>
                <input type="text" class="form-control" name="comment" value="${requestScope.existedCar.comment}" id="inputComment"
                       required>
            </div>
            <div class="mb-3">
                <label class="form-label">Оценка автомобиля:</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="mark" id="inlineRadio1" value="1">
                    <label class="form-check-label" for="inlineRadio1" style= "color:blue">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="mark" id="inlineRadio2" value="2">
                    <label class="form-check-label" for="inlineRadio2" style= "color:blue">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="mark" id="inlineRadio3" value="3">
                    <label class="form-check-label" for="inlineRadio3" style= "color:blue">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="mark" id="inlineRadio4" value="4">
                    <label class="form-check-label" for="inlineRadio4" style= "color:blue">4</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="mark" id="inlineRadio5" value="5">
                    <label class="form-check-label" for="inlineRadio5" style= "color:blue">5</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <button type="reset" class="btn btn-secondary">Очистить форму</button>
        </form>
    </div>
</div>
</body>
</html>