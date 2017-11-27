var stompClient = null;

function setConnected(connected) {
    $('#connect').prop('disabled', connected);
    $('#disconnect').prop('disabled', !connected);

    if (connected)
        $('#conversation').show();
    else
        $('#conversation').hide();

    $('#greetings').html('');
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (imageBase64) {
            showImages(imageBase64);
        });
    });
}

function disconnect() {
    if (stompClient !== null)
        stompClient.disconnect();

    setConnected(false);
    console.log('Disconnected!');
}

function showImages(imageBase64) {
    $('#greetings').append('<img src="' + imageBase64 + '">');
}

$(function () {
    $('form').on('submit', function (e) {
        e.preventDefault();
    });
    $('#connect').click(function () {
        connect();
    });
    $('#disconnect').click(function () {
        disconnect();
    });
});