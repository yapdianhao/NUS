from socket import *

serverName = 'localHost'
serverPort = 2105

clientSocket = socket(AF_INET, SOCK_DGRAM)

message = input("Enter a message: ")

clientSocket.sendto(message.encode(), (serverName, serverPort))

receivedMsg, serverAddress = clientSocket.recvfrom(2048)

print('from server: ' + receivedMsg.decode())

clientSocket.close()
