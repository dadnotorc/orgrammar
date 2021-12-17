/*
Easy
#Sort, #Quick Sort, #Array

 */
package lintcode;

/**
 * 479 · Second Max of Array
 *
 * Find the second max number in a given array.
 *
 * You can assume the array contains at least two numbers.
 * The second max number is the second number in a descending array.
 *
 * Example1:
 * Input: [1,3,2,4]
 * Output: 3
 *
 * Example2:
 * Input: [1,1,2,2]
 * Output: 2
 */
public class _0479_SecondMaxOfArray {

    /**
     * 维护 max 与 secMax 变量
     * O(n)
     */
    public int secondMax_2max(int[] nums) {
        int max = nums[0];
        int secMax = nums[1];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                secMax = max;
                max = nums[i];
            } else if (nums[i] > secMax) {
                secMax = nums[i];
            }
        }

        return secMax;
    }


    /**
     * Sort -> return second last
     * O(nlogn)
     */
    public int secondMax_quicksort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
        return nums[nums.length - 2];
    }

    public void quicksort(int[] nums, int l, int r) {
        if (l >= r) { return; }

        int pivot = partition(nums, l, r);

        quicksort(nums, l, pivot - 1);
        quicksort(nums, pivot + 1, r);
    }

    public int partition(int[] nums, int l, int r) {
        int pivotVal = nums[r];
        int i = l;

        for (int j = l; j < r; j++) {
            if (nums[j] < pivotVal) {
                if (i != j) {
                    swap(nums, i, j);
                }
                i++;
            }
        }

        // 此时, 因为 i 之前已经++, 现在已经指到正确的 pivot 位置. 将其与作为 pivot 值的最右 r 交换
        swap(nums, i, r);
        return i;
    }

    public void swap(int[] nums, int l, int r) {
        int xor = nums[l] ^ nums[r];
        nums[l] ^= xor;
        nums[r] ^= xor;
    }
}
