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
    '''
    string = message
    while True:
        if not message:
            print("no more data")
            break
        else:
            if string.find(b"  ") == -1:
                message = connectionSocket.recv(1)
                string += message
            else:
                print(string)
                connectionSocket.send(string)
                message = connectionSocket.recv(1)
                string = message
    connectionSocket.close()
    '''
    while True:
        if not message:
            break
        else:
            if message.find(b"  ") == -1:
                message += connectionSocket.recv(1)
                #read until hit 2 white spaces, header finished reading
                continue
            else:
                message = message[:-2] # slice away the 2 blank spaces at the end
                message = message.decode()
                lst = message.split(' ') # split the string into a list
                # the first element will be the request, get/post/delete
                # the second will be the path, /key/....
                message = b''
                if lst[0].lower() == 'get':
                    if lst[1] in d:
                        string = '200 OK content-length ' + str(len(d[lst[1]])) + '  ' + d[lst[1]]
                        #print(string)
                        connectionSocket.send(string.encode()) 
                    else:
                        string = '404 NotFound  '
                        #print(string)
                        connectionSocket.send(string.encode())
                elif lst[0].lower() == 'post':
                    for i in range(2, len(lst)):
                        lst[i] = lst[i].lower()
                    for i in range(2, len(lst) - 1):
                        if lst[i] == 'content-length' and i % 2 == 0 and lst[i + 1].isdigit():
                            value = connectionSocket.recv(int(lst[i + 1]))
                            d[lst[1]] = value.decode()
                            break
                    string = '200 Okay  '
                    #print(string)
                    connectionSocket.send(string.encode())
                elif lst[0].lower() == 'delete':
                    if lst[1] in d:
                        string = '200 OK content-length ' + str(len(d[lst[1]])) + ' ' + d[lst[1]]
                        del d[lst[1]]
                        connectionSocket.send(string.encode())
                    else:
                        string = '404 NotFound  '
                        connectionSocket.send(string.encode())
                message += connectionSocket.recv(1)


