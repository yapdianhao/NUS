import sys
import socket

def main(args):
    port_number = int(args[1])
    alice_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    unrelinet = ('localhost', port_number)
    message = sys.stdin.read(64)
    while True:
        if not message:
            break
        else:
            alice_socket.sendto(message.encode(), unrelinet)
        message = sys.stdin.read(64)
    alice_socket.close()







main(sys.argv)