"""
2454 · N threads to find the number with most factors
Easy
#Thread Pool

This problem requires you to start n threads to find the number with the most factors in the interval [1, m].
If there are multiple numbers with the most factors, find the largest one.

Write the find_num_with_most_factors (findNumWithMostFactors in Java/C++) method of the Solution class file,
which passes in an integer n and an integer m, you need to divide the interval [1, m] into n subintervals,
create a subthread for each subinterval and call find_num_with_most_factors_concurrently.py (in Java/C++
FindNumWithMostFactorsConcurrently.java) file in the search_range class (searchRange in Java/C++)  of the
FindNumWithMostFactorsConcurrently file.

This method finds the number of factors of the number with the most factors in the subinterval 
(including the first and last) and the number itself. Finally, the results from n threads are combined to
find the number with the most factors in [1, m] and returned.

1 <= m <= 10^4
1 <= n <= 10
1 <= n <= m

Example
We will get the result and print it by calling the find_num_with_most_factors (findNumWithMostFactors in Java/C++)
method under the solution.py (Solution.java in Java, solution.h in C++) file. method to get the result for
this question and print it.
 
When n=1 and m=3, your code should output: 3

When n=2 and m=5, your code should output: 4
"""

from concurrent.futures import ThreadPoolExecutor, wait, ALL_COMPLETED
from find_num_with_most_factors_concurrently import FindNumWithMostFactorsConcurrently

class Solution:
    def find_num_with_most_factors(self, n: int, m: int) -> int:
        """
        @param n: number of threads
        @param m: range from 1 to m
        @return: the number with the most factors
        """
        executor = ThreadPoolExecutor(max_workers=n)
        batch_size = (m - 1) // n + 1
        start = 1
        tasks = []
        while start <= m:
            end = min(start + batch_size - 1, m)
            task = executor.submit(FindNumWithMostFactorsConcurrently.search_range, start, end)
            tasks.append(task)
            start = end + 1

        wait(tasks, return_when=ALL_COMPLETED)
        most_factors, most_factors_num = max(task.result() for task in tasks)

        return most_factors_num
return most_factors_num
