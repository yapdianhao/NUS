###############################################
# This skeleton program is prepared for weak  #
# and average students.                       #
# If you are very strong in programming. DIY! #
# Feel free to modify this program.           #
###############################################

## Alice knows Bob's public key
## Alice sends Bob session (AES) key
## Alice receives messages from Bob, decrypts and saves them to file

import socket
import sys
import pickle
from Crypto.Cipher import AES
from Crypto.Cipher import PKCS1_OAEP
from Crypto.PublicKey import RSA
from Crypto import Random

from AESCipher import AESCipher

# Check if the number of command line argument is 2
if len(sys.argv) != 2:
    exit("Usage: python3 Alice.py <port>")
else:
    name, port = sys.argv

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect(('', int(port)))


def main():
    # read Bob's public key from file
    # key is stored using RSA.exportKey and must
    # be read with the respective counterpart
    pub_key = read_public_key()

    # generate AES key using the supplied AESCipher class
    # by providing a 32-byte random string as the password
    alice_key = generate_random_key()

    # encrypt session key using RSA PKCS1_OAEP
    # because RSA can only encode strings and numbers
    # we only need to encode and send the 32-byte random password

    # send the session key
    send_session_key(pub_key, alice_key)

    # receive the messages
    cipher = AESCipher(alice_key)
    receive_messages(cipher)

def read_public_key():
    with open("bob-python.pub", "r") as f:
        pub_key = RSA.importKey(f.read())
        return pub_key

def generate_random_key():
    key = Random.get_random_bytes(32)
    return key

def send_session_key(pub_key, alice_key):
    rsa = PKCS1_OAEP.new(pub_key)
    session_key = rsa.encrypt(alice_key)
    sock.send(pickle.dumps(session_key))


def receive_messages(cipher):
    # because each line is sent by pickling
    # it might be better to read from the socket
    # as a stream and let pickle do its job
    temp = sock.makefile("b", 1024)
    with open("msgs.txt", "a") as f:
        try:
            for i in range(10):
                received = pickle.load(temp)
                decrypted = cipher.decrypt(received)
                f.write(decrypted.decode())
        except:
            pass

main()

