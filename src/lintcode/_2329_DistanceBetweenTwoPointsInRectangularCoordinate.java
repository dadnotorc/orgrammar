/*
Naive
#Math
 */
package lintcode;

/**
 * 2329 · Finding The Distance Between Two Points In Rectangular Coordinate System (Java version)
 *
 * Please get four positive integers x1, y1, x2, y2 from the standard input stream (console),
 * which represent the coordinates of two points (x1, y1)，(x2, y2).
 * You need to calculate the distance between the two points (keep two decimal places),
 * and you need to use the System.out.printf statement to output the distance.
 *
 * 1 <= x1, y1, x2, y2 <= 10
 *
 * Example
 * The evaluation opportunity will compile the code of the entire project into an executable Main program,
 * and execute your code Main in this way. Your code needs to read data x1, y1, x2, y2 from the standard input stream (console),
 * each parameter is on a separate line, calculate the result and print it to the standard output stream (console).
 *
 * Example 1
 * x1 = 1, y1 = 3, x2 = 5, y2 = 4
 * output: 4.12
 * Explanation: If the third decimal place of the square root of 17 in the calculation result is 3,
 * it will be 4.12 after retaining two decimal places
 *
 * Example 2
 * x1 = 4, y1 = 3, x2 = 7, y2 = 5
 * output : 3.61
 * Explanation: The square root of 13 in the calculation result, the third decimal place is 5,
 * so you need to add one place to the second decimal place, then keep two decimal places and it is 3.61
 */

import java.util.Scanner;

public class _2329_DistanceBetweenTwoPointsInRectangularCoordinate {

    /*
    算是计算方式
    Math.sqrt() : 计算平方根
    Math.cbrt() : 计算立方根
    Math.pow(a, b) : 计算 a 的 b 次方
    Math.max( , ) : 计算最大值
    Math.min( , ) : 计算最小值
    Math.abs() : 取绝对值

    Math.ceil(): 天花板的意思，就是逢余进一, 例如 -10.1 -> -10,  10.7 -> 11.0
    Math.floor() : 地板的意思，就是逢余舍一, 例如 -10.1 -> -11,  10.7 -> 10.0
    Math.rint(): 四舍五入，返回double值。注意.5的时候会取偶数, 例如 10.1 -> 10.0,  10.5 -> 10.0, 11.5 -> 12.0
    Math.round(): 四舍五入，float时返回int值，double时返回long值, 例如 -10.5 -> -10,  10.7 -> 11

    特殊点注意
    int a = 1300, b = 1000;
    System.out.println(Math.ceil(a / b));  // 1  表达式A(错误使用)
    System.out.println(Math.ceil(a / (float)b));  // 2 表达式B(正确使用)
     */



    /**
     * 使用 Math.sqrt 求平方根
     */
    public static void main(String[] args) {
        // read data from console
        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();

        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        System.out.printf("%.2f\n", distance); // 注意 这个是 printf

        // 或者
        System.out.println(String.format("%.2f", distance));
    }

    /**
     * 使用 Math.pow 求平方根
     */
    public static void main_1(String[] args) {
        // read data from console
        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();

        double distance = Math.pow((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2), 0.5);
        System.out.printf("%.2f\n", distance); // 注意 这个是 printf

        // 或者
        System.out.println(String.format("%.2f", distance));
    }
}
