import sys

def IP_to_decimal():
    string = sys.argv[1]
    first = string[:8]
    sec = string[8:16]
    third = string[16:24]
    fourth = string[24:]
    ip_address = str(int(first,2)) + '.' + str(int(sec,2)) + '.'+ str(int(third,2)) + '.' + str(int(fourth,2))
    return ip_address

print(IP_to_decimal())
