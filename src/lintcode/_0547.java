package lintcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 547. Intersection of Two Arrays
 * Easy
 * Facebook, Uber
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * - Each element in the result must be unique.
 * - The order of the results needs to be ascending
 *
 * Example 1:
 * Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2],
 * Output: [2].
 *
 * Example 2:
 * Input: nums1 = [1, 2], nums2 = [2],
 * Output: [2].
 *
 * Challenge
 * Can you implement it in three different algorithms?
 */
public class _0547 {

    // sort and binary search
    // time complexity:  O(nlogn) + O(n) + O(n), sorting + 1st loop + 2nd loop
    // space complexity: O(n), n = Math.min(nums1.length, nums2.length)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);

        HashSet<Integer> set = new HashSet<>();

        for (int b : nums2) {
            if (!set.contains(b)) {
                if (binarySearch(nums1, b)) {
                    set.add(b);
                }
            }
        }

        int[] ans = new int[set.size()];
        int index = 0;
        for (int num : set) {
            ans[index++] = num;
        }

        return ans;
    }

    public boolean binarySearch(int[] arr, int b) {
        int l = 0, r = arr.length - 1;

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == b) {
                return true;
            } else if (arr[mid] < b) {
                l = mid;
            } else {
                r = mid;
            }
        }

        if (arr[l] == b) {
            return true;
        }
        if (arr[r] == b) {
            return true;
        }

        return false;
    }

    // sort and merge - 1
    public int[] intersection1(int[] nums1, int[] nums2) {
        // write your code here
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        HashSet<Integer> set = new HashSet<>(); // avoid duplicate
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (!set.contains(nums1[i])) {
                    set.add(nums1[i]);
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        Iterator<Integer> it = set.iterator();
        int[] ans = new int[set.size()];
        int index = 0;
        while (it.hasNext()) {
            ans[index++] = it.next();
        }

        return ans;
    }

    // sort and merge - 2
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        int[] tmp = new int[Math.min(nums1.length, nums2.length)];
        int tmpIndex = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (tmpIndex == 0 || tmp[tmpIndex - 1] != nums1[i]) {
                    tmp[tmpIndex++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] ans = new int[tmpIndex];
        for (int index = 0; index < tmpIndex; index++) {
            ans[index] = tmp[index];
        }

        return ans;
    }

    // hash map
    public int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> set1 = new HashSet<>();
        for (int a : nums1) {
            if (!set1.contains(a)) {
                set1.add(a);
            }
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int b : nums2) {
            if (set1.contains(b) && !set2.contains(b)) {
                set2.add(b);
            }
        }

        int[] ans = new int[set2.size()];
        int index = 0;
        for (Integer i : set2) {
            ans[index++] = i;
        }

        return ans;
    }


}
