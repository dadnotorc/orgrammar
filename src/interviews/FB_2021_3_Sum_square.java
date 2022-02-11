package interviews;

import org.junit.Test;

import java.util.*;

/**
 * 类似 15. 3Sum
 * 从 unsorted array 中, 找出 a, b, c, such as a ^ 2 + b ^ 2 = c ^ 2.
 * 直角三角形, 勾3 股4 弦5 => 3^2 + 4^2 = 5^2
 * 可以有负数
 *
 * 遇到重复的时候, 怎么办, 例如   [-5,-4,-3,3,4,5]
 * 答案有 [-5,-4,-3], [-5,4,-3], [-5,-4,3], [-5,4,3],
 *        [5,-4,-3],  [5,4,-3],  [5,-4,3],  [5,4,3]
 */
public class FB_2021_3_Sum_square {

    /*
    先做平方, 再按照 3 sum 的形式做

    先固定一个点, 然后做 2 sum - 时间 O(n) + O(n^2), 空间 O(1)
     */


    class ResultType{
        int origin_index;
        int val;
        public ResultType (int[] num, int index) {
            this.origin_index = index;
            this.val = num[index] * num[index];
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums == null || nums.length == 0) { return ans; }

        int n = nums.length;

        ResultType[] sqs = new ResultType[n];

        for (int i = 0; i < n; i++) {
            sqs[i] = new ResultType(nums, i);
        }

        Arrays.sort(sqs, new Comparator<ResultType>() {
            @Override
            public int compare(ResultType t1, ResultType t2) {
                return t1.val - t2.val;
            }
        });

        // 排序后, sqs 里将都是非负数, 且从小到大排列
        // 现在寻找 a + b = c;

        for (int i = n - 1; i >= 2; i--) { // 不需要遍历到 0, 至少要剩下两个数
            int target = sqs[i].val;
            int l = 0, r = i - 1;

            // 双指针 (不是 binary search)
            while (l < r) {
                if (sqs[l].val + sqs[r].val == target) {

                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            // 这里 l + j,  r - k, 相向移动

                            if (sqs[l + j].val == sqs[l].val && sqs[r - k].val == sqs[r].val) {
                                // 注意 这里用 Integer, 而不是 int
                                Integer[] cur = {sqs[l + j].origin_index, sqs[r - k].origin_index, sqs[i].origin_index};
                                Arrays.sort(cur);
                                ans.add(Arrays.asList(cur));
                            }
                        }
                    }

                    l += sqs[l + 1].val == sqs[l].val ? 2 : 1;
                    r -= sqs[r - 1].val == sqs[r].val ? 2 : 1;

                } else if (sqs[l].val + sqs[r].val < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return ans;
    }


    @Test
    public void test1() {
        int[] nums = {-5,-4,-3,3,4,5};
        List<List<Integer>> exp = new ArrayList<>();
        exp.add(Arrays.asList(-5,-4,-3));
        exp.add(Arrays.asList(-5,-4,3));
        exp.add(Arrays.asList(-5,4,-3));
        exp.add(Arrays.asList(-5,4,3));
        exp.add(Arrays.asList(5,-4,-3));
        exp.add(Arrays.asList(5,-4,3));
        exp.add(Arrays.asList(5,4,-3));
        exp.add(Arrays.asList(5,4,3));

        List<List<Integer>> actual = threeSum(nums);
    }
}
