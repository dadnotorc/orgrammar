def composition(dict_1: dict) -> list:
    '''
    :param dict_1: Input dictionary
    :return: A new list of keys and values in the dictionary
    '''

    result = []
    for k, v in dict_1.items():
        result.append(k)
        result.append(v)
    return result

    # return [key if j ^ 1 else value for key, value in dict_1.items() for j in (0, 1)]