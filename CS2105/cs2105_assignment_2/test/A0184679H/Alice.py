import sys
import socket
import zlib

def main(args):
    port_number = int(args[1])
    alice_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    unrelinet = ('localhost', port_number)
    # 1 bit of sequence number + 34 bits of checksum, 29 bits of stuff
    message = sys.stdin.buffer.read1(29)
    alice_socket.settimeout(0.01)
    sequence = 0
    while True:
        if not message:
            break
        else:
            try:
                datagram = process(message, sequence)
                alice_socket.sendto(datagram, unrelinet)
                # get the ack
                acked = alice_socket.recv(1)
                # Data corruption.
                # if ack does not match sequence sent, resend
                while (sequence != int(acked.decode())):
                    alice_socket.sendto(datagram, unrelinet)
                    acked = alice_socket.recv(1)
                if sequence == 0:
                    sequence += 1
                else:
                    sequence -= 1
            # ACK corruption. 
            except ValueError:
                continue
            # Timeout.
            except socket.timeout:
                continue
        message = sys.stdin.buffer.read1(29)
    alice_socket.close()

def checksum(string):
    return zlib.crc32(string)

def checksum_to_bit(num):
    return bin(num).encode()

def process(message, sequence):
    to_send = b''
    to_send += str(sequence).encode()
    to_send += checksum_to_bit(checksum(message))
    if len(to_send) < 35:
        to_send += b' ' * (35 - len(to_send)) 
    to_send += message
    return to_send

main(sys.argv)
