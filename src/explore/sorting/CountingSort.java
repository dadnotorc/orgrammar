package explore.sorting;

import org.junit.Test;

public class CountingSort {

    /**
     * 假设:
     * 1. 数组中所有元素均为非负数 >= 0
     *
     */
    public int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        int n = array.length;
        int[] ret = new int[n];

        // 先找出最大值
        int max = findMax(array);

        // 先统计数组中所有数值出现的次数
        int[] count = new int[max + 1]; // 从 0 到 max
        for (int num : array) {
            count[num]++;
        }

        // 在count数值中就前缀和计算. 记录原数组中 <= count当前下标 的数值个数
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // 进行排序
        for (int j = n - 1; j >= 0; j--) { // 从后往前放入数组, 保持先后顺序
            int numToPut = array[j];
            int index= count[numToPut] - 1;
            ret[index] = numToPut;
            count[numToPut]--;
        }

        return ret;
    }


    public int findMax(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }

        return max;
    }

    @Test
    public void test1() {
        int[] array = {3,5,4,1,2,6};
        int[] expect = {1,2,3,4,5,6};
        int[] output = sort(array);
        org.junit.Assert.assertArrayEquals(output, expect);
    }
}
