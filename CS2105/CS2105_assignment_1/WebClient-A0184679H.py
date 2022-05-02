from socket import *

serverName = 'localHost'
serverPort = 2000
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

message = "POST /key/ModuleCode content-length 6  CS2105GET /key/ModuleCode  POST /key/ModuleCode content-length 7  CS2040S"

clientSocket.send(message.encode())
receivedMsg = clientSocket.recv(2048)
while True:
    print(receivedMsg.decode())
    receivedMsg = clientSocket.recv(2048)
    if not receivedMsg:
        break
        print(True)

clientSocket.close()
