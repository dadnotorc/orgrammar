package interviews;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A file is partitioned into N sub files. The merger can only merge 2 files
 *  at a time. The time required to merge the 2 files is equal to the sum of
 *  their sizes. The size of this merged file is also equal to the sum of
 *  their sizes. Find the minimum possible time to merge the given N sub files
 *  into a single file
 *
 * Constraints
 * * 2 <= numOfSubFiles <= 10^6
 * * 1 <= files[i] <= 10^6
 * * 0 <= i < numOfSubFiles
 *
 * Example
 * Input: numOfSubFiles = 4; files=[8,4,6,12]
 * Output: 58
 * Explanation:
 * The optimal way to merge the sub files is as follows:
 * Step 1: merge the files of size 4 and 6 (time required is 10). Size of sub
 *  files after merging: [8,10,12]
 * Step 2: merge the files of size 8 and 10 (time required is 18). Size of sub
 *  files after merging: [18,12]
 * Step 3: merge the files of size 18 and 12 (time required is 30).
 * Total time required to merge the file is 10 + 18 + 30 = 58
 *
 * Similar problem: https://leetcode.com/problems/last-stone-weight/
 *
 * Amazon
 */
public class Heap_MergeSubFiles {

    /**
     * 取最小值 - 使用 minheap
     *
     * @param numOfSubFiles the number of the sub files
     * @param files a list containing the sizes of the sub files
     * @return the minimum time required to merge all sub files
     */
    public static int minimumTime(int numOfSubFiles, List<Integer> files) {
        if (files == null) {
            return 0;
        }

        if (files.size() == 1) {
            return files.get(0);
        }

        int ans = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < numOfSubFiles; i++) {
            minHeap.offer(files.get(i));
        }

        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            minHeap.offer(a + b);
            ans += a+b;
        }

        return ans;
    }


    /**
     * 取最大值 - 使用 max heap
     */
    public static int maximumTime(int numOfSubFiles, List<Integer> files) {
        if (files == null) {
            return 0;
        }

        if (files.size() == 1) {
            return files.get(0);
        }

        int ans = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < numOfSubFiles; i++) {
            minHeap.offer(files.get(i));
        }

        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            minHeap.offer(a + b);
            ans += a+b;
        }

        return ans;
    }

    private static class MyComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return y - x;
        }
    }

    public static void main(String[] args) {
        List<Integer> files = Arrays.asList(20,4,8,2);
        int numOfSubFiles = 4;
        int outputMin = minimumTime(numOfSubFiles, files);
        int outputMax = maximumTime(numOfSubFiles, files);
        System.out.println("Min Heap Test 1 is " + (outputMin == 54 ? "passed" : "failed"));
        if (outputMin != 54)
            System.out.println("exp = 54; act = " + outputMin);
        System.out.println("Max Heap Test 1 is " + (outputMax == 94 ? "passed" : "failed"));
        if (outputMax != 94)
            System.out.println("exp = 94; act = " + outputMax);

        files = Arrays.asList(1, 2, 5, 10, 35, 89);
        numOfSubFiles = 6;
        outputMin = minimumTime(numOfSubFiles, files);
        outputMax = maximumTime(numOfSubFiles, files);
        System.out.println("Min Heap Test 2 is " + (outputMin == 224 ? "passed" : "failed"));
        if (outputMin != 224)
            System.out.println("exp = 224; act = " + outputMin);
        System.out.println("Max Heap Test 2 is " + (outputMax == 680 ? "passed" : "failed"));
        if (outputMax != 680)
            System.out.println("exp = 680; act = " + outputMax);

        files = Arrays.asList(2, 2, 3, 3);
        numOfSubFiles = 4;
        outputMin = minimumTime(numOfSubFiles, files);
        outputMax = maximumTime(numOfSubFiles, files);
        System.out.println("Min Heap Test 3 is " + (outputMin == 20 ? "passed" : "failed"));
        if (outputMin != 20)
            System.out.println("exp = 20; act = " + outputMin);
        System.out.println("Max Heap Test 3 is " + (outputMax == 24 ? "passed" : "failed"));
        if (outputMax != 24)
            System.out.println("exp = 24; act = " + outputMax);
    }
}
