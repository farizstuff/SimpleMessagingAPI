//websocket client
var stompClient = null;

function resetProperties(connected) {
    $("#button-start").prop("disabled", connected);
    $("#button-stop").prop("disabled", !connected);
    if (connected) {
        $("#input-group").show();
        $("#infotext").html("You are connected");
    }
    else {
        $("#message").html("");
        $("#input-group").hide();
        $("#infotext").html("You are not connected");
    }

}

function connect() {
    var socket = new SockJS('/chat-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        resetProperties(true);
        console.log("reset...");
        stompClient.subscribe('/chat/sendMessage', function (greeting) {
            showMessageOnPage(JSON.parse(greeting.body).message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    resetProperties(false);
}

function sendMessage() {
    stompClient.send("/chat/sendMessage", {}, JSON.stringify({'message': $("#message-input").val()}));
}

function showMessageOnPage(message) {
    $("#message").append("" + message + "<br>");
}
//end of websocket client

//send sms rest client
function sendSMS(messageContent) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sms";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            showResponseOnPage(JSON.stringify(json, null, 4));
        }
    };
    var data = JSON.stringify({"action": "SMS", "subAction": "sendSMS", "message": messageContent});
    xhr.send(data);
}

function showResponseOnPage(responseText) {
    $("#response").append("" + responseText + "<br>");
}
//end of send sms rest

//history sms rest client
function historySMS(limit) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sms";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            showResponseHistoryOnPage(JSON.stringify(json, null, 4));
        }
    };
    var data = JSON.stringify({"action": "SMS", "subAction": "getHistorySMS", "limit": limit});
    xhr.send(data);
}

function showResponseHistoryOnPage(responseText) {
    $("#response2").append("" + responseText + "<br>");
}
//end of history sms rest client

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    resetProperties(false);
    $( "#button-start" ).click(function() { connect(); });

    $( "#button-stop" ).click(function() { disconnect(); });

    $( "#button-send" ).click(function() {
        sendMessage();
        $("#message-input").val("");
        });

    $( "#button-sendsms" ).click(function() {
        $("#response").html("");
        sendSMS($("#message-inputsms").val());
        $("#message-inputsms").val("");
    });

    $( "#button-sendlimit" ).click(function() {
        $("#response2").html("");
        historySMS($("#message-inputlimit").val());
        $("#message-inputlimit").val("");
        });
});