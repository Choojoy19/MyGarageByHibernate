function validateForm (event) {
    // проверяем пароли
    // выбираем элементы
    var password1 = document.getElementById('password1');
    var password2 = document.getElementById('password2');
    // сравниваем написанное, если не равно, то:
    if (password1.value !== password2.value) {
        // сообщаем пользователю, можно сделать как угодно
        alert('Passwords not match!!!');
        document.getElementById('regForm').reset();
        // сообщаем браузеру, что не надо обрабатывать нажатие кнопки
        // как обычно, т. е. не надо отправлять форму
        return false;
    }
}
