package lintcode;

/**
 * 190. Next Permutation II
 * Medium
 * #Permutation, #Array
 * Facebook
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as
 * the lowest possible order (ie, sorted in ascending order).
 *
 * Example 1:
 * Input:1,2,3
 * Output:1,3,2
 *
 * Example 2:
 * Input:3,2,1
 * Output:1,2,3
 *
 * Example 3:
 * Input:1,1,5
 * Output:1,5,1
 *
 * Challenge
 * - The replacement must be in-place, do not allocate extra memory.
 *
 *
 * leetcode 31. Next Permutation
 *
 *
 * 字典序的定义：
 * 在数学中，字典或词典顺序（也称为词汇顺序，字典顺序，字母顺序或词典顺序）是基于字母顺序排列的单词按字母顺序排列的方法。
 *
 * 字典序描述：
 * 设P是1～n的一个全排列:p=p1p2......pn=p1p2......pj-1pjpj+1......pk-1pkpk+1......pn
 * 1）从排列的右端开始，找出第一个比右边数字小的数字的序号j（j从左端开始计算），即 j=max{i|pi<pi+1}
 * 2）在pj的右边的数字中，找出所有比pj大的数中最小的数字pk，即 k=max{i|pi>pj}（右边的数从右至左是递增的，因此k是所有大于pj的数字中序号最大者）
 * 3）对换pj，pk
 * 4）再将pj+1......pk-1pkpk+1......pn倒转得到排列p'=p1p2.....pj-1pjpn.....pk+1pkpk-1.....pj+1，这就是排列p的下一个排列。
 *
 * [5,9,8,4] => [8,9,5,4] - (5 与 8 互换)
 *           => [8,4,5,9] - (9,5,4 反转成 4,5,9)
 */
public class _0190_NextPermutation2 {

    /**
     * 1. 从倒数第二位开始 往左, 找到第一个递减的值j, i.e nums[j] < nums[j+1]
     * 2. 如果找到j (j >= 0), 则从倒数第一位开始 往左, 找到第一个大于j的值k, nums[j] < nums[k], 两者互换
     * 3. 反转从j+1开始到末端的数组, 并返回
     * 4. 如果2不成立, 即数组是单调递减(j < 0), 直接反转整个数组并结束
     */
    public void nextPermutation(int[] nums) {

        // 如果数组为空, 或者只有1位, 直接返回
        if (nums == null || nums.length <= 1)
            return;

        // 在数组中, 从右向左, 找出第一个递减的值 nums[j] < nums[j+1]
        // 也就是说, 跳过所有 nums[j] >= nums[j+1]
        int j = nums.length - 2;
        while (j >= 0 && nums[j] >= nums[j+1])
            j--;

        // 如果数组并非单调递减, (j==0 的情况比如 [5,9,8,4])
        // 此时在数组中, 从右往左, 找出第一位比j大的数字k, nums[j] < nums[k], 并交换
        // 也就是说, 跳过所有 nums[j] >= nums[k]
        // [5,9,8,4] -> j = 0, k = 2 -> 互换得到 [8,9,5,4]
        if (j >= 0) {
            int k = nums.length - 1;
            while (nums[j] >= nums[k]) // 无需判断是否越界, 因为前面已保证k肯定存在
                k--;

            swap(nums, j, k);
        }

        // 如果原数组为单调递减, while 循环结束时 j = -1, 上述if语句不成立, 则无需swap, 并反转整个数组

        // 反转时, 从 j+1 位开始到数组末端, 进行反转 [8,9,5,4] => [8,4,5,9]

        reverse(nums, j + 1, nums.length - 1);
    }

    /**
     * 在nums数组中, 从start位开始到end位结束, 进行反转. 例如:
     * i, i+1, ..., j-1, j => j, j-1, ..., i+1, i
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * 在nums数组中, 将left位和right位数字交换位置
     */
    private void swap(int[]nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
