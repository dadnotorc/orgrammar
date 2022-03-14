import sys
from solution import composition

input_path = sys.argv[1]

with open(input_path, 'r', encoding='utf-8') as f:
    dict_1 = eval(f.readline())

result = composition(dict_1)
print(result, end="")