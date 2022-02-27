$(document).ready(function() {
    $('#repPass').change(function() {
        var pass = $("#pass").val();
        var pass_rep = $("#repPass").val();

        if (pass != pass_rep) {
            $("#repPass").css('border', 'red 1px solid');
            $('#errorBlock').html('Пароли не совпадают');
        }
    });
});