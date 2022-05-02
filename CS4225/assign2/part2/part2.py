import sys
import collections

d = collections.defaultdict(int)
s = set()
ct = 0
for line in sys.stdin:
    line = line.split(",")
    if ct == 0:
        ct += 1
        continue
    day = int(line[-1].rstrip())
    if day % 7 == 0:
        s.add((line[0], line[1]))


for src, dest in s:
    d[dest] += 1

print([i for i in d if d[i] == 13])
    

