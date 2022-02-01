/*
2097 · Find the sum of two parameters

Please write Python code, import the function plus from a_plus_b.py to main.py, and read two parameters from the command,
 then call the parameters from a_plus_b and output the sum of addition, and finally print the result.

Please write the relevant Python code in main.py to complete the function call and to achieve the addition of two parameters.s.

Note that the parameter type is changed to int type

Example 1
When the input data are: 1 2
the output data are：3

Example 2
When the input data are: 3 4
the output data are：7
*/

# main.py

import sys

# try to import the function plus from a_plus_b.py in the same directory
from a_plus_b import plus

# read two parameters from command like `python main.py 1 2` by sys.argv
i = int(sys.argv[1])
j = int(sys.argv[2])

# call plus function and add them up
ans = plus(i, j)

# print the sum to the console
print(ans)
