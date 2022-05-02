package lintcode;

/**
 * 6 · Merge Two Sorted Arrays
 * Easy
 * #Two Pointers
 *
 * Merge two given sorted ascending integer array A and B into a new sorted integer array.
 *
 * Example 1:
 * Input: A = [1], B = [1]
 * Output: [1,1]
 *
 * Example 2:
 * Input: A = [1,2,3,4], B = [2,4,5,6]
 * Output: [1,2,2,3,4,4,5,6]
 *
 * Challenge
 * How can you optimize your algorithm if one array is very large and the other is very small?
 */
public class _0006_Merge_Two_Sorted_Arrays {

    /*
    如果用 list 存储, 最后转成 array 的话, 要注意
    - 不能使用 Foo[] array = list.toArray(new Foo[0]);
      因为:
      this works only for arrays of reference types.

    - For arrays of primitive types, use the traditional way:
      List<Integer> list = ...;
      int[] array = new int[list.size()];
      for(int i = 0; i < list.size(); i++) { array[i] = list.get(i); }
     */


    /**
     * 双指针 分别指向两个数组
     * 1. 同时遍历两个数组, 把较小值加入答案中. 记得移动指针
     * 2. 前一循环结束后, 可能还有某一数组未被读完, 则将其加入答案中
     */
    public int[] mergeSortedArray_two_pointers(int[] A, int[] B) {
        if (A == null || A.length == 0 ) { return B; }
        if (B == null || B.length == 0 ) { return A; }

        int a_len = A.length, b_len = B.length;
        int a_index = 0, b_index = 0, ans_index = 0;
        int[] ans = new int[a_len + b_len];

        while (a_index < a_len && b_index < b_len) {
            if (A[a_index] < B[b_index]) {
                ans[ans_index++] = A[a_index++];
            } else {
                ans[ans_index++] = B[b_index++];
            }
        }

        while (a_index < a_len) {
            ans[ans_index++] = A[a_index++];
        }
        while (b_index < b_len) {
            ans[ans_index++] = B[b_index++];
        }

        return ans;
    }


    /**
     * 找出两个数组中的较短者, 依次读取其每个值, 然后通过 binary search, 找出它在另一数组中的对应位置
     * * 使用 binary search 找到
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        if (A == null || A.length == 0 ) { return B; }
        if (B == null || B.length == 0 ) { return A; }

        int a_len = A.length, b_len = B.length;
        int[] ans = new int[a_len + b_len];

        // 优化 - 遍历短数组 (因为其较少)
        // 对短数组中所有数字, 在长数组中使用 binary search 查找下标位置
        int[] longer, shorter;
        if (a_len > b_len) {
            longer = A;
            shorter = B;
        } else {
            longer = B;
            shorter = A;
        }

        int i = 0, index = 0; // i - 长数组的指针, index - 答案数组的指针

        for (int num : shorter) {
            int position = binarySearch(longer,num);
            while (i < position) {
                ans[index++] = longer[i++];
            }
            ans[index++] = num;
        }

        while (i < longer.length){ // 别忘了把剩下的也加上
            ans[index++] = longer[i++];
        }

        return ans;
    }

    // 找出 target 在 array 中对应的位置
    public int binarySearch(int[] array, int target) {
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // 如果 target 较大 - 放在 l 下一位
        // 如果 target 较小 - 放在 l 位 (挤进去)
        // 如果 target 与之相等 - 放在 l 或者 下一位都可以
        return array[l] < target ? l + 1 : l;
    }
}
