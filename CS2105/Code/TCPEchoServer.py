from socket import *

serverPort = 2105 # hard-coded

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen()
print("Server is now ready to receive mesage")

connectionSocket, clientAddr = serverSocket.accept()
message = connectionSocket.recv(2048) # hard-coded
if message == b'':
    print("no")
connectionSocket.send(message)
connectionSocket.close()
