"""
2273 · Sequential Output
Naive

Please obtain a positive integer n from the standard input stream (console).
It is required to output all integers in the range [1,n] through the print statement,
and each number must be output in a new line.

1 <= n <= 100

Example 1
When n = 3, the printed result of the program execution is:
1
2
3

Example 2
When n = 5, the printed result of program execution is:
1
2
3
4
5

"""

n = int(input())

# range(n) 是从 0 到 n - 1, increment by 1
print("printing range({})".format(n))
for i in range(n):
	print(i)

# range(i, n) 是从 i 到 n - 1, increment by 1
print("----------")
start = 1
print("printing range({}, {})".format(start, n + 1))
for i in range(start, n + 1):
	print(i)

# range(i, n, x) 是从 i 到 n - 1, increment by x
print("----------")
x = 2
print("printing range({}, {}), increment by {}".format(start, n + 1, x))
for i in range(1, n + 1, x):
	print(i)


print("----------")
print("while loop")
i = 1
while i <= n:
    print(i)
    i += 1