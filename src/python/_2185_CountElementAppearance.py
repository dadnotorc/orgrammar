/*
写一个 count_5 function. It takes a list, counts the number 5 in the list, and return the count.


Example one
input: [5, 2, 6, 5, 3, 8, 8, 7, 5, 5, 4]
output: 4
有 4 个 5

input: [5, 5, 5, 5, 42, 555, 7, 799, 5, 31, 4, 5]
output: 6
*/

# main.py

import sys
from solution import count_5

input_path = sys.argv[1]

with open(input_path, 'r', encoding='utf-8') as f:
    list_1 = eval(f.readline())

result = count_5(list_1)
print(result, end="")


# solution.py
def count_5(list_1: list) -> int:
    '''
    :param list_1: Input list
    :param x: The elements to find
    :return: The number of occurrences of the element
    '''
    # -- write your code here --
    count = 0

    for i in list_1:
        if (i == 5):
            count += 1

    return count


# solution.py 另一种写法
def count_5(list_1: list) -> int:
    '''
    :param list_1: Input list
    :param x: The elements to find
    :return: The number of occurrences of the element
    '''
    # -- write your code here --
    count, i = 0, 0

    while i < len(list_1):
        if list_1[i] == 5:
            count += 1
        i += 1

    return count



