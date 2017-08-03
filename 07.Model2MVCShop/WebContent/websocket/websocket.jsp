
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="id" value="${user.userId}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Echo Chamber</title>
</head>
<body>
<div>
    <div>
    	<input type="hidden" id="userId"  value="${id}" />
        <input type="text" id="messageinput" onkeydown="send()"/>
    </div>
    <div>
        <button type="button" onclick="openSocket();">Open</button>
        <button type="button" onclick="send();">Send</button>
        <button type="button" onclick="closeSocket();">Close</button>
    </div>
    <!-- Server responses get written here -->
    <div id="messages"></div>
</div>
    <!-- Script to utilise the WebSocket -->
    <script type="text/javascript">
        var webSocket;
        var messages = document.getElementById("messages");
		var messageinput=document.getElementById("messageinput");
        
		var userId=document.getElementById("userId").value;
        
        function openSocket() {
            // Ensures only one connection is open at a time
            if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
                writeResponse("WebSocket is already opened.");
                return;
            }
            
            // Create a new instance of the websocket
            webSocket = new WebSocket("ws://localhost:8080/chat");
            //webSocket = new WebSocket("ws://192.168.0.23:8080/chat");
            writeResponse("WebSocket open.");
            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function(event) {
                // For reasons I can't determine, onopen gets called twice
                // and the first time event.data is undefined.
                // Leave a comment if you know the answer.
                if (event.data === undefined)
                    return;

                writeResponse(event.data);
            };

            webSocket.onmessage = function(event) {
                writeResponse(event.data);
            };

            webSocket.onclose = function(event) {
                writeResponse("Connection closed");
            };
        }

        /**
         * Sends the value of the text input to the server
         */
        function send() {
        	
        	if(event.keyCode==13 || event.keyCode==null){
            var text = document.getElementById("messageinput").value;
            webSocket.send(userId+" : "+text);
            messageinput.value="";
        	}
        }

        function closeSocket() {
            webSocket.close();
        }

        function writeResponse(text) {
            messages.innerHTML += "<br/>" + text;
        }
    </script>

</body>
</html>
