function chats() {
    let chatList = $('.chats');

    chatList.append("<h4>Loading...</h4>");

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
            chats();
            $('#chatName').val("");
        }
    });
}