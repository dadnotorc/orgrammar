package leetcode;

/**
 * 1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
 * Medium
 * #Two Pointers, #String, #Greedy
 *
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num
 * and is greater in value than num. There can be many wonderful integers.
 * However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 *
 * Return the minimum number of adjacent digit swaps that needs to be applied to num
 * to reach the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 *
 * Example 1:
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 *
 * Example 2:
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 *
 * Example 3:
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 *
 *
 * Constraints:
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 */
public class _1850_Min_Adjacent_Swaps_Reach_Kth_Smallest_Num {

    /**
     * 1. 找到经过 k 次 permutation 后的新数字
     *    - 把 String 转成 int[], 方便计算
     *    - 每次 permutation 期间
     *         a) 从末尾开始, 找到第一组(即最右) 相邻 且 递减的两个值 - num[i] < num[i + 1]
     *         b) 从末尾开始, 找到第一个(即最右) 大于 i 指针的值     - num[i] < num[j], i < j
     *         c) 可以使用 二分法 优化
     *         d) 从 i 位到 末尾, 进行反转.
     * 2. 统计新老数字之间需要多少步骤来交换
     *    - 双指针, 一个指 原值 src, 一个指 新值 target
     *    - 双指针一起移动, 直到遇到第一个不同的值
     *       - target 指针继续移动, 直到找到与 src 指针相同的值 (需要 swap 回来的值)
     *       - 将 target 数组上, target 指针 与 src 指针 之间的值 全部后移 (表示交换), 同时统计次数
     *       - target 指针向前回移 到 src 指针相同处时, 继续寻找下一个需要 swap 的值
     */
    public int getMinSwaps(String num, int k) {
        if (num == null || num.length() < 2) {
            return 0;
        }

        int[] numArray = toIntArray(num);

        for (int i = 0; i < k; i++) {
            nextPermutation(numArray);
        }

        return getMinSwap(toIntArray(num), numArray);
    }

    public int[] toIntArray(String s) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = s.charAt(i) - '0';
        }
        return ans;
    }

    // 参考 lintcode 190. Next Permutation II
    // [5,9,8,4] => 找到 i 指向 5, j 指向8, swap
    //           => [8,9,5,4] => 将 8 之后的部分反转
    //                        => [8,4,5,9]
    public void nextPermutation(int[] num) {
        int n = num.length;
        int i = n - 2;
        // 从末尾开始往前, 找第一对 相邻 且 递减 的两个数  num[i] < num[i + 1]
        while (i >= 0 && num[i] >= num[i + 1]) {
            i--;
        }

        // 如果找到 i, 那再从末尾往前, 找第一个大于 i 的值  num[i] < num[j], i < j
        if (i >= 0) {
            int j = findLastBiggerNum(num, i); // j 值肯定存在, 因为至少 num[i] < num[i + 1]
            swap(num, i, j);
        } else {
            // 因为题目保证, kth smallest wonderful integer exists
            // 否则, 假设原数组为单调递减, i = -1, 上述if语句不成立
            System.out.println("ERROR: original number is monotonically decreasing"); //
        }

        reverse(num, i + 1, n - 1); // 注意 这里是吧 i 下一位 到末尾 反转
    }

    // 找到最后一位(最右) 大于 i 指针的值
    // 简单解法 - 遍历; 优化 - 二分法 - 这里的二分法写法比较特殊, 要注意!
    public int findLastBiggerNum(int[] num, int i) {
        int j = num.length - 1;

        /* 可用 二分 优化
        while (j > i && num[i] >= num[j]) {
            j--;
        }

        return j;
        */

        int target = num[i];

        // 从 i 下一位开始 - 这里的二分法写法比较特殊, 要注意!
        i++;
        while (i <= j) {
            int mid = i + (j -i) / 2;
            if (target < num[mid]) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return i - 1;
    }

    public void swap(int[] num, int i, int j) {
        int xor = num[i] ^ num[j];
        num[i] ^= xor;
        num[j] ^= xor;
    }

    public void reverse(int[] num, int start, int end) {
        while (start < end) {
            swap(num, start, end);
            start++;
            end--;
        }
    }

    public int getMinSwap(int[] src, int[] target) {
        int n = src.length;

        int step = 0;

        // 左至右, 从第一个不同开始, 统计 swap 计数
        for (int i = 0 ; i < n; i++) { // n -1 ?
            int j = i;

            // 跳过其他不同的值
            while (src[i] != target[j]) {
                j++;
            }

            // 找到相同的值后, 开始统计交换的步数
            // - 笨办法 - 一步一步swap
            // - 优化 - 每个值前移, 最后一步再把原值赋回去

            // int temp = src[i]; // 这一步可省略, 无需将原值赋值回去
            while (i < j) {
                // 要把 target 上 j 所指的值往前移动到 i 的位置
                // 所以 j 之前的值都忘后诺
                target[j] = target[j - 1];
                j--;
                step++;
            }

            // target[i] = temp; // 最后这步是把原值赋回去, 但是可以省略
        }

        return step;
    }
}
