import functools

def func():
    int_2 = functools.partial(int, base=2)
    return int_2