package explore.sorting;

import org.junit.Test;

public class InsertSort {

    /*
    插入排序 - 数组左边为已排序区间, 右边为未排序区间. 每次将未排序区间第一位, 插入到已排序区间中应当的位置

    时间复杂度: O(n ^ 2)
    空间复杂度: O(1)
    稳定的排序算法 - 大小相同的元素, 不会交换位置
     */
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // 从 0 位到 i 位以前, 为已排序区间;
        // 从 i 位到末尾, 为未排序区间
        for (int i = 1; i < n; i++) { // 从未排序区间的第一位开始, 将其移至已排序区间的相应位置
            for (int j = i - 1; j >= 0; j--) { // 从已排序区间的最后一位开始, 向前寻找插入位置
                if (array[j] > array[j + 1]) { // 只有在 > 的时候才 swap. 等于的时候不变
                    // swap
                    swap(array, j, j + 1);
                } else {
                    break; // 注意, 此处是break, 而不是return. 因为未排序空间可能仍未空
                }
            }
        }
    }

    /*
    优化 - 不要每次swap, 而是将 i 位的值记录. 只有当找到对应的位置时才赋值. 这样的优点是减少赋值的次数
     */
    public void sort_EarlyStop(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        for (int i = 1; i < n; i++) {
            int val = array[i];
            int j = i - 1; // 将 j 从 for 循环中提出来, 因为 for 循环结束后, 我们会到最后的 j + 1 位赋值
            for (; j >= 0; j--) {
                if (array[j] > val) { // 注意, 这里 不能比较 array[j] > array[j + 1]
                    array[j + 1] = array[j]; // 将较大者移至后一位
                } else {
                    break;
                }
            }

            array[j + 1] = val; // 注意, 这里是 j + 1 位, 因为 for 循环的最后一次, 会将 j 再往前一位, 导致循环结束
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
