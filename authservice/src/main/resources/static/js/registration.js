function registration() {
    let repeatPassword = $('#inputRepeatPassword').val();
    let password = $('#inputPassword').val();
    let username = $('#inputUsername').val();

    if (repeatPassword !== password) {
        failNoty("Passwords do not match");
        return;
    }

    if (password === "" || username === "") {
        failNoty("Password and username must not be empty!");
        return;
    }

    $.ajax({
        type: "post",
        url: "/oauth/register",
        headers: {
            "Authorization": "Basic " + btoa("service:secret"),
            "Content-Type": "application/json"
        },
        data: JSON.stringify({
            username: username,
            password: password
        }),
        dataType: "json",
        success: function () {
            window.location.href = "/login";
        },
        error: function () {
            failNoty("User is'nt created! Try other username or password");
        }
    });
}