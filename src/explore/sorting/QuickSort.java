package explore.sorting;

import org.junit.Test;

public class QuickSort {

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }

        // top-down
        // 先找到pivot point, 做partition.
        int pivot = partition(array, l, r);

        // 后做递归. 因为分区后, pivot已在其应当的位置, 递归时不需要考虑它了
        sort(array, l, pivot - 1);
        sort(array, pivot + 1, r);
    }

    public int partition(int[] array, int l, int r) {
        int pivot = array[r]; // 取最后一位作为pivot
        int i = l; // 已整理区的最后一位, 此区中所有值均小于pivot

        for (int j = l; j < r; j++) {
            if (array[j] < pivot) {
                if (i != j) {
                    swap(array, i, j);
                }
                i++;
            }
        }

        // 别忘了这一步. 交换后, i点即为pivot点
        swap(array, i, r);

        return i;
    }

    public void swap(int[] array, int l, int r) {
        int xor = array[l] ^ array[r];
        array[l] = array[l] ^ xor;
        array[r] = array[r] ^ xor;
    }

    @Test
    public void test1() {
        int[] array = {3,5,4,1,2,6};
        int[] expect = {1,2,3,4,5,6};
        sort(array);
        org.junit.Assert.assertArrayEquals(array, expect);
    }
}
