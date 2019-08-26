package interviews;

import org.junit.Test;

public class FindElementInSortedArray {

    /**
     * Given a sorted array, A, with possibly duplicated elements, find the
     * indices of the first and last occurrences of a target element, x.
     * Return -1 if the target is not found.
     *
     * Example:
     * Input: A = [1,3,3,5,7,8,9,9,9,15], target = 9
     * Output: [6,8]
     *
     * Input: A = [100, 150, 150, 153], target = 150
     * Output: [1,2]
     *
     * Input: A = [1,2,3,4,5,6,10], target = 9
     * Output: [-1, -1]
     *
     * AirBnB
     */
    // 假设是单上升数组
    public int[] getRange(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            return new int[] {-1, -1};

        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        int l = 0, r = arr.length - 1;
        int mid;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (arr[mid] < target) {
                l = mid;
            } else if (arr[mid] > target) {
                r = mid;
            } else {
                //return new int[] {findFirst(arr, target, mid), findLast(arr, target, mid)};
                return new int[] {
                        findIndice(arr, target, l, mid, true),
                        findIndice(arr, target, mid, r, false)
                };
            }
        }

        if (arr[l] == target) {
            ans[0] = l;
            ans[1] = l;
        }
        if (arr[r] == target) {
            ans[0] = (ans[0] == -1 ? r : ans[0]);
            ans[1] = r;
        }

        return ans;
    }

    private int findIndice(int[] arr, int target,
                           int start, int end, boolean searchLeft) {
        int l = start, r = end;
        int mid;

        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            if (arr[mid] < target)
                l = mid;
            else if (arr[mid] > target)
                r = mid;
            else {
                if (searchLeft)
                    r = mid;
                else
                    l = mid;
            }
        }

        if (searchLeft)
            return (arr[l] == target) ? l : r;
        else
            return (arr[r] == target) ? r : l;
    }

    private int findFirst(int[] arr, int target, int index) {
        int i = index - 1;
        while (i >= 0) {
            if (arr[i] != target) {
                return i + 1;
            }
            i--;
        }
        return 0;
    }

    private int findLast(int[] arr, int target, int index) {
        int i = index + 1;
        while (i < arr.length) {
            if (arr[i] != target) {
                return i - 1;
            }
            i++;
        }
        return arr.length - 1;
    }

    @Test
    public void test1() {
        int[] arr = {1,3,3,5,7,8,9,9,9,15};
        int[] exp = {6,8};
        int[] act = getRange(arr, 9);
        org.junit.Assert.assertArrayEquals(exp, act);
    }

    @Test
    public void test2() {
        int[] arr = {100, 150, 150, 153};
        int[] exp = {1,2};
        int[] act = getRange(arr, 150);
        org.junit.Assert.assertArrayEquals(exp, act);
    }
    @Test
    public void test3() {
        int[] arr = {1,2,3,4,5,6,10};
        int[] exp = {-1,-1};
        int[] act = getRange(arr, 9);
        org.junit.Assert.assertArrayEquals(exp, act);
    }
}
