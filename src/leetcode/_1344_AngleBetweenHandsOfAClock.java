/*
Medium
#Math
 */
package leetcode;

/**
 * 1344. Angle Between Hands of a Clock
 *
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees)
 * formed between the hour and the minute hand.
 *
 * Example 1:
 * Input: hour = 12, minutes = 30 (12:30)
 * Output: 165
 *
 * Example 2:
 * Input: hour = 3, minutes = 30 (3:30)
 * Output: 75
 *
 * Example 3:
 * Input: hour = 3, minutes = 15 (3:15)
 * Output: 7.5
 *
 * Example 4:
 * Input: hour = 4, minutes = 50
 * Output: 155
 *
 * Example 5:
 * Input: hour = 12, minutes = 0
 * Output: 0
 *
 * Constraints:
 * - 1 <= hour <= 12
 * - 0 <= minutes <= 59
 * - Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class _1344_AngleBetweenHandsOfAClock {

    /**
     * 1. 计算时针与分针各自与顶点(12:00)的角度
     * 2. 计算两个指针之间的角度 (2种可能) - 取较小者
     */
    public double angleClock(int hour, int minutes) {

        // 分针60分钟转360度 = 每分钟转6度.
        double minAngle = minutes * 6;

        // 时针每12小时转360度 = 每满1小时转30度; 不足1小时的部分, 60分钟转30度 = 即每分钟转0.5度; 两者相加
        // 注意! hour等于12的时候, 实际上为0点, 所以需要 mod 12
        double hourAngle = hour % 12 * 30 + minutes * 0.5;

        double bigger = Math.max(minAngle, hourAngle);
        double smaller = Math.min(minAngle, hourAngle);

        // 时针分针的角度有两个:
        // 1. 如果两者相差 < 180度: 较大者 - 较小者
        // 2. 如果上者 > 180度, 则反向计算角度: 360 - 较大者 + 较小者
        return Math.min(bigger - smaller, 360 - bigger + smaller);
    }


    /**
     * 另一种写法, 使用Math.abs
     */
    public double angleClock_2(int hour, int minutes) {

        // 分针60分钟转360度 = 每分钟转6度.
        double minAngle = minutes * 6;

        // 时针每12小时转360度 = 每满1小时转30度; 不足1小时的部分, 60分钟转30度 = 即每分钟转0.5度; 两者相加
        // 注意! hour等于12的时候, 实际上为0点, 所以需要 mod 12
        double hourAngle = hour % 12 * 30 + minutes * 0.5;

        double angle = Math.abs(minAngle - hourAngle);
        return Math.min(angle, 360 - angle);
    }
}
