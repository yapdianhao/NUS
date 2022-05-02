import sys

def packetCat():
    data = sys.stdin.buffer.read1(6)
    stuff = sys.stdin.buffer.read1(1)
    size = 0
    while True:
        if data == b"":
            break
        while not stuff == b"B":
            size = size * 10 + int(stuff)
            stuff = sys.stdin.buffer.read1(1)
        for i in range(size):
            something = sys.stdin.buffer.read1(1)
            sys.stdout.buffer.write(something)
            sys.stdout.buffer.flush()
        size = 0
        data = sys.stdin.buffer.read1(6)
        stuff = sys.stdin.buffer.read1(1)

packetCat()


