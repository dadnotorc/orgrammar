package explore;

import org.junit.Test;

/**
 * Morris Traversal 方法遍历二叉树（非递归，不用栈，O(1)空间）
 * https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 */
public class MorrisTraversal {

    /**
     * 一、中序遍历
     * 步骤：
     * 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
     *
     * 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     *
     *    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     *
     *    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
     *
     * 3. 重复以上1、2直到当前节点为空。
     *
     * 空间复杂度：O(1)，因为只用了两个辅助指针。
     * 时间复杂度：O(n)。证明时间复杂度为O(n)，最大的疑惑在于寻找中序遍历下二叉树中所有节点的前驱节点的时间复杂度是多少，即以下两行代码：
     * 1 while (prev->right != NULL && prev->right != cur)
     * 2     prev = prev->right;
     * 直觉上，认为它的复杂度是O(nlgn)，因为找单个节点的前驱节点与树的高度有关。但事实上，寻找所有节点的前驱节点只需要O(n)时间。
     * n个节点的二叉树中一共有n-1条边，整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，
     * 如下图所示，其中红色是为了定位到某个节点，黑色线是为了找到前驱节点。所以复杂度为O(n)。
     * https://images0.cnblogs.com/blog/300640/201306/15150628-5285f29bab234750a62e2309394b6e14.jpg
     */
    public void inOrderMorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null) {
            if(cur.left == null) {
                System.out.print(cur.val + " ");
                cur = cur.right;
            } else {
                // find predecessor
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) { // 2a
                    pre.right = cur;
                    cur = cur.left;
                } else { // 2b
                    pre.right = null;
                    System.out.print(cur.val + " ");
                    cur = cur.right;
                }
            }
        }
    }


    /**
     * 二、前序遍历
     * 前序遍历与中序遍历相似，代码上只有一行不同，不同就在于输出的顺序。
     * 步骤：
     * 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
     *
     * 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     *
     *    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。输出当前节点（在这里输出，这是与中序遍历唯一一点不同）。
     *       当前节点更新为当前节点的左孩子。
     *
     *    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。(这里不输出当前节点, 与中序遍历不同)
     *       当前节点更新为当前节点的右孩子。
     *
     * 3. 重复以上1、2直到当前节点为空。
     *
     * 时间复杂度与空间复杂度都与中序遍历时的情况相同。
     */
    public void preOrderMorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null) {
            if(cur.left == null) {
                System.out.print(cur.val + " ");
                cur = cur.right;
            } else {
                // find predecessor
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) { // 2a
                    pre.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                } else { // 2b
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }


    /**
     * 三、后序遍历
     * 后续遍历稍显复杂，需要建立一个临时节点dump，令其左孩子是root。并且还需要一个子过程，就是倒序输出某两个节点之间路径上的各个节点。
     * 步骤：
     * 当前节点设置为临时节点dump。
     * 1. 如果当前节点的左孩子为空，则将其右孩子作为当前节点。
     *
     * 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     *
     *    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     *
     *    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。__倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点__。
     *       当前节点更新为当前节点的右孩子。
     *
     * 3. 重复以上1、2直到当前节点为空。
     *
     * 空间复杂度同样是O(1)；时间复杂度也是O(n)，倒序输出过程只不过是加大了常数系数。
     */
    public void postOrderMorrisTraversal(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        dump.left = root;
        TreeNode cur = dump;
        TreeNode pre = null;

        while (cur != null) {
            if(cur.left == null) {
                // 这里不再print了
                cur = cur.right;
            } else {
                // find predecessor
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) { // 2a
                    pre.right = cur;
                    cur = cur.left;
                } else { // 2b
                    printReverse(cur.left, pre);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    // print the reversed tree nodes
    private void printReverse(TreeNode from, TreeNode to) {
        reverse(from, to);

        TreeNode p = to;
        while (true) {
            System.out.print(p.val + " ");
            if (p == from) {
                break;
            }
            p = p.right;
        }

        reverse(to, from);
    }

    // reverse the tree nodes 'from' -> 'to'
    private void reverse(TreeNode from, TreeNode to) {
        if (from == to) {
            return;
        }

        TreeNode x = from, y = from.right, z = null;

        while (true) {
            z = y.right;
            y.right = x;
            x = y;
            y = z;
            if (x == to) {
                break;
            }
        }
    }







    @Test
    //       4
    //     /    \
    //    2     6
    //   / |   / \
    //  1  3  5  7
    // in-order traversal: 1 2 3 4 5 6 7
    // pre-order traversal: 4 2 1 3 6 5 7
    // post-order traversal: 1 3 2 5 7 6 4
    public void test_inorder() {
        TreeNode n0 = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(7);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        System.out.print("in-order traversal: ");
        inOrderMorrisTraversal(n0);
        System.out.print("\n");
        System.out.print("pre-order traversal: ");
        preOrderMorrisTraversal(n0);
        System.out.print("\n");
        System.out.print("post-order traversal: ");
        postOrderMorrisTraversal(n0);
        System.out.print("\n");
    }



    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
