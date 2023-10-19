const socket = new SockJS('/chat'); // Altere para o endpoint WebSocket do seu aplicativo
const stompClient = Stomp.over(socket);

const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');
const chatMessages = document.getElementById('chat-messages');

stompClient.connect({}, (frame) => {
    console.log('Conexão estabelecida: ' + frame);

    stompClient.subscribe('/topic/teste', (message) => {
        const messageData = JSON.parse(message.body);
        appendMessage(messageData.sender, messageData.content);
    });

    sendButton.addEventListener('click', () => {
        sendMessage();
    });

    messageInput.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });
});

function sendMessage() {
    const messageText = messageInput.value;
    if (messageText.trim() !== '') {
        stompClient.send('/app/chat/teste', {}, JSON.stringify({ sender: 'carlos', content: messageText }));
        messageInput.value = '';
    }
}

function appendMessage(sender, content) {
    const messageDiv = document.createElement('div');
    messageDiv.className = 'message';
    messageDiv.innerHTML = `<strong>${sender}:</strong> ${content}`;
    chatMessages.appendChild(messageDiv);
}
