/*
Easy
#Greedy

 */
package lintcode;

/**
 * 188 · Insert five
 *
 * Given a number, insert a 5 at any position of the number to make the number largest after insertion.
 *
 * |a| <= 10^6
 *
 * Example 1:
 * Input:  a = 234
 * Output: 5234
 */
public class _0188_Insertfive {

    // 两种情况
    // - a是正数，那么就将5插入从左开始, 第一个比5小的元素之前 (跳过 >= 5 的值)
    // - a是负数，那么就将5插入从左开始, 第一个比5大的元素之前 (跳过 <= 5 的值)
    public int InsertFive(int a) {
        char[] curNum = String.valueOf(a).toCharArray();
        char[] newNum = new char[curNum.length + 1];
        int index = 0;

        // 在找到该插入位置之前, 直接填入原值
        if (a >= 0) { // a 为正数
            while (index < curNum.length && curNum[index] - '0' >= 5) { // 这里要小心, 是 >= 5. 例如 556, expected 5565, 而不是 5556
                newNum[index] = curNum[index];
                index++;
            }
        } else { // a 为负数
            while (index < curNum.length && curNum[index] - '0' <= 5) { // 这里要小心, 是 <= 5. 例如 -548, expected -5458, 而不是 -5548
                newNum[index] = curNum[index];
                index++;
            }
        }

        newNum[index++] = '5';
        while (index - 1 < curNum.length) {
            newNum[index] = curNum[index - 1];
            index++;
        }

        return Integer.parseInt(String.valueOf(newNum));
    }
}
