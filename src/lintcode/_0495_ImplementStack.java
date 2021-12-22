/*
Easy
Stack
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 495 · Implement Stack
 *
 * Implement a stack. You can use any data structure inside a stack except stack itself to implement it.
 *
 * Example 1:
 * Input:
 * push(1)
 * pop()
 * push(2)
 * top()  // return 2
 * pop()
 * isEmpty() // return true
 * push(3)
 * isEmpty() // return false
 *
 * Example 2:
 * Input:
 * isEmpty()
 */
public class _0495_ImplementStack {

    private List<Integer> list = new ArrayList<>();

    public void push(int x) {
        list.add(x);
    }

    public void pop() {
        if (!isEmpty()) {
            list.remove(list.size() - 1);
        }
    }

    public int top() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
