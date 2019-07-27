package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1108. Defanging an IP Address
 * Easy
 *
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * A defanged IP address replaces every period "." with "[.]".
 *
 * Example 1:
 * Input: address = "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 *
 * Example 2:
 * Input: address = "255.100.50.0"
 * Output: "255[.]100[.]50[.]0"
 *
 * Constraints:
 * - The given address is a valid IPv4 address.
 */
public class _1108_DefangingIPAddr {
    public String defangIPaddr(String address) {

        String[] subs = address.split("\\.");
        String ans = "";
        for (int i = 0; i < subs.length - 1; i++) {
            ans += subs[i] + "[.]";
        }

        return ans + subs[subs.length - 1];
    }

    @Test
    void test1() {
        String address = "1.1.1.1";
        String expected = "1[.]1[.]1[.]1";
        String actual = new _1108_DefangingIPAddr().defangIPaddr(address);
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        String address = "255.100.50.0";
        String expected = "255[.]100[.]50[.]0";
        String actual = new _1108_DefangingIPAddr().defangIPaddr(address);
        assertEquals(expected, actual);
    }
}
