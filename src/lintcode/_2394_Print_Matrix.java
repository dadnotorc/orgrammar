package lintcode;

import java.util.Scanner;

/**
 * 2394 · Print matrix (Java version)
 * Naive
 *
 * Given a positive integer n, with n as the side length, please output the sequence number
 * from left to right and top to bottom through the output statement to form a square matrix,
 * the sequence number starts from 1, and the two sequence numbers are separated by a space ,
 * No spaces are added at the end of each line.
 * - 0 <= n <= 10
 * - No spaces at the end of the line
 * - No line break at the end
 *
 * Example
 * The evaluation opportunity will compile the code of the entire project into an executable Main program,
 * and execute your code Main in this way. Your code needs to read data n from the standard input stream (console),
 * calculate the result and print it to the standard output stream (console).
 *
 * Example 1
 * When n = 3, the printed result of the program execution is:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * Example 2
 * When n = 5, the printed result of the program execution is:
 * 1 2 3 4 5
 * 6 7 8 9 10
 * 11 12 13 14 15
 * 16 17 18 19 20
 * 21 22 23 24 25
 */

public class _2394_Print_Matrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            int n = input.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(i * n + j + 1).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            sb.setLength(0);
        }
    }
}
