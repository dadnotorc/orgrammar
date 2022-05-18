package lintcode;

import java.util.*;

/**
 * 1082 · Employee Importance
 * Easy
 * #DFS
 *
 * You are given a data structure of employee information, which includes
 * - the employee's unique id,
 * - his importance value
 * - and his direct subordinates' id.
 *
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively.
 * Then employee 1 has a data structure like [1, 15, [2]],
 * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []].
 * Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 *
 * Now given the employee information of a company, and an employee id,
 * you need to return the total importance value of this employee and all his subordinates.
 *
 * - One employee has at most one direct leader and may have several subordinates.
 * - The maximum number of employees won't exceed 2000.
 *
 * Example 1:
 * Input: employees = [[1,2,[2]], [2,3,[]]], id = 2
 * Output: 3
 * Explanation:
 * the total importance value of employee 1 is 3
 *
 * Example 2:
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
 * They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 */
public class _1082_Employee_Importance {

    /**
     * 1. 建立 map <id, employee>
     * 2. 将遇到的 id 分别加入 queue 与 hashset
     *    - queue 记录当前 id 的下属们
     *    - hashset 记录已经访问过的员工 id
     *
     * 时间 O(n), 空间 O(n)
     */
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        int ans = 0;

        // 先用 HashMap 记录 <ID, Employee class>
        HashMap<Integer, Employee> map = getMap(employees);

        // queue 记录下属, set 记录已经查过的员工
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();

        q.offer(id);

        while (!q.isEmpty()) {
            int cur_employee = q.poll();
            if (map.containsKey(cur_employee) && !set.contains(cur_employee)) {
                set.add(cur_employee);
                ans += map.get(cur_employee).importance;
                for (int employee_id : map.get(cur_employee).subordinates) {
                    q.offer(employee_id);
                }
            }
        }

        return ans;
    }

    public HashMap<Integer, Employee> getMap(List<Employee> employees) {
        HashMap<Integer, Employee> map = new HashMap<>();

        for (Employee e : employees) {
            map.put(e.id, e);
        }

        return map;
    }

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }
}
