package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There is a tree representing the hierarchy of Amazon management.
 * CEO being the root, and you being somewhere in tree.
 * Each node contains the employee's name and income.
 *
 * Two parts:
 * 1. Of the employees on your level,
 *     what are the salaries that are greater than yours?
 * 2. Who's subtree has the largest average income?
 *     (including the root of the subtree)
 *
 * Amazon
 */
public class TreeTraverse {
    class TreeNode {
        String name;
        int level;
        int income;
        List<TreeNode> directReport;
        public TreeNode(String name, int level, int income) {
            this.name = name;
            this.level = level;
            this.income = income;
            this.directReport = new ArrayList<>();
        }
    }

    /* Q1 */
    /**
     * Find the employee with the same name as provided - DFS
     *
     * @param root CEO of the company
     * @param name name of the employee
     * @return the tree node of the employee
     */
    public TreeNode findEmployee(TreeNode root, String name) {
        if (root == null || name == null || name.length() == 0)
            return null;

        if (root.name.equals(name)) {
            return root;
        }

        TreeNode target = null;

        for (TreeNode em: root.directReport) {
            target = findEmployee(em, name);
            if (target != null)
                return target;
        }

        return null;
    }

    /**
     * For all other employees on the same level, find the incomes
     *  that are higher than the target employee - BFS
     *
     * @param root CEO of the company
     * @param name name of the employee
     * @return a list of higher income than the target employee's
     */
    public List<Integer> findHigherIncome(TreeNode root, String name) {
        if (root == null || name == null || name.length() == 0)
            return null;

        // the target employee
        TreeNode employee = findEmployee(root, name);
        if (employee == null) {
            return null;
        }

        int level = employee.level;
        int income = employee.income;

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);


        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curEm = q.poll();

                if (curEm.level == level && curEm.income > income)
                    list.add(curEm.income);

                // assume employees on lower level would have lower income
                // thus skip scan them and their direct reports
                if (curEm.level < level)
                    continue;

                if (curEm.level > level) {
                    for (TreeNode em: curEm.directReport) {
                        q.offer(em);
                    }
                }
            }
        }

        return list;
    }

    /* Q2 */
    public class ResultType {
        public int sum, size, maxSubTreeSum, maxSubTreeSize;
        TreeNode maxSubTree;
        public ResultType(int sum, int size, int maxSubTreeSum,
                          int maxSubTreeSize, TreeNode maxSubTree) {
            this.sum = sum;
            this.size = size;
            this.maxSubTreeSum = maxSubTreeSum;
            this.maxSubTreeSize = maxSubTreeSize;
            this.maxSubTree = maxSubTree;
        }
    }

    /**
     * Find whose subtree has the largest average income,
     *  including the root of the subtree
     *
     * @param root CEO of the company
     * @return the subtree with the largest average income
     */
    public TreeNode findSubTree(TreeNode root) {
        ResultType result = helper(root);
        return result.maxSubTree;
    }

    public ResultType helper(TreeNode root) {
        if (root == null)
            return new ResultType(0, 0, 0, 0, root);

        ResultType[] subResults = new ResultType[root.directReport.size()];
        int index = 0;
        for (TreeNode em: root.directReport) {
            subResults[index++] = helper(em);
        }

        int sum = root.income;
        int size = 1;
        for (ResultType rt: subResults) {
            sum += rt.sum;
            size += rt.size;
        }

        ResultType result = new ResultType(sum, size, sum, size, root);

        for (ResultType rt: subResults) {
            if (rt.size > 0 &&
                    result.maxSubTreeSum * rt.maxSubTreeSize < rt.maxSubTreeSum * result.maxSubTreeSize) {
                result.maxSubTree = rt.maxSubTree;
                result.maxSubTreeSum = rt.maxSubTreeSum;
                result.maxSubTreeSize = rt.maxSubTreeSize;
            }
        }

        return result;
    }
}
