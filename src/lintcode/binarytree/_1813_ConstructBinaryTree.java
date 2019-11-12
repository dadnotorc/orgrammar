/*
Medium
Union Find
Salesforce
 */
package lintcode.binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1813. Construct Binary Tree
 *
 * Give you a series of (parent nodes, child nodes) edges, you need to use
 * these edges to determine whether the binary tree can be generated correctly,
 * if not, return the highest priority error according to the type of error
 *
 * The correct input is that both the parent and child nodes belong to A-Z
 * Can generate a binary tree and return "successful".
 * The priority of the error type is from "more children", "repeat edge",
 *  "have cycle", "more parent", "input error".
 *
 * Notice
 * - pair.length >= 1
 * - "more children" means a node has more than two nodes
 * - "repeat edge" means a edge appear more than once
 * - "have cycle" means there have a cycle
 * - "more parent" means a node have two parent or have more root nodes
 * - "input error" means the parent node and the child node in pair[i]
 *   indicate that the rule does not match the rule.
 *
 * Example1：
 * Input:
 * pair: [["A","C"],["A","B"],["A","D"]]
 * Output: "more children"
 * Explanation：A have three children,so you should return "more children"
 *
 * Example2:
 * Input:
 * pair:[["A","C"],["C","A"],["C","B"],["A","C"]]
 * Output: "repeat edge"
 * Explanation: A->C and C->A ,there is a cycle, but A->C have two edges,
 * and repeat edge prior more than cycle, so you should return repeat edge
 */
public class _1813_ConstructBinaryTree {

    // todo has bug
    private static final int SUCCESSFUL = 0;
    private static final int INPUT_ERROR = 1;
    private static final int MORE_PARENT = 2;
    private static final int HAVE_CYCLE = 3;
    private static final int REPEAT_EDGE = 4;
    private static final int MORE_CHILDREN = 5;

    // 不能使用 hashmap, 因为 [[A, B], [A, C]] 是有效的pairs, 可以组成binary tree


    public String ConstructBinaryTree(List<List<String>> pairs) {
        HashMap<Character, Character> hm = new HashMap<>();
        int err = SUCCESSFUL;

        for (List<String> pair : pairs) {
            if (pair.size() != 2)
                err = Math.max(err, INPUT_ERROR);

            String parent = pair.get(0);
            String child = pair.get(1);
            if (parent.length() == 1 && child.length() == 1) {
                char parentChar = parent.charAt(0);
                char childChar = child.charAt(0);

                int checkResult = checkPair(hm, childChar, parentChar);
                if (checkResult != SUCCESSFUL) {
                    err = Math.max(err, checkResult);
                }

                hm.putIfAbsent(childChar, parentChar);

            } else {
                err = Math.max(err, INPUT_ERROR);
            }
        }

        return printAns(err);
    }

    // hashmap中, key = child node, value = parent node.
    // 因为1个parent node可以有2个child nodes, 但1个child node只有1个parent child
    private int checkPair(HashMap<Character, Character> hm, char child, char parent) {
        if (child >= 'A' && child <= 'Z'
                && parent >= 'A' && parent <= 'Z') {
            if (hm.containsKey(child)) {
                if (hm.get(child) != parent) {
                    return MORE_PARENT;
                } else {
                    return REPEAT_EDGE;
                }
            } else if (hm. containsKey(parent)) {
                if (hm.get(parent) == child) {
                    return HAVE_CYCLE;
                }
            } else if (hm.containsValue(parent)) {
                if (!isParentValid(hm, parent)) {
                    return MORE_CHILDREN;
                }
            }
        } else {
            return INPUT_ERROR;
        }

        return SUCCESSFUL;
    }

    private boolean isParentValid(HashMap<Character, Character> hm, char parent) {
        int count = 0;
        for (char value : hm.values()) {
            if (value == parent) {
                count++;
                if (count >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private String printAns(int err) {
        switch (err) {
            case SUCCESSFUL: return "successful";
            case MORE_CHILDREN: return "more children";
            case REPEAT_EDGE: return "repeat edge";
            case HAVE_CYCLE: return "have cycle";
            case MORE_PARENT: return "more parent";
            case INPUT_ERROR: return "input error";
            default: return "";
        }
    }

    // [["A","C"],["B","C"],["C","B"]] -> have cycle
    @Test
    public void test1() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("C");

        List<String> list2 = new ArrayList<>();
        list2.add("B");
        list2.add("C");

        List<String> list3 = new ArrayList<>();
        list3.add("C");
        list3.add("B");

        List<List<String>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        String exp = "have cycle";
        System.out.println("act = " + ConstructBinaryTree(lists));
        org.junit.Assert.assertTrue(exp.equals(ConstructBinaryTree(lists)));
    }



    // [["A","C"],["C","A"],["C","B"],["A","C"]] -> repeat edge
}
