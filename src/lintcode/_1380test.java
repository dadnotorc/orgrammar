package lintcode;

import org.junit.jupiter.api.Test;
import util.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class _1380test {

    @Test
    void test1() {
        String[] logs = {
                "zo4 4 7",
                "a100 Act zoo",
                "a1 9 2 3 1",
                "g9 act car"};
        String[] expected = {
                "a100 Act zoo",
                "g9 act car",
                "zo4 4 7",
                "a1 9 2 3 1"};
        String[] actual = (new _1380().logSort(logs));
        // "Act zoo" < "act car", so the output is as above.
        assertTrue(helper.compareStringArrays(expected, actual));
    }

    @Test
    void test2() {
        String[] logs = {
                "zo4 4 7",
                "a100 Actzoo",
                "a100 Act zoo",
                "Tac Bctzoo",
                "Tab Bctzoo",
                "g9 act car"};
        String[] expected = {
                "a100 Act zoo",
                "a100 Actzoo",
                "Tab Bctzoo",
                "Tac Bctzoo",
                "g9 act car",
                "zo4 4 7"};
        String[] actual = (new _1380().logSort(logs));
        // Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ",
        //  so" Tab Bctzoo "< Tac Bctzoo".
        // Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".
        assertTrue(helper.compareStringArrays(expected, actual));
    }


}
