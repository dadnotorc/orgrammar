import sys
from solution import str_update

input_path = sys.argv[1]

with open(input_path, 'r', encoding="utf-8") as f:
    txt = f.read()

print(str_update(txt))
