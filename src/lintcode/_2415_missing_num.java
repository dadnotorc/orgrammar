package lintcode;

import java.util.Scanner;

/**
 * 2415 · Looking for missing numbers (Java version)
 * Naive
 *
 * Please obtain a positive integer n and an array A from the standard input stream (console).
 * The array A contains a total of n-1 integers, and the range of n-1 integers is in the interval
 * Between [1,n] (no repetition), find the number that does not appear in the array in the range of [1,n],
 * and output the number to the standard through the System.out.println statement Output stream (console).
 *
 * 1 <= n <= 1000
 * 1 <= A[i] <= n
 *
 * Your code needs to read the data n and A from the standard input stream (console)
 * (a total of 2 lines, the second line contains n-1 integers, separated by spaces),
 * calculate Output the result and print it to the standard output stream (console).
 *
 * Example one
 * When n = 3, A[0] = 3, A[1] = 1, it should print 2
 * Explanation: Array = [3 1]
 * In the array of the interval [1,3], the number 2 is missing
 *
 * Example two
 * When n = 5, A[0] = 4, A[1] = 2, A[2] = 3, A[3] = 5, it should be printed 1
 * Explanation: In the array of the interval [1,5], the number 1 is missing
 */
public class _2415_missing_num {

    /**
     * ^ 异或运算
     * 定义：参加运算的两个数据，按二进制位进行“异或”运算。
     * 运算规则：参加运算的两个对象，如果两个相应位相同为 0，相异为 1。
     * 示例：
     * int a = 26;
     * int b = 11;
     * int c = a ^ b;
     * 某个二进制位在 a 和 b 中不同，结果就为 1，否则为 0。所以 26 ^ 11 = 17
     * 26:  0 0 0 1 1 0 1 0
     * 11:  0 0 0 0 1 0 1 1
     * ————————————————————
     * 17:  0 0 0 1 0 0 0 1
     */
    public static void main_2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        /* bug! 这里不能对 n 直接进行 XOR, 因为它是循环的边界
        for (int i = 1; i <= n - 1; i++) {
            n ^= i;
            n ^= sc.nextInt();
        }*/

        int ans = n;
        for (int i = 1; i <= n - 1; i++) {
            ans ^= i;
            ans ^= sc.nextInt();
        }

        System.out.println(ans);
    }



    /**
     * 正数运算, 效率不如位运算
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        while (sc.hasNext()) {
            sum += sc.nextInt(); // 每次读取只会读一个数字, 而不是一整串字符串
        }

        System.out.println((1 + n) * n / 2 - sum);
    }
}
