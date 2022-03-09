from threading import current_thread, main_thread


class FindNumWithMostFactorsConcurrently:
    call_search_range_count = 0

    @classmethod
    def search_range(cls, start: int, end: int):
        """
        @param start: range start
        @param end: range end
        @return: a pair
        """
        cls.call_search_range_count += 1
        if current_thread() is main_thread():
            raise Exception("You should call search_range in sub thread")

        most_factors, most_factors_num = 0, None
        for num in range(start, end + 1):
            factors = 0
            i = 1
            while i * i <= num:
                if num % i == 0:
                    factors += 1
                    if num // i != i:
                        factors += 1
                i += 1
            if factors >= most_factors:
                most_factors_num = num
                most_factors = factors

        return most_factors, most_factors_num
