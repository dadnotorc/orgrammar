package lintcode;

import java.util.Stack;

/**
 * 227 · Mock Hanoi Tower by Stacks
 * Easy
 * #Stack
 *
 * In the classic problem of Towers of Hanoi, you have 3 towers and N disks of different sizes
 * which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size
 * from top to bottom (i.e., each disk sits on top of an even larger one). You have the following constraints:
 *
 * 1. Only one disk can be moved at a time.
 * 2. A disk is slide off the top of one tower onto the next tower.
 * 3. A disk can only be placed on the top of a larger disk.
 *
 * Write a program to move the disks from the first tower to the last tower.
 *
 * Example1:
 * Input: 3
 * Output:
 * towers[0]: []
 * towers[1]: []
 * towers[2]: [2,1,0]
 *
 * Example2:
 * Input: 10
 * Output:
 * towers[0]: []
 * towers[1]: []
 * towers[2]: [9,8,7,6,5,4,3,2,1,0]
 *
 */
public class _0227_Mock_Hanoi_Tower_by_Stacks {

    /*
    汉诺塔问题的解决方案如下：

    - 以目标杆为中介，从起始杆将 1 至 n−1 号盘移至中间杆
    - 将起始杆里的第 n 号盘移动到目标杆
    - 以起始杆为中介，从中间杆将 1 至 n−1 号盘移至目标杆

    这一个流程形成了一个递归过程，而我们要做的就是要利用栈去模拟这一个过程。栈具有后进先出的性质，这个和汉诺塔里塔的移动是一样的。
     */

    class Tower{

        private Stack<Integer> disks;

        /*
         * @param i: An integer from 0 to 2
         */
        public Tower(int i) {
            // create tower stack
            disks = new Stack<>();
        }

        /*
         * @param d: An integer
         * @return: nothing
         */
        public void add(int d) {
            // Add a disk into this tower
            if (!disks.isEmpty() && disks.peek() <= d) {
                System.out.println("Error placing disk " + d);
            } else {
                disks.push(d);
            }
        }

        /*
         * @param t: a tower
         * @return: nothing
         */
        public void moveTopTo(Tower t) {
            // Move the top disk of this tower to the top of t.
            if (this.disks.isEmpty()) {
                System.out.println("Current tower is empty");
            } else if (!t.disks.isEmpty() && t.disks.peek() < this.disks.peek()) {
                System.out.printf(
                        "Unable to move %d as the target tower has a smaller disk %d on top",
                        this.disks.peek(), t.disks.peek());
            } else {
                t.add(this.disks.pop());
            }
        }

        /*
         * @param n: An integer
         * @param destination: a tower
         * @param buffer: a tower
         * @return: nothing
         */
        public void moveDisks(int n, Tower destination, Tower buffer) {
            // Move n Disks from this tower to destination by buffer tower
            if (n > 0) {
                moveDisks(n - 1, buffer, destination); // 以目标杆为中介，从起始杆将 1 至 n-1 号盘移至中间杆
                moveTopTo(destination); //将起始杆里的第 n 号盘移动到目标杆
                buffer.moveDisks(n - 1, destination, this); //以起始杆为中介，从中间杆将 1 至 n-1 号盘移至目标杆
            }
        }

        /*
         * @return: Disks
         */
        public Stack<Integer> getDisks() {
            // write your code here
            return disks;
        }
    }

    /**
     * Your Tower object will be instantiated and called as such:
     * Tower[] towers = new Tower[3];
     * for (int i = 0; i < 3; i++) towers[i] = new Tower(i);
     * for (int i = n - 1; i >= 0; i--) towers[0].add(i);
     * towers[0].moveDisks(n, towers[2], towers[1]);
     * print towers[0], towers[1], towers[2]
     */

}
