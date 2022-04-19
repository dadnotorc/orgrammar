package lintcode;

import java.util.*;

/**
 * 1331 · English Software
 * Easy
 * #Prefix Sum, #Array
 * NetEase
 *
 * Xiao Lin is a representative of the English class in the class.
 * He wants to develop a software to handle the grades of classmates.
 *
 * Xiao LIn ’s software has a magical feature that can reflect the position of your grades
 * in the class through a percentage. "Classmates with grades exceeding…%".
 * Suppose this percentage is p, and s score is tested, then p can be calculated by the following formula:
 * p = (number of people whose score does not exceed s - 1) / total number of students in the class * 100%
 * Please design this software
 *
 * The score array is given to represent the i-th person to take score[i] points
 * Give the ask array to represent the score of the i-th individual
 * Each query will output the corresponding score percentage, no need to output a percent sign
 * The answer is rounded down（To avoid loss of precision, please calculate multiplication first）
 *
 * Example 1:
 * Input: score= [100,98,87], ask=[1,2,3]
 * Output: [66,33,0]
 * Explanation:
 * The first person scored 100 points, more than 66% of students
 */
public class _1331_English_Software {

    /*
    1. 统计 0 至 100分, 每个分数的获得者有多少人
    2. 计算前缀和, 即得分 <= cur_score 的总人数
    3. 用公式计算 p

    注意
    - ask 数列是 1-based
    - duplicate? 有重复也没关系

    时间 O(2 * n)
    空间 O(1)
     */
    public List<Integer> englishSoftware(List<Integer> score, List<Integer> ask) {
        int[] counts = new int[101]; // 统计当前分数获得者有多少

        for (int i : score) {
            counts[i]++;
        }

        // 计算前缀和 - 即 得分 <= i 的人数共有多少
        for (int i = 1; i < 101; i++) {
            counts[i] += counts[i - 1];
        }

        int total_students = score.size();
        List<Integer> ans = new ArrayList<>();
        for (int i : ask) {
            int cur_score = score.get(i - 1); // ask 是 1-based, 所以别忘了 - 1

            // counts[cur_score] 为得分 <= cur_score 的总人数. 计算 p 的时候, 减去当前这个人 (即 - 1)
            // 先乘 后除, 避免浮点运算
            int p = (counts[cur_score] - 1) * 100 / total_students;
            ans.add(p);
        }

        return ans;
    }

}
