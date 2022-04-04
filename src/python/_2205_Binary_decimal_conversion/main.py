import sys
import functools
from solution import func

n = str(sys.argv[1])
int_2 = func()
int_num = int_2(n)
assert isinstance(int_2, functools.partial)
print(int_num)
