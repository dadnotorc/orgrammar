public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) { return 0; }
        if (nums.length == 1) { return 1; }

        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }

            fast++;
        }

        return slow + 1;
    }
}
