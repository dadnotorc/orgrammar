/*
Easy
#Binary Search
 */
package leetcode;

import util.SVNRepo;

/**
 * 278. First Bad Version
 *
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed
 * based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Example:
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 */
public class _0278_FirstBadVersion extends SVNRepo {

    /* The isBadVersion API is defined in the parent class SVNRepo.
      boolean isBadVersion(int version); */

    public int firstBadVersion_2(int n) {
        int l = 1, r = n;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) {
                r = m; //注意这里不能 -1, 因为m可能是最终答案, m-1就变成false, 此时将无法找回m
            } else {
                l = m + 1; // 这里可以 +1, 因为m还是false, 肯定不会是最终答案
            }
        }

        // while循环最后一次, 即当l与r相邻时, m=l
        // - 当m为true时, r = m = l
        // - 当m为false时, l = m+1 = r
        return l; // 也可以return r
    }

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) {
                r = m;
            } else {
                l = m;
            }
        }

        if (isBadVersion(l))
            return l;
        else
            return r;
    }
}
