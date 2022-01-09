/*
Medium
Quick Sort, Sort
Facebook, Google
 */
package lintcode;

/**
 * 5. Kth Largest Element
 *
 * Find K-th largest element in an array.
 * - You can swap elements in the array
 * - 1 <= k <= 10^5
 *
 * Example 1:
 * Input:  n = 1, nums = [1,3,4,2]
 * Output: 4
 *
 * Example 2:
 * Input:  n = 3, nums = [9,3,2,4,8]
 * Output: 4
 *
 * Challenge
 * - O(n) time, O(1) extra memory.
 */
public class _0005_KthLargestElement {

    /*
    最直接的办法是直接排序, 然后返回第 k 大的值. 时间复杂度 O(nlogn)
     */


    /**
     * quick sort 快排的变体 - 通过快排的 partition 步骤, 将 < pivot 的值划分到 pivot 左边, > pivot 的值放在 pivot 右边
     * 从而直接得到 pivot 的 rank.
     */
    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return Integer.MIN_VALUE;
        }

        k = nums.length - k; // 为了方便写, 将 k 大转成 k 小问题
        return partition(nums, 0, nums.length - 1, k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
        int i = start;

        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                if (i != j) {
                    swap(nums, i, j);
                }
                i++;
            }
        }

        swap(nums, i, end); // 别忘了最后这步 swap, 交换后 i 点为 pivot 点

        if (k < i) { // k 小 在 i 左侧
            return partition(nums, start, i - 1, k);
        }
        if (k > i) { // k 小 在 i 右侧
            return partition(nums, i + 1, end, k);
        }

        // k 正好等于 i, 找到了
        return nums[k];
    }

    public void swap(int[] array, int i, int j) {
        int xor = array[i] ^ array[j];
        array[i] = array[i] ^ xor;
        array[j] = array[j] ^ xor;
    }
}
