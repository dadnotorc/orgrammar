/*
Easy
#Math

 */
package lintcode;

/**
 * Given an integer r which pretends the radius of a circle.
 * Your task is return an array.
 * The first element of this array represents the circumference of this circle.
 * The second element of this array represents the area of this circle.
 * - PI = 3.14
 *
 * Example 1:
 * Input : r = 2
 * Output : [12.56, 12.56]
 */
public class _0764_CalculateCircumferenceArea {

    public double[] calculate(int r) {
        double[] res = new double[2];
        res[0] = 2 * 3.14 * r;
        res[1] = 3.14 * r * r;
        return res;
    }
}
