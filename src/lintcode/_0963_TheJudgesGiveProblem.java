/*
Medium
#Binary Search
NetEase
 */
package lintcode;

/**
 * 963 · The Judges Give Problem
 *
 * The difficulty of the questions is divided into three grades:
 * "simple questions", "medium questions" and "difficult questions".
 * The judges have come up with E simple questions, M medium questions and H difficult questions.
 * Then the judges give EM "Simple And Medium" questions and MH "Medium And Difficult" questions.
 * The "Simple And Medium" is a type of problem that can be classified as either a "simple question or a "medium question".
 * The "Medium And Difficult" is a type that can be classified as either a "medium question" or a "difficult question".
 * The judges decreed that a contest must consist of three questions: one for "easy", one for "medium" and
 * one for "difficult". Each question can only appear in one match at most.
 * Now we want to know how many contests can the judges organize?
 *
 * 0 <= E, EM, M, MH, H <= 10^18
 *
 * Example 1:
 * Input: 2, 2, 1, 2, 2
 * Output: 3
 *
 * Contest 1: E_1, M_1, H_1
 * Contest 2: E_2, EM_1, H_2
 * Contest 3: EM_2, MH_1, MH2
 */
public class _0963_TheJudgesGiveProblem {

    /**
     * 二分 contest 数量
     */
    public long theNumberOfContests(long E, long EM, long M, long MH, long H) {
        long start = 0; // contest 数量从 0 开始, 因为各类题型可能等于 0
        long end = Math.max(E + EM, Math.max(EM + M + MH, MH + H)); // 简单/中等/困难 三类的最大值 -> 即 contest 数量的天花板

        while (start + 1 < end) {
            long mid = start + (end - start) / 2; // mid 为当前市场的 contest 数量
            if (canHoldContest(E, EM, M, MH, H, mid)) { // 如果可以组织 mid 数量的 contest, 就可以试试更多的 contest
                start = mid;
            } else { // 无法组织 mid 数量的contest, 所以要减少
                end = mid;
            }
        }

        return canHoldContest(E, EM, M, MH, H, end) ? end : start; // 先尝试后者, 因为需要返回尽量多的 contest
    }

    public boolean canHoldContest(long E, long EM, long M, long MH, long H, long target) {
        long min = Math.min(E, Math.min(M, H)); // 能支持的最少的 contest 数量
        if (target < min) {
            return true;
        }

        if (E < target) { // 为达到 target 数量的 contest, 如果简单题 E 数量不够, 则用 EM 凑. 相应的减少 EM 可用的数量
            EM -= target - E;
        }
        if (EM < 0) { // 如果 E + EM < target, 说明没戏
            return false;
        }

        if (H < target) { // 同理, 这里判断困难题
            MH -= target - H;
        }
        if (MH < 0) {
            return false;
        }

        // 此时已知, 简单题和困难题都已经满足条件, 即 >= targe
        // 剩下判断中等题数量是否 >= target
        return M + EM + MH >= target;
    }

}
