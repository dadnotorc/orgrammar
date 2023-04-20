package leetcode;

/**
 * 205. Isomorphic Strings
 * Easy
 * #Hash Table, #String
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 *
 *
 * Constraints:
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class _0205_Isomorphic_Strings {

    /**
     * 记录 s, t 中每个字符出现的先后顺序, 例如 paper vs title
     *
     * paper VS title
     * order1[p] = 0, order2[t] = 0, 相等 -> 给双方赋值 order1[p] = 1, order2[t] = 1
     * order1[a] = 0, order2[i] = 0, 相等 -> 给双方赋值 order1[a] = 2, order2[i] = 2
     * order1[p] = 1, order2[t] = 1, 相等 -> 给双方赋值 order1[p] = 3, order2[t] = 3
     * order1[e] = 0, order2[l] = 0, 相等 -> 给双方赋值 order1[e] = 4, order2[l] = 4
     * order1[r] = 0, order2[e] = 0, 相等 -> 给双方赋值 order1[r] = 5, order2[e] = 5
     *
     *
     * 同理 foo VS bar
     * order1[f] = 0, order2[b] = 0, 相等 -> 给双方赋值 order1[f] = 1, order2[b] = 1
     * order1[o] = 0, order2[a] = 0, 相等 -> 给双方赋值 order1[o] = 2, order2[a] = 2
     * order1[o] = 2, order2[r] = 0, 不相等
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) { return false; }

        int[] order1 = new int[256]; // 128 亦可
        int[] order2 = new int[256];

        for (int i = 0; i < s.length(); i++) {
            int c1 = s.charAt(i);
            int c2 = t.charAt(i);

            // 如果当前 c 从未遇见过, order[c] 仍为 0
            // 如果 c1 或者 c2 的出现顺序不同, 则返回 false

            if (order1[c1] != order2[c2]) {
                return false;
            }

            // 将当前 c 的出现顺序定位 i + 1, 目的是去除 0

            order1[c1] = i + 1;
            order2[c2] = i + 1;
        }

        return true;
    }
}
