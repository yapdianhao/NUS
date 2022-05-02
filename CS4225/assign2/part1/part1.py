import sys

for line in sys.stdin:
    if 'alpha' not in line and 'beta' not in line and 'Invalid' in line:
        print(line)

