# Object Types

`type(object)` 
`type(class_name, base_class, dict)`

`isinstance(object, classtype)`

# Math

`//` - 整除, 8 // 3 = 2

`**` - 指数, 3 ** 4 = 81 = 3 ^ 4

`round()` - 四舍五入, 可指定小数点后长度

# String

`f"{num:.2f}"` - 浮点, 2位小数

`lower()` - 变小写, `"string".lower()`

`count()` - 统计某个字符出现次数 `"string".count('x')`


# Errors

`raise TypeError`

`raise ValueError`

```python
try:
	...
except ValueError:
	...
	raise
```

```python
try:
	...
except Exception as e:
	return e
```


# Loop

`for i in range(n)` -  [0, n - 1], increment by 1

`for i in range(l, r)`  - [l, r - 1], increment by 1

`for i in range(l, r, x)`  - [l, r - 1], increment by x


# Condition

```python
if xxx:
	...
elif yyy:
	...
else:
	...
```

