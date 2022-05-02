def main():
    ct = 0
    trash = list(map(int, input().split(" ")))
    lst = list(map(int, input().split(" ")))
    print(lst)
    for i in range(len(lst)):
        for j in range(i + 1, len(lst)):
            if (lst[i] > lst[j]):
                ct += 1
                #print((lst[i], lst[j]))
    return ct
print(main())