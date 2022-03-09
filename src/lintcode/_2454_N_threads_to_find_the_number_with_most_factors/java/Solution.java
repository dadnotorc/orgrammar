package lintcode._2454_N_threads_to_find_the_number_with_most_factors.java;

/**
 * 2454 · N threads to find the number with most factors
 * Easy
 * #Thread Pool
 * 
 * This problem requires you to start n threads to find the number with the most factors in the interval [1, m].
 * If there are multiple numbers with the most factors, find the largest one.
 * 
 * Write the find_num_with_most_factors (findNumWithMostFactors in Java/C++) method of the Solution class file,
 * which passes in an integer n and an integer m, you need to divide the interval [1, m] into n subintervals,
 * create a subthread for each subinterval and call find_num_with_most_factors_concurrently.py (in Java/C++
 * FindNumWithMostFactorsConcurrently.java) file in the search_range class (searchRange in Java/C++)  of the
 * FindNumWithMostFactorsConcurrently file.
 * 
 * This method finds the number of factors of the number with the most factors in the subinterval 
 * (including the first and last) and the number itself. Finally, the results from n threads are combined to
 * find the number with the most factors in [1, m] and returned.
 * 
 * 1 <= m <= 10^4
 * 1 <= n <= 10
 * 1 <= n <= m
 * 
 * Example
 * We will get the result and print it by calling the find_num_with_most_factors (findNumWithMostFactors in Java/C++)
 * method under the solution.py (Solution.java in Java, solution.h in C++) file. method to get the result for
 * this question and print it.
 * 
 * When n=1 and m=3, your code should output: 3
 *
 * When n=2 and m=5, your code should output: 4
 */
public class Solution {
    /*
    1. 计算 n 个线程各自需要处理的计算因子个数
    2. 创建子线程计算各自的拥有因子最多的数，并记录
    3. 最后从 n 个子线程的数中，取一个因子最多的数并返回。
     */
    public int findNumWithMostFactors(int n, int m) throws Exception {
        int[] ans = new int[2]; // [most factor, res]

        int subinterval_size = (m - 1) / n;
        int start = 1; // 题目要求的 interval 是 [1, m], 所以从 1 开始

        for (int i = 1; i <= n; i++) { // n 个 subthreads, [1, n]
            int end = Math.min(m, start + subinterval_size); // 最后一组, 长度可能不够, 所以要调整 end

            InnerThread1 innerThread1 = new InnerThread1(start, end);

            innerThread1.start();
            innerThread1.join();

            Integer[] curResult = innerThread1.getResult();
            // System.out.println("start =" + start + ", end = " + end);
            // System.out.println("cur = [" + curResult[0] + ", " + curResult[1] + "]");

            if (curResult[0] >= ans[0]) { // 注意! 这里要用 >=, 而不是 >
                ans[0] = curResult[0];
                ans[1] = curResult[1];
            }

            start = end + 1; // 别忘了更新 start
        }


        return ans[1];
    }


    class InnerThread1 extends Thread {
        FindNumWithMostFactorsConcurrently mFactors;

        private int start, end;
        private Integer[] result;

        public Integer[] getResult() { return this.result; }

        InnerThread1 () { super(); }

        InnerThread1 (int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            if (mFactors == null) {
                mFactors = new FindNumWithMostFactorsConcurrently();
            }

            try {
                result = FindNumWithMostFactorsConcurrently.searchRange(start, end);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}