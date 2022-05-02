import zlib
import sys

def checksum():
    file = sys.argv[1]
    with open(file, "rb") as f:
        bytes = f.read()
    checksum = zlib.crc32(bytes)
    return checksum


print(checksum())
