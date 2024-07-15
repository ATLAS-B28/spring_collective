"use strict";

//const { send } = require("vite");

var usernamePage = document.querySelector("#app")
var chatPage = document.querySelector("#chat-page")
var usernameForm = document.querySelector("#usernameForm")
var messageForm = document.querySelector("#messageForm")
var messageInput = document.querySelector("#message");
var messageArea = document.querySelector("#messageArea")
var connectingElement = document.querySelector(".connecting")

var stompClient = null
var username = null

var password = null;

var color = [
     "#2196F3",
     "#32c787",
     "#00BCD4",
     "#ff5652",
     "#ffc107",
     "#ff85af",
     "#FF9800",
     "#39bbb0",
     "#fcba03",
     "#fc0303",
     "#de5454",
     "#b9de54",
     "#54ded7",
     "#54ded7",
     "#1358d6",
     "#d611c6",
]

function connect(event) {
     username = document.querySelector("#name").value().trim()
     password = document.querySelector("#password").value
     if(username) {
      if(password == "hello") {
        usernamePage.classList.add("hidden")
        chatPage.classList.remove("hidden")
         var socket = new SockJS("/websocket")
         stompClient = Stomp.over(socket)
         stompClient.connect({}, onConnected, onError)
      } else {
          let mes = document.getElementById("mes")
          mes.innerHTML = "Wrong Password"
      }
     }

     event.preventDefault()
}

function onConnected() {
     stompClient.subscribe("/topic/public", onMessageReceived)

     stompClient.send(
          "/app/chat.register",
          {},
          JSON.stringify({sender: username, type:"JOIN"})
     )

     connectingElement.classList.add("hidden")
}

function onError(error) {
     connectingElement.textContent = 
     "Could not connect to Websocket! Please refresh the page and try again or contact your administrator."
     connectingElement.computedStyleMap.color="red"
}

function send(event) {
     var messageContent = messageInput.value.trim()

     if(messageContent && stompClient) {
          let chatMessage = {
               sender: username,
               content: messageInput.value,
               type:"CHAT"
          }

          stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage))
          messageInput.value = ""
     }

     event.preventDefault()
}

function onMessageReceived(payload) {
     let message = JSON.parse(payload.body)
     let messageElement = document.createElement("li")

     if(message.type === "JOIN") {
          messageElement.classList.add("event-message")
          message.content = message.sender + " joined!"
     } else if(message.type == "LEAVE") {
          messageElement.classList.add("event-message")
          message.content = message.sender + " left!"
     } else {
          messageElement.classList.add("chat-message")

          let avatarElement = document.createElement("i")
          let avatarText = document.createTextNode(message.sender[0])
          avatarElement.appendChild(avatarText)
          avatarElement.style["background-color"] = getAvatarColor(message.sender)

          messageElement.appendChild(avatarElement)

          let usernameElement = document.createElement("span")
          let usernameText = document.createTextNode(message.sender)

          usernameElement.appendChild(usernameText)
          messageElement.appendChild(usernameElement)

          usernameElement.style["color"] = getAvatarColor(message.sender)
     
     }

     messageArea.appendChild(messageElement)
     messageArea.scrollTop = messageArea.scrollHeight
}

function getAvatarColor(messageSender) {
     let hash = 0
     for(let i = 0; i < messageSender.length; i++) {
          hash = 31*hash+messageSender.charCodeAt()
     }

     let index = Math.abs(hash%color.length)
     return color[index]
}

usernameForm.addEventListener("submit", connect, true)
messageForm.addEventListener("submit", send, true)
















