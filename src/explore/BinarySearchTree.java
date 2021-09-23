package explore;

public class BinarySearchTree {

    private Node root;

    public Node find (int data) {
        Node node = root;
        while (node != null) {
            if (node.data == data) {
                return node;
            } else if (node.data < data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null; // 循环结束, 未找到
    }

    public void insert (int data) {
        if (root == null) { // 树为空, 直接插入到root节点
            root = new Node(data);
            return;
        }

        if (find(data) != null) {
            return;
        }

        Node node = root;
        while (node != null) {
            if (node.data < data) { // 新数据较大, 在右子树中查找
                if (node.right == null) { // 右节点为空, 直接插入
                    node.right = new Node(data);
                    return;
                }
                node = node.right;
            } else { // 新数据较小, 在左子树中查找
                if (node.left == null) {
                    node.left = new Node(data);
                    return;
                }
                node = node.left;
            }
        }
    }

    public void delete (int data) {
        Node node = root; // node将是要删除的节点
        Node parentNode = null; // parentNode是node的父节点

        // 先找到要删除的节点
        while (node != null && node.data != data) {
            parentNode = node;
            if (node.data < data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (node == null) {
            return;
        }

        // 如果node有左右两个节点, 先找到其右子树的最小节点p, 然后将其值互换. (删除部分留在后面, 此处只做互换)
        if (node.left != null && node.right != null) {
            Node p = node.right;
            Node pp = node;
            while (p.left != null) {
                pp = p;
                p = p.left;
            }
            node.data = p.data;
            node = p; // node与p互换值以后, 我们只需要删除p点即可
            parentNode = pp;
        }

        Node childNode = node.left != null ? node.left : node.right;

        if (parentNode == null) { // 要删除根节点
            root = childNode;
        } else if (parentNode.left == node) {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
    }

    public Node findMax () {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node findMin() {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;
        public Node (int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
}
