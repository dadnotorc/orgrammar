package lintcode.binarysearch;

import util.SVNRepo;

/**
 * 74. First Bad Version
 * Medium
 * Facebook
 *
 * The code base version is an integer start from 1 to n. One day, someone
 *  committed a bad version in the code case, so it caused this version
 *  and the following versions are all failed in the unit tests.
 *  Find the first bad version.
 *
 * You can call isBadVersion to help you determine which version is the first
 *  bad one. The details interface can be found in the code's annotation part.
 *
 * Please read the annotation in code area to get the correct way to call
 *  isBadVersion in different language.
 * For example, Java is SVNRepo.isBadVersion(v)
 *
 * Example
 * Given n = 5:
 *     isBadVersion(3) -> false
 *     isBadVersion(5) -> true
 *     isBadVersion(4) -> true
 * Here we are 100% sure that the 4th version is the first bad version.
 *
 * Challenge
 * You should call isBadVersion as few as possible.
 */
public class _0074_FirstBadVersion {

    /**
     * public class SVNRepo {
     *     public static boolean isBadVersion(int k);
     * }
     * you can use SVNRepo.isBadVersion(k) to judge whether
     * the kth code version is bad or not.
     *
     * 左边为false, 右边为true
     */
    public int findFirstBadVersion(int n) {

        int l = 1, r = n;
        while (l + 1 < r) {
            int mid = l + (r -l) / 2;
            if (SVNRepo.isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        if (SVNRepo.isBadVersion(l) == true) {
            return l;
        } else {
            return r;
        }
    }
}
