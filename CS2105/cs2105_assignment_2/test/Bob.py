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
    while True:
        if not received:
            break
        sequence_check = received[0:1].decode()
        # sending package that is in the correct sequence. 
        if sequence_check == str(sequence):
            message = received[35:]
            header = received[1:35].decode()
            check_sum = checksum_to_bit(checksum(message))
            # append the length of the checksum
            if len(check_sum) < 34:
                check_sum += ' ' * (34 - len(check_sum))
            if check_sum == header:
                sys.stdout.buffer.write(received[35:])
                sys.stdout.buffer.flush()
                bob_socket.sendto(str(sequence).encode(), alice_address)
                # expect next sequence
                if sequence == 0:
                    sequence += 1
                else:
                    sequence -= 1
            # Data corruption. Send wrong ack.
            else:
                if sequence == 0:
                    bob_socket.sendto('1'.encode(), alice_address)
                else:
                    bob_socket.sendto('0'.encode(), alice_address)
        # package out of sequence. Ignore, send old ack
        else:
            if sequence == 0:
                bob_socket.sendto('1'.encode(), alice_address)
            else:
                bob_socket.sendto('0'.encode(),alice_address)
                
        received = bob_socket.recv(64)
    bob_socket.close()

def checksum(string):
    return zlib.crc32(string)

def checksum_to_bit(num):
    return bin(num)

main(sys.argv)
