'use strict';
var stompClient = null;

let messageList = $("#messages");

$(function () {
    let socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/' + chatId, onMessageReceived, {'username': userId});
    });
});

function sendMessage() {
    if (stompClient) {
        let message = {
            senderId: name,
            data: $('#message_text').val(),
            type: 'TEXT'
        };

        stompClient.send("/app/" + chatId, {}, JSON.stringify(message));
    }
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);

    messageList.append("<br> --> " + message.senderId + " " + message.data);
}