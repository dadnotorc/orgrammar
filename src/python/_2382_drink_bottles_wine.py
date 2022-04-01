'''
2382 · Can drink at most a few bottles of wine (Python version)

A bottle of wine is 5 yuan, and 55 wine bottles can be exchanged for a bottle of wine.
 How many bottles of wine can I drink at most for n yuan?

0 <= n <= 1000
You cannot obtain wine by borrowing and returning a bottle

Example:
input: n = 20
output: 4

input: n = 25
output: 6
'''

money = int(input())
wine = money // 5 # 用 // 做整数运算, / 就变成浮点数运算了
bottle = wine

while (bottle // 5 != 0): # 还能以 5 换 1 的时候
    wine += bottle // 5
    bottle = bottle // 5 + bottle % 5

print(wine)


# 这个写法里 把钱数 n 和 酒瓶数 rem 搞混了, 就错了

# n = int(input())
# ans = 0
# rem = 0
#
# while(n // 5 != 0 or rem // 5 != 0):
#     ans += n // 5
#     rem = n % 5
#     n = n // 5 + rem
#
# print(ans)