let currentChatId = null;

let messageList = $(".messages");
let messageData = $("#messageData");

function messages(chatId) {
    messageList.append("<h5>Loading...</h5>");

    if (currentChatId != null) {
        $(".chats a").removeClass("active");
        unsubscribe(currentChatId);
    }

    $('#chat' + chatId)
        .addClass("active");

    currentChatId = chatId;

    if (isMobile) {
        hideMenu();
    }

    $('#chatDescription').text($('#chat' + currentChatId + " h5").text());

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

    connect();
}

function newMessage() {
    let data = messageData.val();

    if (data !== null || data !== "") {
        messageData.val("");
        sendMessage(data);
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

    messageList.append("<div>\n" +
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