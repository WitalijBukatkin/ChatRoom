let currentChatId;

function messages(chatId) {
    let messageList = $('.messages');

    $('#chat' + chatId)
        .addClass("active");

    messageList.append("<h4>Loading...</h4>");

    currentChatId = chatId;

    connect();

    $.ajax({
        type: 'get',
        url: '/ajax/chats/' + currentChatId + '/messages',
        headers: header,
        dataType: 'json',
        success: function (messages) {
            messageList.empty();

            $.each(messages, function (index, message) {
                displayMessage(message.data, message.senderId);
            });
        }
    });
}

function newMessage() {
    let data = $('#message').val();

    if (data !== null || data !== "") {
        sendMessage(currentChatId, data);
        $('#message').val("");
    }
}

function displayMessage(text, senderName) {
    let side, sender;

    if (senderName === userName) {
        side = "right";
        sender = "I";
    } else {
        side = "left";
        sender = senderName;
    }

    $('.messages').append("<div>\n" +
        "                <div class=\"message\">\n" +
        "                    <div class=\"messageBlock side_" + side + "\">\n" +
        "                        <p>" + text + "</p>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"message\">\n" +
        "                    <div class=\"messageBlock side_" + side + "\">\n" +
        "                        <small class=\"senderName_" + side + "\">" + sender + " <i class=\"fa fa-user fa-sm\"></i></small>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>");
}