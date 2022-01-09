package interviews;

import org.junit.Assert;
import org.junit.Test;

/**
 * A simple cipher is built ton the alphabet wheel which has uppercase English letter ['A'-'Z'].
 * 一个环, A - Z, 绕一圈, 26 个字母. 左移/右移 26次回到自己
 *
 * Given an encrypted string consisting of English letters ['A'-'Z'] only, decrypt the string by replacing
 * each character with the kth character away on the wheel in the counter-clockwise direction (逆时针)
 *
 * Example
 * encrypted = 'VTAOG', k = 2
 * output = 'TRYME'
 * Explanation: V 向左2次 -> T, T -> R, A -> Y, O -> M, G -> E
 *
 * Constraints:
 * 1 <= |encrypted| <= 10^5
 * 1 <= k <= 10^5
 * encrypted[i] 属于 ascii['A'-'Z']
 */
public class AMZN_2022_Cipher_Alphabet_Wheel {

    /**
     * 1. 把逆时针换算成顺时针, 注意需要 % 26
     */
    public String decrypt(String encrypted, int k) {
        if (encrypted == null || encrypted.length() == 0 || k % 26 == 0) {
            return encrypted;
        }

        k = 26 - (k % 26); // 转换成 顺时针 - 别忘了 % 26

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {
            char encrypted_char = encrypted.charAt(i);

            // 注意: 这里不能写成 (encrypted_char + k) % 26, 因为 encrypted_char 是在 256位 ascii 中, 而不是在 26位字母中
            // 必须要写 (char)
            // 也必须要写 % 26, 例如当 encrypted_char = 'V', k = 24 的时候
            char decrypted_char = (char) ('A' + (encrypted_char - 'A' + k) % 26);

            sb.append(decrypted_char);
        }

        return sb.toString();
    }






    @Test
    public void test1() {
        String encrypted = "VTAOG";
        int k = 2;
        Assert.assertEquals("TRYME", decrypt(encrypted, k));

        encrypted = "VTAOG";
        k = 28;
        Assert.assertEquals("TRYME", decrypt(encrypted, k));
    }
}
