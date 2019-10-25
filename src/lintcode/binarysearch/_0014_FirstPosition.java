/*
Easy
Array, Binary Search
Salesforce
 */
package lintcode.binarysearch;

import org.junit.Test;

/**
 * 14. First Position of Target
 *
 * For a given sorted array (ascending order) and a target number, 
 *  find the first index of this number in O(log n) time complexity.
 * If the target number does not exist in the array, return -1.
 * 
 * Challenge:
 * If the count of numbers is bigger than 2^32, can your code work properly?
 */
public class _0014_FirstPosition {

	/**
     * 使用两分法
	 * time:  O(log n)
	 * space: O(1)
     */
    public int binarySearch(int[] nums, int target) {
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int l = 0, r = nums.length - 1;
    	int mid;
    	
    	while (l < r) {
    		// use following instead of mid = (l + r) / 2, because 
    		// if l = 2 ^ 31 and r = 2 ^ 31, the latter will cause overflow
    		mid = l + (r - l) / 2;
    		if (nums[mid] == target) {
    			r = mid; // 不结束的原因是为了继续向左寻找第一位
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

    // 解法2 与第一种解法类似
    public int binarySearch_two(int[] nums, int target) {
    	if (nums == null || nums.length == 0)
    		return -1;

    	int l = 0, r = nums.length - 1;
    	int mid;

    	while (l + 1 < r) {
    		mid = l + (r - l) / 2;
    		if (nums[mid] == target)
    			r = mid;
    		else if (nums[mid] > target)
    			r = mid - 1; // or r = mid
    		else
    			l = mid + 1; // or l = mid
		}

    	if (nums[l] == target)
    		return l;
    	if (nums[r] == target)
    		return r;
    	return -1;
	}

	@Test
	public void test1() {
		int[] nums = new int[] {1,4,4,5,6,6,7,8,9,10};
		int target = 1;
		// the first index of 1 is 0.
		org.junit.Assert.assertEquals(binarySearch(nums, target),0);
		org.junit.Assert.assertEquals(binarySearch_two(nums, target), 0);
	}

	@Test
	public void test2() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 3;
		int result = (new _0014_FirstPosition().binarySearch(nums, target));
		// the first index of 3 is 2.
		org.junit.Assert.assertEquals(binarySearch(nums, target), 2);
		org.junit.Assert.assertEquals(binarySearch_two(nums, target), 2);
	}

	@Test
	public void test3() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 6;
		int result = (new _0014_FirstPosition().binarySearch(nums, target));
		// Not exist 6 in array.
		org.junit.Assert.assertEquals(binarySearch(nums, target), -1);
		org.junit.Assert.assertEquals(binarySearch_two(nums, target), -1);
	}
}
