var stompClient = null;

function setConnected(connected) {
}

function connect() {
    var socket = new SockJS('/monitor');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/notifications/log', function(message) {
            console.log('Received: ' + message);
            showLog(message.body);
        });
    });
}

function showLog(log) {
    var msgBox = $('#messages');
    msgBox.append(log + '<br/>');
    msgBox[0].scrollTop = msgBox[0].scrollHeight;
}