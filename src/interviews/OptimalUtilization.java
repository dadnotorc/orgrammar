package interviews;

/**
 * Input: 3 arguments
 * 1. int - target value
 * 2. a list of pairs of integers, [[id1,num1],[id2,num2,]...]
 * 3. another list similar to #2 above
 *
 * Output: a list of pairs of integers representing the pairs of IDs from
 *   input #1 and #2, where their combined value <= target, and is the biggest.
 *  If no pair is possible, return an empty list
 *
 * Example1:
 * Input:
 * - target = 7000
 * - list1 = [[1,2000],[2,4000],[3,6000]]
 * - list2 = [[1,2000]]
 * Output: [[2,1]]
 * Explanation: There are only 3 combo, [1,1],[2,1],[3,1], which have a total of
 *  4000, 6000, and 8000 respectively. Since 6000 is the largest and doesn't
 *  exceed 7000. So, [2,1] is the only answer
 *
 * Example2:
 *  * Input:
 *  * - target = 10000
 *  * - list1 = [[1,3000],[2,5000],[3,7000],[4,10000]]
 *  * - list2 = [[1,2000],[2,3000],[3,4000],[4,5000]]
 *  * Output: [[2,4],[3,2]]
 *  * Explanation: These 2 pairs give the optimal results, sum = target.
 *
 * 注意:
 * 1. 两个lists并未排序
 * 2. 每个list内, id是唯一unique; 但是在另一list内,可能会有重复出现的id
 * 3. 返回值可为空, 一个值, 或者多个值
 *
 * Amazon OA
 */
public class OptimalUtilization {

//    public List<List<Integer>> optimalUtilization(
//            int target, List<List<Integer>> list1, List<List<Integer>> list2) {
//        if (list1 == null || list1.size() == 0 ||
//                list2 == null || list2.size() == 0) {
//            return new ArrayList<>();
//        }
//
//
//
//    }
}
