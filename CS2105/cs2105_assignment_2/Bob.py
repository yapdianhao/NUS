import sys
import socket
import zlib

def main(args):
    port_number = int(args[1])
    bob_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    bob_address = ('localhost', port_number)
    bob_socket.bind(bob_address)
    sequence = 0
    received, alice_address = bob_socket.recvfrom(64)
    while (True):
        if not received:
            break
        else:
            message = received[32:].decode()
            check_sum = checksum_to_bit(checksum(message))
            if check_sum == message[0:31]:
                sys.stdout.buffer.write(message[32:])
                print(True)
                sys.stdout.buffer.flush;
                bob_socket.sendto(sequence, alice_address)
                if sequence == 0:
                    sequence += 1
                else:
                    sequence -= 1
            else:
                if sequence == 0:
                    bob_socket.sendto(1, alice_address)
                else:
                    bob_socket.sendto(0, alice_address)
                # send the ACK
            #sys.stdout.buffer.write(received)
            #sys.stdout.buffer.flush();
            received = bob_socket.recv(64)
    bob_socket.close()

def checksum(string):
    return zlib.crc32(string.encode())

def checksum_to_bit(num):
    return bit(num).encode()


main(sys.argv)