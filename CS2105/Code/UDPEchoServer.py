from socket import *

serverPort = 2105

serverSocket = socket(AF_INET, SOCK_DGRAM)

serverSocket.bind(('', serverPort))
print('Server is ready to receive message')

message, clientAddress = serverSocket.recvfrom(2048)

serverSocket.sendto(message, clientAddress)

serverSocket.close()