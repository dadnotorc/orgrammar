package leetcode;

/**
 * 1093. Statistics from a Large Sample
 * Medium
 *
 * We sampled integers between 0 and 255, and stored the results in an array count:
 *  count[k] is the number of integers we sampled equal to k.
 *
 *
 * Return the minimum, maximum, mean, median, and mode of the sample respectively,
 *  as an array of floating point numbers.  The mode is guaranteed to be unique.
 *
 * Recall that the median of a sample is:
 * - The middle element, if the elements of the sample were sorted
 *    and the number of elements is odd;
 * - The average of the middle two elements, if the elements of the sample were sorted
 *    and the number of elements is even.
 *
 * Constraints:
 * 1. count.length == 256
 * 2. 1 <= sum(count) <= 10^9
 * 3. The mode of the sample that count represents is unique.
 * 4. Answers within 10^-5 of the true value will be accepted as correct.
 *
 * 此题含义是, 在[0,255]中采样, 在样本中, 将k出现的次数存入count[k], 例如:
 * count = [0,1,3,4,0...0]:
 * k = 0, count[k] = 0 说明在样本里, 0出现的次数为0次
 * k = 1, count[k] = 1 说明在样本里, 1出现的次数为1次
 * k = 2, count[k] = 3 说明在样本里, 2出现的次数为3次
 * k = 3, count[k] = 4 说明在样本里, 3出现的次数为4次 -> 众数 mode
 * 所以min=1.0, max=3.0, mean=(1+2+2+2+3+3+3+3)/8.0=2.375, median=[2+3]/2.0=2.5, mode=3.0
 *
 * 时间复杂度: O(1). 因为数组长度256, 两次循环512, 仍是常数
 * 空间复杂度: O(1)
 */
public class _1093 {
    public double[] sampleStats(int[] count) {
        double min = -1, max = 0, mean = 0, median = 0;
        double sum = 0; // sum设为double, 否则算mean时会出问题
        int mode = 0; // 在这里mode为int, 因为还需要用count[mode]来寻找众数
        int n = 0; // n = 样本数目

        for (int k = 0; k < count.length; k++) {
            if (count[k] > 0) {
                // 样本中, i出现过至少一次
                if (min < 0) {
                    min = k;
                }
                max = k;
                n += count[k];
                sum += k * count[k];
                if (count[k] > count[mode]) {
                    mode = k;
                }
            }
        }

        // 第一遍循环做完, 已找到 min, max, mode, 已获得样本数目n, 样本数字总和sum

        if (n > 0) {
            mean = sum / n;
        } else {
            return new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        }

        int m1 = (n + 1) / 2;
        int m2 = (n / 2) + 1;
        // 如果样本数n为odd, m1 = m2
        // 如果样本数n为even, m1 + 1 = m2

        for (int i = 0, c =0; i < count.length; i++) {
            //n为odd时,  median =  m1 = m2      = m1 / 2 + m2 / 2
            //n为even时, median = (m1 + m2) / 2 = m1 / 2 + m2 / 2

            if (c < m1 && c + count[i] >= m1) {
                // find m1, add its half
                median += i / 2.0d;
            }
            if (c < m2 && c + count[i] >= m2) {
                // find m2, add its half
                median += i / 2.0d;
            }
            c += count[i];
        }

        return new double[]{min, max, mean, median, mode};
    }
}
