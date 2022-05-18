package lintcode;

/**
 * 2256 · Replace the contents of a string
 * Easy
 * #StringBuffer
 *
 * Write code to replace the content of a string using the relevant methods in the StringBuffer class.
 *
 * There is an updateString method in the Solution class of this question.
 * This method has two String parameters str1 and str2,
 * str1 represents the original string and str2 represents the content to be replaced.
 * The method replaces the contents of the original string with the contents of the specified index range,
 * and returns the replaced string with the StringBuffer type.
 *
 * Requirement: Replace the contents of the string index 4 to 7, including 4 but not 7.
 * str1 should be greater than or equal to 7 and str2 should be greater than or equal to 1.
 *
 * - Note the index position of the string
 *
 * Sample 1
 * input: str1 = www.abc.com, str2 = baidu
 * output: www.baidu.com
 *
 * Sample 2
 * input: str1 = www.def.com, str2= mi
 * output: www.mi.com
 *
 * Challenge
 * We can also use the relevant methods in the StringBuilder class to replace strings.
 * The usage of the StringBuilder class and the StringBuffer class are basically the same,
 * the difference between them is that the StringBuffer is thread-safe and the StringBuilder is not.
 */
public class _2256_Replace_string_contents {

    public static void main(String[] args) {
        Solution_2256 solution = new Solution_2256();
        StringBuffer str = solution.updateString(args[0], args[1]);
        System.out.println(str);
    }
}

class Solution_2256 {
    /**
     * 使用 delete() + insert()
     */
    public StringBuffer updateString(String str1, String str2) {
        if (str1 == null || str1.length() < 7 || str2 == null || str2.length() < 1) {
            return new StringBuffer(str1);
        }

        StringBuffer sb = new StringBuffer(str1);
        sb.delete(4, 7); // start - inclusive; end – exclusive.
        sb.insert(4, str2);

        return sb;
    }


    public StringBuffer updateString_2(String str1, String str2) {
        if (str1 == null || str1.length() < 7 || str2 == null || str2.length() < 1) {
            return new StringBuffer(str1);
        }

        StringBuffer sb = new StringBuffer(str1);

        return sb.replace(4, 7, str2); // start - inclusive; end – exclusive.
    }
}
