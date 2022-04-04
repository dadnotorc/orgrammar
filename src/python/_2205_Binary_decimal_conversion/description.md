# 2205 · Binary to decimal conversion

Python's functools module provides a number of useful features,
one of which is biased function that make function calls more easily by setting default values for the arguments.

The int() function can convert strings to integers, and when only strings are passed in,
the int() function will convert them to decimal by default.
However, the int() function also provides an additional base argument with default value 10.
If the base arguments are passed in, the function will do base conversions.

In order to avoid redefining the base parameter every time we use the int() function for binary conversions,
we need to use the `functools` module to help us create a partial function that
fixes the base parameter for binary and decimal conversions.

Write the code in solution.py that is required to compute the decimal conversion of a binary string n.


### Example 1:
input: n = 1010101 \
output: 85

### Example 2:

input: When n = 10000 \
output: 16


## Hint 

What `functools.partial` does is to fix certain parameters of a function and return
a new function that is simpler to call. We can define a recursive function like this.

```
import functools

func_new = functools.partial(func, parameters)
When a function has too many parameters and needs to be simplified, use functools.partial to create a new function that holds some of the parameters of the original function, making it simpler to call.
```

Learn about partial functions in the [Python documentation on partial functions](https://blog.csdn.net/qq_43030934/article/details/104048662).



## 解题思路
偏函数可以通过设定参数的默认值，可以降低函数调用的难度

源代码
python
1
2
3
4
5
6

import functools

def func():
int2 = functools.partial(int, base = 2)
return int2
知识要点
偏函数
Python 的 functools 模块提供了很多有用的功能，其中一个就是偏函数（Partial function）。要注意，这里的偏函数和数学意义上的偏函数不一样。
在介绍函数参数的时候，我们讲到，通过设定参数的默认值，可以降低函数调用的难度。而偏函数也可以做到这一点。举例如下：
int 函数可以把字符串转换为整数，当仅传入字符串时，int 函数默认按十进制转换：

1
2
>>> int('12345')
12345
但 int 函数还提供额外的 base 参数，默认值为 10。如果传入 base 参数，就可以做 N 进制的转换：

1
2
3
4
>>> int('12345', base=8)
5349
>>> int('12345', 16)
74565
假设要转换大量的二进制字符串，每次都传入 int(x, base=2) 非常麻烦，于是，我们想到，可以定义一个 int2 的函数，默认把 base=2 传进去：

1
2
def int2(x, base=2):
return int(x, base)
这样，我们转换二进制就非常方便了。

1
2
3
4
>>> int2('1000000')
64
>>> int2('1010101')
85
functools.partial 就是帮助我们创建一个偏函数的，不需要我们自己定义 int2，可以直接使用下面的代码创建一个新的函数 int2：

1
2
3
4
5
6
>>> import functools
>>> int2 = functools.partial(int, base=2)
>>> int2('1000000')
64
>>> int2('1010101')
85
所以，简单总结 functools.partial 的作用就是，把一个函数的某些参数给固定住（也就是设置默认值），返回一个新的函数，调用这个新函数会更简单。

在重设之后, 也可以在函数调用时传入其他值:

1
2
​>>> int2('1000000', base=10)  # 这里 base 变成了 10，覆盖了已设的默认值 2。
1000000
注意 这里在创建新的偏函数后，依旧可以更改已经设置的默认值，但是必须清晰指出是更改了 base 的值。

否则如果直接传如一个数值会报错:

1
int2('100', 10)  # 报错，10 前未加 base=，不能分辨这是传给 base 的
原因如下:

创建偏函数时，实际上可以接收函数对象、*args 和 **kw 这 3 个参数，当传入：

1
int2 =functools.partial(int, base=2)  # int 是函数对象，base=2 是 **kw，没有传入 *args 参数
实际上固定了 int() 函数的关键字参数 base，也就是：

1
int2('10010')
相当于：

1
2
kw = {'base': 2}
int('10010', **kw)  # 如果不指明 base=2 而只是传入 2 的话，那么 2 被认为是 *args 的值
当传入：

1
max2 =functools.partial(max, 10)  # 这里的 10 显然就是作为 *args 里的值传入的
实际上会把 10 作为 *args 的一部分自动加到参数列表里去，也就是：

1
max2(5, 6, 7)  # 原本有 *args 的列表，然后会把 10 再加进去
相当于：

1
2
args = (10, 5, 6, 7)
max(*args)
结果为 10。