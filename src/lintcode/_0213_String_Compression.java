package lintcode;

/**
 * 213 · String Compression
 * Easy
 * #String, #Simulation
 * Snapchat, Microsoft, Lyft, Yelp
 *
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3.
 *
 * If the "compressed" string would not become smaller than the original string, your method should return the original string.
 *
 * You can assume the string has only upper and lower case letters (a-z).
 *
 * Example 1:
 * Input: str = "aabcccccaaa"
 * Output: "a2b1c5a3"
 *
 * Example 2:
 * Input: str = "aabbcc"
 * Output: "aabbcc"
 */
public class _0213_String_Compression {

    /* 面试时要考虑的
    1. 按照字母出现的顺序进行压缩
       - 字母可能重复出现, aaabbbaaa -> a3b3a3
    2. 左指针 - 指向前一个字母
       右指针 - 负责统计前一个字母的字数
     */

    /**
     * 第二版
     */
    public String compress_2(String originalString) {
        if (originalString == null || originalString.length() < 2) {
            return originalString;
        }

        StringBuilder sb = new StringBuilder();

        int l = 0, r = 1;

        while (r < originalString.length()) {
            if (originalString.charAt(l) != originalString.charAt(r)) {
                // 处理上一个字母及个数
                sb.append(originalString.charAt(l)); // 这里也是用 l
                sb.append(r - l);
                l = r;
            }

            r++; // 别把这个写进 if 里面去
        }

        sb.append(originalString.charAt(l)); // 这里用 l, 而不是 r
        sb.append(r - l);

        return sb.length() < originalString.length() ? sb.toString() : originalString; // 易错点 #3
    }


    /**
     * 第一版 - 就是遍历
     */
    public String compress(String originalString) {
        if (originalString == null || originalString.length() < 2) {
            return originalString;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(originalString.charAt(0));

        int l = 0, r = 1;
        int count = 1;

        while (r < originalString.length()) {
            if (originalString.charAt(l) == originalString.charAt(r)) {
                count++;
            } else {
                sb.append(count);
                count = 1; // 易错点 #1, 别 reset 成 0
                sb.append(originalString.charAt(r));
                l = r;
            }

            r++;
        }

        sb.append(count); // 易错点 #2, 别忘了最后这个

        return sb.length() < originalString.length() ? sb.toString() : originalString; // 易错点 #3
    }
}
