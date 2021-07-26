package explore.sorting;

import org.junit.Test;

public class BubbleSort {

    /*
    冒泡排序 - 每次对比相邻的两个数据, 将较大者放在后面. 每次冒泡过程, 就会将一个元素移动到它应在的位置上 (从后往前)

    时间复杂度: O(n ^ 2)
    空间复杂度: O(1)
    稳定的排序算法 - 大小相同的元素, 不会交换位置
     */
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // 有 n 个数字, 就做 n 次冒泡.
        // 第 i 次冒泡结束后, 倒数第 i 个数字就已被排好序. 例如第一次冒泡结束, 倒数第一个数字就被排好序
        // i 从 1 开始到 n, 方便理解
        for (int i = 1; i <= n; i++) {

            // 在第 i 次冒泡, 从 0 位开始遍历到到 n - i 的前一位. i 从 1 开始
            // 注意: 到前一位结束, 因为我们会对比 (array[j], array[j + 1])
            for (int j = 0; j < n - i; j++) {
                if (array[j] > array[j + 1]) {
                    // swap
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /*
    优化 - 当某次冒泡中, 未作任何swap, 既排序已完成
     */
    public void sort_EarlyStop(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        for (int i = 1; i <= n; i++) {

            boolean isSorted = true; // 提前结束的flag

            for (int j = 0; j < n - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false; // 做了swap, 表示排序未完成, 不能提前结束
                }
            }

            if (isSorted) {
                return;
            }
        }
    }

    public void swap(int[] array, int i, int j) {
        int xor = array[i] ^ array[j];

        array[i] = array[i] ^ xor;
        array[j] = array[j] ^ xor;
    }

    @Test
    public void test1() {
        int[] array = {3,5,4,1,2,6};
        int[] expect = {1,2,3,4,5,6};
        sort(array);
        org.junit.Assert.assertArrayEquals(array, expect);
    }

    @Test
    public void test2() {
        int[] array = {3,5,4,1,2,6};
        int[] expect = {1,2,3,4,5,6};
        sort_EarlyStop(array);
        org.junit.Assert.assertArrayEquals(array, expect);
    }
}
