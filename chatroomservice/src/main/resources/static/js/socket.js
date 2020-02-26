'use strict';
var stompClient = null;

function connect() {
    let token = header.Authorization.split(' ')[1];
    let socket = new SockJS('/websocket/?access_token=' + token);

    stompClient = Stomp.over(socket);
    stompClient.connect();
}

function subscribe(){
    stompClient.subscribe('/topic/' + currentChatId, onMessageReceived);
}

function unsubscribe(chatId){
    stompClient.unsubscribe('/topic/' + chatId);
}

function sendMessage(chatId, text) {
    if (stompClient) {
        let message = {
            senderId: name,
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