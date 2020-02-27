let isMobile = false;

function showMenu() {
    $('.chats').css("display", "block");
    $('.newChat').css("display", "block");

    $('.menu').css("display", "none");
    $('.chatTitle').css("display", "none");
    $('.messages').css("display", "none");
    $('.newMessage').css("display", "none");
    isMobile = true;
}

function hideMenu() {
    $('.menu').css("display", "block");
    $('.chatTitle').css("display", "block");
    $('.messages').css("display", "block");
    $('.newMessage').css("display", "block");

    $('.chats').css("display", "none");
    $('.newChat').css("display", "none");
    isMobile = true;
}