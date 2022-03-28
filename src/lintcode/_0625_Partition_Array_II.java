package lintcode;

/**
 * 625 · Partition Array II
 *
 * Partition an unsorted integer array into three parts:
 * 1. The front part < low
 * 2. The middle part >= low & <= high
 * 3. The tail part > high
 * Return any of the possible solutions.
 *
 * low <= high in all testcases.
 *
 * Example 1:
 * Input: nums = [4,3,4,1,2,3,1,2], low = 2, high = 3
 * Output: [1,1,2,3,2,3,4,4]
 * Explanation:
 * [1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not
 *
 * Example 2:
 * Input: nums = [3,2,1], low = 2, high = 3
 * Output: [1,2,3]
 *
 * Challenge
 * Do it in place.
 * Do it in one pass (one loop).
 */
public class _0625_Partition_Array_II {

    /**
     * 左右指针 l, r 分别指向 首位 与 末位
     * i 指针 从 l 遍历到 r (注意, 不能从 l + 1 到 r - 1, 会出错, 例如 nums = [3,2,1], low = 2, high = 3
     * - 如果 i 位 <  low --> i 位 与 l 位的值互换, l 指针后移, i 指针不变
     * - 如果 i 位 >  high --> i 位 与 r 位的值互换, r 指针前移, i 指针不变
     * - 其他情况, i 指针后移, l 与 r 不变
     */
    public void partition2(int[] nums, int low, int high) {
        int l = 0, r = nums.length - 1;
        int i = 0; // i 从 l 开始, 到 = r 才结束
        while (i <= r) {
            if (nums[i] < low && i > l) {
                swap(nums, i, l);
                l++;
            } else if (nums[i] > high && i < r) {
                swap(nums, i, r);
                r--;
            } else {
                i++;
            }
        }
    }

    private void swap (int[] nums, int i , int j) {
        int xor = nums[i] ^ nums[j];
        nums[i] ^= xor;
        nums[j] ^= xor;
    }

    /*
    这道题与148. 颜色分类非常相似，要求把数组划分为三个部分. 方法同样使用三个指针，
    指针left和right分别指向一二部分和二三部分的边界，指针mid从left到right进行遍历，
    通过交换元素和移动指针来更新两个边界。时间复杂度为O(n)，只需常量空间。

    算法流程
    - 我们建立首尾双指针left和right，left指示第一部分和第二部分的边界，right指示第二部分和第三部分的边界。
    更具体的，left左边（不含left）所有值都< low，
    right右边（不含right）所有值都> high。初始化left为0，right为len(nums)-1。

    第三个指针mid从left起向right移动，边扫描边实时更新两个边界。

    若 nums[mid] > high：交换第 mid个和第 right个元素，并将 right指针左移。

    若 nums[mid] < low ：交换第 mid个和第left个元素，并将left和mid指针都向右移。

    若 nums[mid]在low和high之间 ：将指针mid右移。

    补充一下，当mid与left交换后，mid能够后移，因为此时nums[mid]的值不可能> high；
    但mid与right交换后，mid不能后移，因为nums[mid]可能为< low，后面还需要与left交换。

    复杂度分析
    时间复杂度：由于对长度n的数组进行了一次遍历，时间复杂度为O(n)
    空间复杂度：由于只使用了常数空间，空间复杂度为O(1)
     */
    public void partition2_2(int[] nums, int low, int high) {
        int l = 0, r = nums.length - 1;
        int i = l;
        while (i <= r) {
            if (nums[i] < low && i >= l) { // 注意, 这里必须 i >= l. 例如 nums=[4,3,4,1,2,3,1,2], low = high = 3
                swap(nums, i, l);
                l++;
                i++;
            } else if (nums[i] > high && i < r) { // 也可以不写 i >= l 和 i < r 的边界检查
                swap(nums, i, r);
                r--;
            } else {
                i++;
            }
        }
    }
}
