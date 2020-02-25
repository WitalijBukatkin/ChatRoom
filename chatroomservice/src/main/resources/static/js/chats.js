function updateChats() {
    let username = $('#chatList').val();

    $.ajax({
        type: "post",
        url: "http://192.168.1.70:5000/oauth/token",
        headers: {
            "Authorization": "Basic " + btoa("service:secret")
        },
        data: {
            grand_type: "password",
            username: username,
            password: password
        },
        dataType: "json",
        success: function () {
            alert("Ok!");
        }
    });
}