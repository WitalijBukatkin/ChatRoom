function authorize() {
    let username = $('#inputUsername').val();
    let password = $('#inputPassword').val();

    $.ajax({
        type: "post",
        url: "/oauth/token",
        headers: {
            "Authorization": "Basic " + btoa("service:secret")
        },
        data: {
            grant_type: "password",
            username: username,
            password: password
        },
        dataType: "json",
        success: function (token) {
            $('#accessToken').val(token.access_token);
            $('.form').submit();
        },
        error: function () {
            failNoty("User or password is fail!");
        }
    });
}