from socket import *

serverName = 'localHost'
serverPort = 2105
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

message = ''

clientSocket.send(message.encode())
receivedMsg = clientSocket.recv(2048)

print("from server: ", receivedMsg.decode())

clientSocket.close()
