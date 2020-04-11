/*
Easy
#Stack, #Design
 */
package leetcode;

/**
 * 155. Min Stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 * 此题跟 LintCode 12. Min Stack 类似
 */
public class _0155_MinStack {

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    /** initialize your data structure here. */

    // 用default constructor就行, 也可以不写
    public _0155_MinStack() {

    }

    private class MinStackNode {
        int val, min;
        MinStackNode nextNode; // 当前节点的下一层 (当前节点在最顶层, nextNode指向第二层)

        private MinStackNode(int val, int min) {
            this(val, min, null);
        }

        // 在当前层记录: 1. 当前层数字, 2. 到目前为止最小的值
        private MinStackNode(int val, int min, MinStackNode node) {
            this.val = val;
            this.min = min;
            this.nextNode = node;
        }
    }

    // 别忘了写这句!
    private MinStackNode head;

    /** Push element x onto stack */
    public void push(int x) {
        if (head == null) {
            head = new MinStackNode(x, x);
        } else {
            // 等号右边是原来的head/top node
            // 等号左边是新加入的, 成为新的head/top
            head = new MinStackNode(x, Math.min(x, head.min), head);
        }
    }

    /** Removes the element on top of the stack */
    public void pop() {
        head = head.nextNode;
    }

    /** Get the top element */
    public int top() {
        return head.val;
    }

    /** Retrieve the minimum element in the stack */
    public int getMin() {
        return head.min;
    }
}
