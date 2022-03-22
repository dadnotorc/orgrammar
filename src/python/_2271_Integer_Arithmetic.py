'''
2271 · Integer Arithmetic (Python version)

Please get two integers a, b from the standard input stream (console),
and calculate the sum, difference, product and quotient of a, b.
(The quotient only keeps the integer part).
Then output to the standard output stream (console) through the print statement.
The results are divided into four lines for output.

1 <= a, b <= 10000

Example 1
When a = 9 and b = 6, the printed result of the program execution is:
15
3
54
1

Example 2
When a = 5 and b = 10, the printed result of the program execution is:
15
-5
50
0
'''

a = int(input())
b = int(input())

print(a + b)
print(a - b)
print(a * b)
print(a // b)

'''
“+”（加法）：加法 - 相加运算符两侧的值
“-”（减法）：减法 - 左操作数减去右操作数
“*”（乘法）：乘法 - 相乘操作符两侧的值
“/”（除法）：除法 - 左操作数除以右操作数
“//”（除法）：除法 - 左操作数整除右操作数
“％”（取余）：取余 - 左操作数除以右操作数的余数


示例
a, b = 5, 2
print(a + b)
print(a - b)
print(a * b)
print(a / b)
print(a // b)
print(a % b)

以上实例编译运行结果如下：
7
3
10
2.5
2
1
'''