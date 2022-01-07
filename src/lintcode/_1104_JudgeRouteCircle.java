/*
Easy
#Simulation
Google
 */
package lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1104 · Judge Route Circle
 *
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge
 * if this robot makes a circle, which means it moves back to the original place finally.
 *
 * The move sequence is represented by a string. And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be
 * true or false to indicate whether the robot returns to the origin.
 *
 * Example 1:
 * Input: "UD"
 * Output: true
 *
 * Example 2:
 * Input: "LL"
 * Output: false
 */
public class _1104_JudgeRouteCircle {

    /**
     * 最简单的就是每步都计算当前坐标
     */
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0) { return true; }

        int[] coord = new int[] {0, 0};

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);

            if (c == 'R') {
                coord[1]++;
            } else if (c == 'L') {
                coord[1]--;
            } else if (c == 'U') {
                coord[0]++;
            } else if (c == 'D') {
                coord[0]--;
            } else {
                return false;
            }
        }

        return coord[0] == 0 && coord[1] == 0;
    }


    /**
     * 把 上下左右 都赋值, 计算所有步数的值
     */
    public boolean judgeCircle_2(String moves) {
        if (moves == null || moves.length() == 0) { return true; }

        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 1);
        map.put('L', -1);
        map.put('U', 2);
        map.put('D', -2);

        int value = 0;
        for (int i = 0; i < moves.length(); i++) {
            value += map.get(moves.charAt(i));
        }

        return value == 0;
    }
}
