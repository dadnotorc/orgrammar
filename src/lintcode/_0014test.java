package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class _0014test {

	@Test
	void test1() {
		int[] nums = new int[] {1,4,4,5,6,6,7,8,9,10};
		int target = 1;
		int result = (new _0014().binarySearch(nums, target));
		// the first index of 1 is 0.
		assertTrue(result == 0);
	}

	@Test
	void test2() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 3;
		int result = (new _0014().binarySearch(nums, target));
		// the first index of 3 is 2.
		assertTrue(result == 2);
	}

	@Test
	void test3() {
		int[] nums = new int[] {1,2,3,3,4,5,10};
		int target = 6;
		int result = (new _0014().binarySearch(nums, target));
		// Not exist 6 in array.
		assertTrue(result == -1);
	}
}
