package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class _1108Test {

    @Test
    void test1() {
        String address = "1.1.1.1";
        String expected = "1[.]1[.]1[.]1";
        String actual = new _1108().defangIPaddr(address);
        assertEquals(expected, actual);
    }

    @Test
    void test2() {
        String address = "255.100.50.0";
        String expected = "255[.]100[.]50[.]0";
        String actual = new _1108().defangIPaddr(address);
        assertEquals(expected, actual);
    }

}