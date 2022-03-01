<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Расходы по автомобилю ${requestScope.car.brand} ${requestScope.car.model}</title>
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
        <a class="nav-link active" aria-current="page" href="addexpense?id=${requestScope.id}">Добавить трату</a>
    </li>
    <li class="nav-item"  >
        <a class="nav-link active" aria-current="page" href="userexpenses">Список раcходов по Вашим автомобилям</a>
    </li>
    <li class="nav-item"  >
        <a class="nav-link active" aria-current="page" href="cabinet">Назад</a>
    </li>

</ul>
<form action="sumExpense" method="post" class="container-fluid" style="background: darkblue; width: 100%">
    <input type="hidden" name="id" value="${requestScope.id}">
    <div class="row" style="color: darkblue">
        <div class="col" >
            <div><label style= "color:orange; margin: 10px">Рассчитать сумму расходов за период:</label></div>
        </div>
        <div class="col">
            <div> <label style= "color:orange">c&nbsp;<input type="date" class="form-control"  name="fromDate" id="fromDateExpense" ></label></div>
        </div>
        <div class="col">
            <div><label for="toDateExpense"style= "color:orange">по&nbsp;&nbsp;<input class="form-control" type="date" name="toDate"  id="toDateExpense" ></label></div>
        </div>
        <div class="col" >
            <div><label for="expenseType"  style= "color:orange">Тип
                <select id="expenseType" name="typeOfExpense" class="form-select">
                    <option selected>Выберите тип...</option>
                    <option value="allexpenses">ВСЕ РАСХОДЫ</option>
                    <option value="fuel">АЗС</option>
                    <option value="service">ТО</option>
                    <option value="svstation">Ремонт на СТО</option>
                    <option value="selfrepair">Самостоятельный ремонт</option>
                    <option value="tirefitting">Шиномонтаж</option>
                    <option value="wash">Мойка</option>
                    <option value="accessory">Аксессуары</option>
                    <option value="otherexp">Другое</option>
                </select></label></div>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary" style="margin: 20px">Рассчитать</button>
        </div>

            <div class="col">
                <label for="sumExpOut" class="form-label" style= "color:orange">Стоимость <input value="${requestScope.sumExpOut}" type="text" name="sumExpOut" class="form-control" id="sumExpOut" placeholder="byn"></label>
            </div>


</div>
</form>

<div class="container" style="background: antiquewhite">
<table class="table table-hover caption-top justify-content-center">
<caption>Расходы по автомобилю ${requestScope.car.brand} ${requestScope.car.model}</caption>
<thead>
<tr>
    <th class="text-center" scope="col">#</th>
    <th class="text-center" scope="col">Дата</th>
    <th class="text-center" scope="col">Тип траты</th>
    <th class="text-center" scope="col">Стоимость</th>
    <th class="text-center" scope="col">Комментарий</th>
</tr>
</thead>
<c:forEach var="expenses" items="${requestScope.listCarExpenses}" varStatus="st">
    <tr>
        <th class="text-center align-middle" scope="row">${st.index}</th>
        <td class="text-center align-middle">${expenses.date}</td>
        <td class="text-center align-middle">${expenses.typeOfExpense}</td>
        <td class="text-center align-middle">${expenses.price}</td>
        <td class="text-center align-middle">${expenses.commentExp}</td>

        <td class="text-center align-middle">
            <div class="btn-group" role="group" aria-label="Basic example">
                <a href="deleteexpense?id=${expenses.id}&carid=${requestScope.car.id}" class="btn btn-secondary" role="button">Удалить</a>
            </div>
        </td>

    </tr>
</c:forEach>
</table>
<br>

</div>
</body>
</html>
