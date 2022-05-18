package lintcode;

import org.junit.Test;

/**
 * 1745. Monotonic Array
 * Easy
 * Array
 * Facebook
 *
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].
 * An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 *
 * Return true if and only if the given array A is monotonic.
 *
 * Notice
 * - 1 ≤ A.length ≤ 50000
 * - −100000 ≤ A[i] ≤ 100000
 *
 * Example 1:
 * Input: [1,2,2,3]
 * Output: true
 *
 * Example 2:
 * Input: [1,3,2]
 * Output: false
 */
public class _1745_Monotonic_Array {

    /* 解法2 - 九章参考 */
    // 遍历一次, 判断是否有递增或者递减的单调性, 满足其中一项即可
    // 写法较简单, 但是运行时间较长, 因为每组对比要完成两次
    public boolean isMonotonic_jiuzhang(int[] a) {

        // 初始值均为正, 因为之后 for 循环里, 每次都是 && 操作
        boolean isIncreasing = true, isDecreasing = true;

        for (int i = 1; i < a.length; i++) {
            isIncreasing &= a[i - 1] <= a[i];
            isDecreasing &= a[i - 1] >= a[i];
        }
        return isIncreasing || isDecreasing;
    }


    /**
     * 另一种写法, 写法比较复杂
     */
    public boolean isMonotonic(int[] a) {
        if (a != null && a.length <= 1)
            return true;

        int curIndex = 1;
        // 跳过开头所以相同的数字
        // 循环到达倒数第二位时停止
        while (curIndex + 1 < a.length) {
            if (a[curIndex - 1] != a[curIndex])
                break;

            curIndex++;
        }

        // 遇到不相同数字, 判断单调性
        boolean isIncreasing = (a[curIndex - 1] < a[curIndex++]);

        // 判断后续数字中是否有破坏单调性的存在
        for (int i = curIndex; i < a.length; i++) {
            if ((isIncreasing && (a[i - 1] > a[i]))
                    || (!isIncreasing && (a[i - 1] < a[i]))) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test1() {
        org.junit.Assert.assertTrue(isMonotonic(new int[] {1,2,2,3}));
        org.junit.Assert.assertTrue(isMonotonic_jiuzhang(new int[] {1,2,2,3}));
    }

    @Test
    public void test2() {
        org.junit.Assert.assertFalse(isMonotonic(new int[] {1,3,2}));
        org.junit.Assert.assertFalse(isMonotonic_jiuzhang(new int[] {1,3,2}));
    }

    @Test
    public void test3() {
        org.junit.Assert.assertTrue(isMonotonic(new int[] {1,1,1,2,2,3,3}));
        org.junit.Assert.assertTrue(isMonotonic_jiuzhang(new int[] {1,1,1,2,2,3,3}));
    }

    @Test
    public void test4() {
        org.junit.Assert.assertFalse(isMonotonic(new int[] {2,2,2,1,1,3}));
        org.junit.Assert.assertFalse(isMonotonic_jiuzhang(new int[] {2,2,2,1,1,3}));
    }

    @Test
    public void test5() {
        org.junit.Assert.assertTrue(isMonotonic(new int[] {1,1,1}));
        org.junit.Assert.assertTrue(isMonotonic_jiuzhang(new int[] {1,1,1}));
    }

    @Test
    public void test6() {
        org.junit.Assert.assertTrue(isMonotonic(new int[] {1}));
        org.junit.Assert.assertTrue(isMonotonic_jiuzhang(new int[] {1}));
    }
}
