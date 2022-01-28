<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Новая трата</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div style="background: black" >
    <img src="https://gofrag.ru/images/100/my-garage-1.jpg" alt="logo"
         width="160px" height="75px">
    <a href="carexpenses" style="color: blue; font-size: medium">Назад</a>
</div>
<div style= "width:100%; height:100%; background: black; top: 100px">
    <form class="row g-3" action="addnewexpense" method="post" style="width:40%; height:300px; position: absolute; top: 130px; left: 450px">
        <input type="hidden" name="id" value="${requestScope.id}">
        <div class="col-md-3">
            <label for="dateExpense" class="form-label" style= "color:orange">Дата</label>
            <input type="date" name="date" class="form-control" id="dateExpense" >
        </div>
        <div class="col-md-6">
            <label for="expenseType" class="form-label" style= "color:orange">Тип траты</label>
            <select id="expenseType" name="typeOfExpense" class="form-select">
                <option selected>Выберите тип...</option>
                <option value="fuel">АЗС</option>
                <option value="service">ТО</option>
                <option value="svstation">Ремонт на СТО</option>
                <option value="selfrepair">Самостоятельный ремонт</option>
                <option value="tirefitting">Шиномонтаж</option>
                <option value="wash">Мойка</option>
                <option value="accessory">Аксессуары</option>
                <option value="otherexp">Другое</option>
            </select>
        </div>
        <div class="col-md-3">
            <label for="price" class="form-label" style= "color:orange">Стоимость</label>
            <input type="text" name="price" class="form-control" id="price" placeholder="byn">
        </div>
        <div class="col-md-12">
            <label  class="form-label" style= "color:orange">Комментарий</label>
            <div class="form-floating">
                <textarea class="form-control" name="commentexp" placeholder="Leave a comment here" id="commentexp" style="height: 100px"></textarea>
                <label for="commentexp">Введите текст...</label>
            </div>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Добавить</button>
            <button type="reset" class="btn btn-dark">Очистить форму</button>
        </div>
    </form>
</div>
</body>
</html>
