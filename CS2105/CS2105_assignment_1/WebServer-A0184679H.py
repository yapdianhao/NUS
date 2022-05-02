import sys
from socket import *

d = {}

serverPort = int(sys.argv[1])

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))


while True:
    serverSocket.listen()
    connectionSocket, clientAddress = serverSocket.accept()
    message = connectionSocket.recv(1)
    while True:
        if not message:
            break
        else:
            if message.find(b"  ") == -1:
                message += connectionSocket.recv(1)
                continue
            else:
                #print(message)
                message = message[:-2] # slice away the 2 blank spaces at the end
                message = message.decode()
                lst = message.split(' ') # split the string into a list
                # the first element will be the request, get/post/delete
                # the second will be the path, /key/....
                message = b''
                if lst[0].lower() == 'get':
                    if lst[1] in d:
                        string = '200 OK content-length ' + str(len(d[lst[1]])) + '  '
                        connectionSocket.send(string.encode() + d[lst[1]])
                        #print(string.encode()) 
                    else:
                        string = '404 NotFound  '
                        connectionSocket.send(string.encode())
                elif lst[0].lower() == 'post':
                    for i in range(2, len(lst)):
                        lst[i] = lst[i].lower()
                    for i in range(2, len(lst) - 1):
                        if lst[i] == 'content-length' and i % 2 == 0 and lst[i + 1].isdigit():
                            value = connectionSocket.recv(int(lst[i + 1]))
                            if len(value) < int(lst[i + 1]):
                                value += connectionSocket.recv(int(lst[i + 1]) - len(value))
                                #print(b"AFTER CHECK: " + value)
                            while (len(value) < int(lst[i + 1])):
                                value += connectionSocket.recv(1)
                            d[lst[1]] = value
                            break
                    string = '200 Okay  '
                    connectionSocket.send(string.encode())
                elif lst[0].lower() == 'delete':
                    if lst[1] in d:
                        string = '200 OK content-length ' + str(len(d[lst[1]])) + '  '
                        connectionSocket.send(string.encode() + d[lst[1]])
                        del d[lst[1]]
                    else:
                        string = '404 NotFound  '
                        connectionSocket.send(string.encode())
                message += connectionSocket.recv(1)


