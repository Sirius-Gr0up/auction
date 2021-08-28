var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    // $("#greetings").html("");
}

var socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/greetings', function (greeting) {
        showGreeting(JSON.parse(greeting.body).content);
        showGreeting(JSON.parse(greeting.body).winner);
        showGreeting(JSON.parse(greeting.body).now);
    });
});

function sendName() {
    let poroduct =document.getElementById('productId');
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#myRange").val(),
                                                             'productId': poroduct.textContent}));
}

function showGreeting(message) {

    // console.log(poroduct.textContent);
    var node = document.createElement("div");
    node.classList.add("col");
    var textnode = document.createTextNode(message);
    node.appendChild(textnode);
    let greetings=document.getElementsByClassName("greetings")[0];
    greetings.appendChild(node);


}

$(function () {

    $( "#send" ).click(function() {

        sendName(); });
});