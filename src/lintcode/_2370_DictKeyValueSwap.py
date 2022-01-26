/*
2370 · Dictionary key value swap

TinyURL is a URL simplification service. For example, when you enter a URL
https://www.lintcode.com/problem/encode-and-decode-tinyurl/, it will return a simplified URL
http://tinyurl. com/4e9iAk, now there is a mapping relationship table from long link to short link,
we need to flip this mapping relationship table to obtain long link through short link.

Please complete the code in solution.py to realize the function of reversal: The reversal function accepts
a dictionary type src parameter, which represents the mapping relationship table from long link to short link.
Write code in the function body to implement the exchange of the key and value of the src dictionary,
and return the new dictionary.y.

Both key and value are unique.

Example one
When the src parameter is the following value:
{
     'https://lintcode.com/problems/design-tinyurl':'http://tinyurl.com/4e9iAk',
}
The printed result of the program execution is:
{'http://tinyurl.com/4e9iAk':'https://lintcode.com/problems/design-tinyurl'}

Example two
When the src parameter is the following value:
{
     'https://www.lintcode.com/problem/2370/':'https://lt.cn/1/2370',
     'https://www.lintcode.com/problem/2371/':'https://lt.cn/1/2371',
}
The printed result of the program execution is:
{'https://lt.cn/1/2370':'https://www.lintcode.com/problem/2370/',
'https://lt.cn/1/2371':'https:/ /www.lintcode.com/problem/2371/'}

*/


##########
solution.py


# 解法2 - 使用字典生成, 取代循环
def reversal(src: dict[str:str]) -> dict[str:str]:
    """
    :param src: Original dictionary
    :return: Return a reversed dictionary
    """
    return {v: k for k, v in src.items()}


# 解法1 - 使用循环
def reversal(src: dict[str:str]) -> dict[str:str]:
    """
    :param src: Original dictionary
    :return: Return a reversed dictionary
    """
    ans = dict()
    for key in dict:
        ans[dict[key] = key

    return ans

###########
main.py


import sys

from solution import reversal

input_path = sys.argv[1]
with open(input_path, 'r', encoding='utf-8') as f:
    src = eval(f.read())
print(reversal(src))
