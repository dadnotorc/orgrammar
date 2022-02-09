/*
2226 · Determine if the specified character is present in the string

In this problem, we will practice the use of the membership operators in and not in.
We have defined the function member_judge for you in solution.py, which has two arguments of type str,
str_1 and str_2, and you need to write the code at the comment position You need to use the if else statement
and the membership operators in and not in to determine whether the string H is in the variable str_1 and
whether M is in the variable str_2, and print out the result with the print statement respectively.
 
For example, to determine whether H is in the variable str_1, the two results of the print statement are in the following format.

print('H in str_1')
print('H not in str_1')
*/

# main.py
```
from solution import member_judge
import sys

str_1 = sys.argv[1]
str_2 = sys.argv[2]

member_judge(str_1, str_2)
```

# solution.py
```
def member_judge(str_1: str, str_2: str) -> None:
    """
    param str_1: a string
    param str_2: another string
    return: No return value is required
    """
    if 'H' in str_1:
        print("H in str_1")
    else:
        print("H not in str_1")
    
    if 'M' not in str_2:
        print("M not in str_2")
    else:
        print("M in str_2")
```

或者

```
def member_judge(str_1: str, str_2: str) -> None:
    """
    param str_1: a string
    param str_2: another string
    return: No return value is required
    """
    # -- write your code here --
    for char in str_1:
        if char == 'H':
            print("H in str_1")
            break
    else:
        print("H not in str_1")
    
    for char in str_2:
        if char == 'M':
            print("M in str_2")
            break
    else:
        print("M not in str_2")
```

