package leetcode;

import java.util.*;

/**
 * First Unique Number
 *
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 * - FirstUnique(int[] nums) - Initializes the object with the numbers in the queue.
 * - int showFirstUnique()   - returns the value of the first unique integer of the queue,
 *                             and returns -1 if there is no such integer.
 * - void add(int value)     - insert value to the queue.
 *
 *
 * Example 1:
 * Input: ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 *        [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * Output: [null,2,null,2,null,3,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(5);            // the queue is now [2,3,5,5]
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3
 * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // return -1
 *
 * Example 2:
 *
 * Input: ["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
 *        [[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
 * Output: [null,-1,null,null,null,null,null,17]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
 * firstUnique.showFirstUnique(); // return -1
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
 * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
 * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
 * firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
 * firstUnique.showFirstUnique(); // return 17
 *
 * Example 3:
 * Input: ["FirstUnique","showFirstUnique","add","showFirstUnique"]
 *        [[[809]],[],[809],[]]
 * Output: [null,809,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([809]);
 * firstUnique.showFirstUnique(); // return 809
 * firstUnique.add(809);          // the queue is now [809,809]
 * firstUnique.showFirstUnique(); // return -1
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^8
 * - 1 <= value <= 10^8
 * - At most 50000 calls will be made to showFirstUnique and add.
 */
public class _0000_FirstUniqueNumber {

    /**
     * 为了解决TLE问题, 使用LinkedHashSet替代LinkedList
     *
     * 使用LinkedHashSet记录unique numbers的顺序, 使用HashSet记录所有遇到的numbers (用来寻找unique)
     */
    Set<Integer> uniqueSet;
    Set<Integer> allSet;

    public _0000_FirstUniqueNumber(int[] nums) {
        uniqueSet = new LinkedHashSet<>();
        allSet = new HashSet<>();

        for (int i : nums) {
            add(i);
        }
    }

    public int showFirstUnique() {
        if (!uniqueSet.isEmpty())
            return uniqueSet.iterator().next();
        else
            return -1;
    }

    public void add(int value) {
        if (allSet.add(value)){
            uniqueSet.add(value);
        } else {
            uniqueSet.remove(value);
        }
    }

    /**
     * 会TLE
     */
    /*
    Queue<Integer> queue; // only contain unique numbers
    HashSet<Integer> set; // contains numbers that have been seen

    public _0000_FirstUniqueNumber(int[] nums) {
        queue = new LinkedList<>();
        set = new HashSet<>();

        for (int i : nums) {
            add(i);
        }
    }

    public int showFirstUnique() {
        if (!queue.isEmpty())
            return queue.peek();
        else
            return -1;
    }

    public void add(int value) {
        if (set.add(value)){
            queue.offer(value);
        } else {
            queue.remove(value);
        }
    }
    */
}
