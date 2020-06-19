/*
Medium
#String
 */
package leetcode;

/**
 * 468. Validate IP Address
 *
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 *
 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
 * each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 *
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
 * The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
 * Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address
 * to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
 *
 * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive
 * colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 *
 * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 * Note: You may assume there is no extra space or special characters in the input string.
 *
 * Example 1:
 * Input: "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 * Input: "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class _0468_ValidateIPAddress {

    /**
     * 使用 IP.chars().filter(ch -> ch == '.').count() 读取'.'的数量
     * 另一种方式就是遍历char, 判断是否等于'.'
     *
     * 使用str.split将ip地址分割, limit=-1 表示apply pattern as many time as possible
     * https://www.geeksforgeeks.org/split-string-java-examples/
     *
     * IPv4 - 每个数字在[0,255]之间, 且不可用0开头
     *
     * IPv6 - 最多4位字符, 不可用'-'开头, 且值必须 >= 0
     */
    public String validIPAddress(String IP) {
        String[] ipv4 = IP.split("\\.", -1);
        String[] ipv6 = IP.split(":", -1);

        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            for (String s : ipv4) {
                if (!isIPv4(s)) {
                    return "Neither";
                }
            }
            return "IPv4";
        }

        if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            for (String s : ipv6) {
                if (!isIPv6(s)) {
                    return "Neither";
                }
            }
            return "IPv6";
        }

        return "Neither"; // 注意 别忘了最后这个return
    }

    private boolean isIPv4(String s) {
        try {
            int val = Integer.parseInt(s, 10);
            return String.valueOf(val).equals(s) && // 保证没有leading zeros
                    val >= 0 && val <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isIPv6(String s) {
        if (s.length() > 4) { // 4位, 开头可为0
            return false;
        }
        try {
            int val = Integer.parseInt(s, 16);
            return val >= 0 &&
                    s.charAt(0) != '-'; // 保证不要出现负数
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
