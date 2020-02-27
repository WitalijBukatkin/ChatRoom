'use strict';
let stompClient;

function connect() {
    let token = header.Authorization.split(' ')[1];
    let socket = new SockJS('/websocket/?access_token=' + token);

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/' + currentChatId, onMessageReceived);
    });
}

function unsubscribe(chatId){
    stompClient.unsubscribe('/topic/' + chatId);
}

function sendMessage(text) {
    if (stompClient) {
        let message = {
            senderId: userName,
            data: text,
            type: 'TEXT'
        };

        stompClient.send("/app/" + currentChatId, {}, JSON.stringify(message));
    }
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);

    displayMessage(message.data, message.senderId);
}