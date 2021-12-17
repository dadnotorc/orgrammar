/*
Naive
#Sort

 */
package lintcode;

/**
 * 463 · Sort Integers
 *
 * Given an integer array, sort it in ascending order.
 * Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.
 *
 * Example 1:
 * 	Input:  [3, 2, 1, 4, 5]
 * 	Output: [1, 2, 3, 4, 5]
 *
 * Example 2:
 * 	Input:  [1, 1, 2, 1, 1]
 * 	Output: [1, 1, 1, 1, 2]
 */
public class _0463_SortIntegers {

    /**
     * selection sort
     * https://www.notion.so/Sorting-O-n-2-7fb92b7995af4d20bf1f4f622984f822#586e94fe75b94513b7ebc62163ac3401
     */
    public void sortIntegers_selection(int[] A) {
        if (A == null || A.length < 2)  { return; }
        int n = A.length;

        for (int i = 0; i < n - 1; i++) { // 无需检查最后一位, 因为当循环到最后时, 已经排序完成
            // 寻找从下标 i 开始到末尾的最小值, 将其值与 i 的值交换
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (A[minIndex] > A[j]) {
                    minIndex = j;
                }
            }

            // 交换值
            if (minIndex != i) {
                int xor = A[minIndex] ^ A[i];
                A[minIndex] ^= xor;
                A[i] ^= xor;
            }
        }
    }

    /**
     * bubble sort, with optional early stop
     * https://www.notion.so/Sorting-O-n-2-7fb92b7995af4d20bf1f4f622984f822#dc73545cef414d76a163a7a1c6f613b9
     */
    public void sortIntegers_bubble(int[] A) {
        if (A == null || A.length < 2)  { return; }
        int n = A.length;

        for (int i = 0; i < n; i++) {
            boolean isSorted = true; // 可略掉
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j] > A[j+1]) {
                    int xor = A[j] ^ A[j + 1];
                    A[j] ^= xor;
                    A[j + 1] ^= xor;
                    isSorted = false; // 可略掉
                }
            }
            if (isSorted) { // 可略掉
                return;
            }
        }
    }


    /**
     * insertion sort
     * https://www.notion.so/Sorting-O-n-2-7fb92b7995af4d20bf1f4f622984f822#31c60130040b48069f220418c35b0501
     */
    public void sortIntegers_insertion(int[] A) {
        if (A == null || A.length < 2)  { return; }
        int n = A.length;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] > A[j+1]) { // 这里 A[j+1] = A[i] 就是无排序组的第一位, 一直往前 swap
                    int xor = A[j] ^ A[j + 1];
                    A[j] ^= xor;
                    A[j + 1] ^= xor;
                } else {
                    break;
                }
            }
        }
    }


    // 减少 swap 次数
    public void sortIntegers_insertion_2(int[] A) {
        if (A == null || A.length < 2)  { return; }
        int n = A.length;

        for (int i = 1; i < n; i++) {
            int numToInsert = A[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (A[j] > numToInsert) {
                    A[j + 1] = A[j];
                } else {
                    break;
                }
            }
            A[j + 1] = numToInsert;
        }
    }
}
