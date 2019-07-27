package lintcode.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 14. First Position of Target
 *
 * For a given sorted array (ascending order) and a target number, 
 *  find the first index of this number in O(log n) time complexity.
 * If the target number does not exist in the array, return -1.
 * 
 * Challenge:
 * If the count of numbers is bigger than 2^32, can your code work properly?
 *
 * time complexity:  O(log n)
 * space complexity: O(1)
 */
public class _0014_FirstPosition {

	/**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return : The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int l = 0;
    	int r = nums.length - 1;
    	int mid;
    	
    	while (l < r) {
    		// use following instead of mid = (l + r) / 2, because 
    		// if l = 2 ^ 31 and r = 2 ^ 31, the latter will cause overflow
    		mid = l + (r - l) / 2;
    		if (nums[mid] == target) {
    			r = mid; // keep searching for more
    		} else if (nums[mid] < target) {
    			l = mid + 1;
    		} else {
    			r = mid - 1;
    		}
    	}
    	
    	if (nums[l] == target) {
    		return l;
    	}
    	
    	return -1;
    }

	@Test
	void test1() {
		int[] nums = new int[] {1,4,4,5,6,6,7,8,9,10};
		int target = 1;
		int result = (new _0014_FirstPosition().binarySearch(nums, target));
		// the first index of 1 is 0.
		assertTrue(result == 0);
	}

	@Test
	void test2() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 3;
		int result = (new _0014_FirstPosition().binarySearch(nums, target));
		// the first index of 3 is 2.
		assertTrue(result == 2);
	}

	@Test
	void test3() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 6;
		int result = (new _0014_FirstPosition().binarySearch(nums, target));
		// Not exist 6 in array.
		assertTrue(result == -1);
	}
}
