package explore.sorting;

import org.junit.Test;

public class MergeSort {

    /*
    归并排序 - 将数组拆分为左右两半, 并分别对其进行归并排序 (递归), 最后将已排好序的左右两半已有序的方式合并

    时间复杂度: O(nlogn)
    空间复杂度: O(n) - tmp 数组
    稳定的排序算法 - 在merge的部分, 保证大小相同的元素, 位置不会改变
     */
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        helper(array, 0, array.length - 1);
    }

    public void helper(int[] array, int l, int r) {
        // 递归出口
        if (l >= r) {
            return;
        }

        // 取得中间位置
        int m = l + (r - l) / 2;

        // 分治递归
        helper(array, l, m);
        helper(array, m + 1, r);

        // 合并
        merge(array, l, m, r);
    }

    public void merge(int[] array, int l, int m, int r) {
        int i = l, j = m + 1; // i 和 j 分别为两个子数组的首位
        int k = 0; // k 为 tmp 数组的指针
        int[] tmp = new int[r - l + 1]; // 临时数组, 长度与array[l...r]相同

        while (i <= m && j <= r) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        // 找出仍未加完的子数组
        // 两个 while 只有一个会执行
        while (i <= m) {
            tmp[k++] = array[i++];
        }
        while (j <= r) {
            tmp[k++] = array[j++];
        }

        // tmp 已排好序, 将其中的值写回 array[p...r]
        for (i = 0; i < k; i++) { // k 最终的值为 r - l + 1. 所以这里用 i < k, 而不是 i <= k
            array[l + i] = tmp[i];
        }
    }

    @Test
    public void test1() {
        int[] array = {3,5,4,1,2,6};
        int[] expect = {1,2,3,4,5,6};
        sort(array);
        org.junit.Assert.assertArrayEquals(array, expect);
    }
}
