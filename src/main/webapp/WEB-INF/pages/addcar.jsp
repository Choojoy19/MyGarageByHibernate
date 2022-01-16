<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить авто</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div style="background: black" >
    <img src="https://gofrag.ru/images/100/my-garage-1.jpg" alt="logo"
         width="160px" height="75px">
    <a href="cabinet" style="color: blue; font-size: medium">Вернуться к профилю</a>
</div>
<div style= "width:100%; height:100%; background: black; top: 100px">
    <form class="row g-3" action="addnewcar" method="post" style="width:40%; height:300px; position: absolute; top: 130px; left: 450px">
        <div class="col-md-6">
            <label for="brand" class="form-label" style= "color:orange">Марка</label>
            <input type="text" name="brand" class="form-control" id="brand">
        </div>
        <div class="col-md-6">
            <label for="model" class="form-label" style= "color:orange">Модель</label>
            <input type="text" name="model" class="form-control" id="model">
        </div>
        <div class="col-md-4">
            <label for="yearOfManufacture" class="form-label" style= "color:orange">Год выпуска</label>
            <input type="text" name="yearOfManufacture" class="form-control" id="yearOfManufacture" >
        </div>
        <div class="col-md-4">
            <label for="color" class="form-label" style= "color:orange">Цвет</label>
            <input type="text" name="color" class="form-control" id="color">
        </div>
        <div class="col-md-4">
            <label for="engineVolume" class="form-label" style= "color:orange">Объем двигателя</label>
            <input type="text" name="engineVolume" class="form-control" id="engineVolume" placeholder="2.0">
        </div>
        <div class="col-md-6">
            <label for="bodyType" class="form-label" style= "color:orange">Тип кузова</label>
            <select id="bodyType" name="bodyType" class="form-select">
                <option selected>Выберите тип...</option>
                <option value="sedan">Седан</option>
                <option value="wagon">Универсал</option>
                <option value="liftback">Лифтбек</option>
                <option value="coupe">Купе</option>
                <option value="hatchback">Хетчбек</option>
                <option value="suv">SUV</option>
                <option value="other">Другой</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="engineType" class="form-label" style= "color:orange">Тип двигателя</label>
            <select id="engineType" name="engineType" class="form-select">
                <option selected>Выберите тип</option>
                <option value="petrol">Бензин</option>
                <option value="diesel">Дизель</option>
                <option value="gas">Газ-бензин</option>
                <option value="hybrid">Гибрид</option>
                <option value="electric">Электро</option>
            </select>
        </div>
        <br>
        <div class="col-md-12">
            <label  class="form-label" style= "color:orange">Оценка автомобиля &nbsp; &nbsp;&nbsp; </label>
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
        <div class="col-md-12">
            <label  class="form-label" style= "color:orange">Комментарий об автомобиле</label>
            <div class="form-floating">
                <textarea class="form-control" name="comment" placeholder="Leave a comment here" id="comment" style="height: 100px"></textarea>
                <label for="comment">Введите текст...</label>
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
