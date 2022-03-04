2216 · String summing, repeated output, slicing

This problem has two strings str_1 and str_2. We need you to refine the code in solution.py.

add the strings str_1 and str_2 to get a new string;

multiply str_1 by 2 to get another new string;

slice str_1 to get the data from the zero to the three of the index of the string to get the new string.
(题目好像有问题, 不用理会这句)

Slices str_1 to get the data from index 1 to 4 of the string, i.e. indexes 1, 2, 3, to get the new string.

Example 1
input: str_1 = 'Hello', str_2 = 'Python'
output: ('HelloPython', 'HelloHello', 'ell')

Example 2
input: str_1 = 'Python', str_2 = 'Perfect'
output: ('PythonPerfect', 'PythonPython', 'yth')



# main.py
```
import sys
from solution import add_repeat_slicing

str_1 = sys.argv[1]
str_2 = sys.argv[2]
str_3 = add_repeat_slicing(str_1, str_2)
print(str_3)
```


# solution.py
```
def add_repeat_slicing(str_1: str, str_2: str) -> tuple:
    """
    :param str_1: The first source string
    :param str_2: The Second source string
    :return: tuple: A tuple containing three new strings
    """
    a = str_1 + str_2
    b = str_1 * 2
    c = str_1[1:4]
    return (a, b, c)
```