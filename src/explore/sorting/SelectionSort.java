package explore.sorting;

import org.junit.Test;

public class SelectionSort {

    /*
    选择排序 - 类似插入排序, 数组左右分成已排序和未排序区间. 每次在未排序区间中找到最小值, 将其与未排序区间的第一位互换

    时间复杂度: O(n ^ 2)
    空间复杂度: O(1)
    不稳定的排序算法 - 大小相同的元素, 位置可能会有变化
     */
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            // 找出从 i 到 末尾的最小值
            int minIndex = findMinIndex(array, i);
            if (i != minIndex) {
                swap(array, i, minIndex);
            }
        }
    }

    public int findMinIndex(int[] array, int i) {
        int minIndex = i;
        for (int j = i; j < array.length; j++) {
            if (array[minIndex] > array[j]) {
                minIndex = j;
            }
        }
        return minIndex;
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
}
