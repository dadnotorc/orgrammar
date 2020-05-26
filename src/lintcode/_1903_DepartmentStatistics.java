/*
Easy

Amazon
 */
package lintcode;

import java.util.List;

/**
 * 1903. Department Statistics
 *
 * You are given information of employees in a company, with their IDs, names and the department they belong to.
 * And their friendships, every friendship contains two IDs, "1, 2" means employees ID 1 and ID 2 are friends.
 * The friendships are not transitive, for example, A and B are both C's friend, however B and C may not be friend.
 * Please count the number of employees that have a friend in another department for every department.
 *
 * Notice
 * All input is followed by a space after the comma, and the output of your program must be in the same format as the sample.
 * The returned list has no order requirements.
 * The size of employee information is no more than 50.
 * The size of friendships is no more than 1000.
 * The IDs are numbers no more than 100.
 * The number of different department is no more than 20.
 *
 * Clarification
 * In the example，employee 1 in Engineer and employee 2 in HR are friends，
 * employee 3 in Engineer and employee 4 in Business are friends，
 * so Engineer has 2 employees having friends with other department，
 * return "Engineer: 2 of 3“。Besides HR has 1， Business has 1.
 *
 * Example
 * Sample Input:
 * employees = [
 *   "1, Bill, Engineer",
 *   "2, Joe, HR",
 *   "3, Sally, Engineer",
 *   "4, Richard, Business",
 *   "6, Tom, Engineer"
 * ]
 *
 * friendships = [
 *   "1, 2",
 *   "1, 3",
 *   "3, 4"
 * ]
 *
 * Sample Output:
 * "Engineer: 2 of 3"
 * "HR: 1 of 1"
 * "Business: 1 of 1"
 */
public class _1903_DepartmentStatistics {

    //todo  https://www.jiuzhang.com/solution/department-statistics/#tag-other
//    public List<String> departmentStatistics(List<String> employees, List<String> friendships) {
//        // write your code here.
//    }


}
