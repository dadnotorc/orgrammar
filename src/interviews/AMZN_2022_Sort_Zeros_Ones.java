package interviews;

import org.junit.Test;

/**
 * Given an array containing only 0 and 1 as its elements. We have to sort the array in such a manner that
 * all the ones are grouped together and all the zeros are grouped together. The group of ones can be either
 * at the start of the array or at the end of the array.
 *
 * The constraint while sorting is that every one/zero can be swapped only with its adjacent zero/one.
 * Find the minimum number of moves to sort the array as per the description.
 *
 * Example:
 * input array ={0,1,0,1}
 * Final array = {0,0,1,1}
 * Minimum moves = 1 (1 at index 1 is swapped with 0 at index 2)
 *
 * input array = {1,1,0,1}
 * Final array - {1,1,1,0}
 * Minimum moves = 1 {1 at index 2 is swapped with index 3}
 */
public class AMZN_2022_Sort_Zeros_Ones {

    /**
     * 两次遍历 - 考虑将 0 放在前面 VS 将 1 放在前面, 取较少者
     * 1. 统计并加和 每个 0 之前有几位 1, 例如 [1,0,1,1,0,1,0,1] -> 1 + 3 + 4 = 8
     * 2. 统计并加和 每个 1 之前有几位 0, 例如 [1,0,1,1,0,1,0,1] -> 0 + 1 + 1 + 2 + 3 = 7
     *
     * 这个做法的意思是:
     * 如果要把 0 放在前, 那么考虑每个 0 前有多少个 1, 也就等于需要 swap 多少次才能把当前的 0 放在 所有 1 之前
     */
    public int minSwap(int[] nums) {
        if (nums == null || nums.length < 2) { return 0; }

        int swapZeros = 0, countOnes = 0; // 把 0 往前移动
        int swapOnes = 0, countZeros = 0; // 把 1 往前移动

        for (int i : nums) {
            if (i == 0) {
                swapZeros += countOnes;
                countZeros++;
            } else {
                swapOnes += countZeros;
                countOnes++;
            }
        }

        return Math.min(swapZeros, swapOnes);
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1,0,1,1,0,1,0,1};
        org.junit.Assert.assertEquals(7, minSwap(nums));

        nums = new int[] {0,1,0,1};
        org.junit.Assert.assertEquals(1, minSwap(nums));

        nums = new int[] {1,1,0,1};
        org.junit.Assert.assertEquals(1, minSwap(nums));

        nums = new int[] {1,1,1,0};
        org.junit.Assert.assertEquals(0, minSwap(nums));

        nums = new int[] {1,1,1,1};
        org.junit.Assert.assertEquals(0, minSwap(nums));
    }
}
