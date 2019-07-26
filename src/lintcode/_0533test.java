package lintcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _0533test {

	@Test
	void test1() {
		int[] nums = new int[] {-1, 2, 1, -4};
		int target = 4;
		int result = (new _0533().twoSumClosest(nums, target));
		// The minimum difference is 1. (4 - (2 + 1) = 1).
		assertTrue(result == 1);
	}

	@Test
	void test2() {
		int[] nums = new int[] {-1, -1, -1, -4};
		int target = 4;
		int result = (new _0533().twoSumClosest(nums, target));
		// The minimum difference is 6. (4 - (- 1 - 1) = 6).
		assertTrue(result == 6);
	}
}
