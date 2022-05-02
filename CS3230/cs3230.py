def go(x, y):
    ans, p, q = 0, x, y
    while q > 0:
        r = q % 2
        q = int(q / 2)
        ans += r * p
        p *= 2
        print(p, q, ans)
    return ans

print(go(5, 10))
