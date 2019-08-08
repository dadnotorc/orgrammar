/*
Medium
Binary Tree
LinkedIn
 */
package lintcode.binarytree;

/**
 * 649. Binary Tree Upside Down
 *
 * Given a binary tree where all the right nodes are either leaf nodes
 * with a sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the original right
 * nodes turned into left leaf nodes. Return the new root.
 *
 * Example1
 * Input: {1,2,3,4,5}
 * Output: {4,5,2,#,#,3,1}
 * Explanation:
 * The input is
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * and the output is
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 * Example2
 * Input: {1,2,3,4}
 * Output: {4,#,2,3,1}
 * Explanation:
 * The input is
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * and the output is
 *    4
 *     \
 *      2
 *     / \
 *    3   1
 *
 * Related problems:
 * 35. Reverse Linked List
 */
public class _0649_BinaryTreeUpsideDown {
}
