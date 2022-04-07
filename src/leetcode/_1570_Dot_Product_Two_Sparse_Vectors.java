package leetcode;

import java.util.HashMap;

/**
 * 1570. Dot Product of Two Sparse Vectors
 * Medium
 * #Prime
 *
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 * - SparseVector(nums) Initializes the object with the vector nums
 * - dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 *
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector
 * efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 * Example 1:
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 *
 * Example 2:
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 *
 * Example 3:
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class _1570_Dot_Product_Two_Sparse_Vectors {

    /*
    how to store sparse vectors efficiently
    使用 hashmap 记录 <非0数字的下标, 该数字>, 方便查找 / 修改 / 删除

    当只有一个 vectors is sparse 的时候, 说明其 hashmap 的 size较小
    计算时, 用遍历较小者, 能减少时间
     */


    class SparseVector{

        // <key = index, value = num value>  不能反过来, 因为 value 可能会有重复
        private HashMap<Integer, Integer> num_index_map = new HashMap<>();

        public SparseVector(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    this.num_index_map.put(i, nums[i]);
                }
            }
        }

        public long dotProduct(SparseVector vec) {
            // 遍历较短的
            if (this.num_index_map.size() < vec.num_index_map.size()) {
                int ans = 0;
                for (int index : this.num_index_map.keySet()) {
                    if (vec.num_index_map.containsKey(index)) {
                        ans += this.num_index_map.get(index) * vec.num_index_map.get(index);
                    }
                }
                return ans;

            } else {
                return vec.dotProduct(this);
            }
        }
    }
}
