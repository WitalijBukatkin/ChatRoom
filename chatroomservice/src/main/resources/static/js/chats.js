function getChats() {
    let chatList = $('.chats');

    chatList.append("<h5>Loading...</h5>");

    $.ajax({
        type: 'get',
        url: '/ajax/chats',
        headers: header,
        dataType: 'json',
        success: function (chats) {
            chatList.empty();
            $.each(chats, function (index, chat) {
                chatList.append("<a href='#' id='chat" + chat.id + "' onclick=\"messages(" + chat.id + ")\" class=\"list-group-item list-group-item-action flex-column align-items-start\">\n" +
                    "                    <div class=\"d-flex justify-content-between\">\n" +
                    "                        <h5 class=\"mb-1\">" + chat.name + "</h5>\n" +
                    "                    </div>\n" +
                    "            </a>");
            });

            if (chatList.text().length === null) {
                chatList.append("<h5>Type username and confirm, or refresh page</h5>");
            }
            else {
                messages(chats[0].id);
            }
        }
    });
}

function newChat() {
    let withUserId = $('#chatName').val().toLowerCase();

    if (withUserId === null || withUserId === '') {
        return;
    }

    $.ajax({
        type: 'post',
        url: '/ajax/chats',
        headers: header,
        dataType: 'json',
        data: {
            withUserId: withUserId
        },
        success: function (chats) {
            getChats();
            $('#chatName').val("");
        }
    });
}