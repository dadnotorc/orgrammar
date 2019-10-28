/*
Medium
Union Find,
Salesforce
 */
package lintcode;

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
 * The priority of the error type is from
 * "large children", "repeat edge", "have cycle", "more parent", "input error".
 *
 * Notice
 *  - pair.length >= 1
 *  - "more children" means a node has more than two nodes
 *  - "repeat edge" means a edge appear more than once
 *  - "have cycle" means there have a cycle
 *  - "more parent" means a node have two parent or have more root nodes
 *  - "input error" means the parent node and the child node in pair[i] indicate that the rule does not match the rule.
 *
 * Example1：
 * Input: pair: [["A","C"],["A","B"],["A","D"]]
 * Output: "more children"
 * Explanation：A have three children,so you should return "more children"
 *
 * Example2:
 * Input:
 * pair:[["A","C"],["C","A"],["C","B"],["A","C"]]
 * Output: "repeat edge"
 * Explanation: A->C and C->A ,there is a cycle, but A->C have two edges, and repeat edge prior more than cycle, so you should return repeat edge
 */
public class _1813_ConstructBinaryTree {

    // todo
//    public String ConstructBinaryTree(List<List<String>> pair) {
//    }
}
