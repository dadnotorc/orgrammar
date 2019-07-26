package interviews;


import java.util.Arrays;
import java.util.Comparator;

/**
 * For an arbitrary long string, sort the characters in lexicographic order.
 * "helloworld" -> "edhllloorw"
 * "bbaaddezz" ->　"aabbddezz"
 *
 * Amazon phone interview
 */
public class SortString {

    // 方法一
    public static String strNaturalSort(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        char[] chars = input.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }

    // 方法二
    public static String strCustomSort(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        int strLen = input.length();
        Character[] chars = new Character[strLen];
        for (int i = 0; i < strLen; i++) {
            chars[i] = input.charAt(i);
        }

        Arrays.sort(chars, new Comparator<Character>() {
            public int compare(Character c1, Character c2) {
                return Character.compare(c1, c2);
            }
        });

        StringBuilder sb = new StringBuilder(strLen);
        for (Character c: chars) {
            sb.append(c.charValue());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String in = "hellworld";
        String out1 = strNaturalSort(in);
        System.out.println(out1);
        String out2 = strCustomSort(in);
        System.out.println(out1);

        in = "bbaaEEEAAa14ax";
        out1 = strNaturalSort(in);
        out2 = strCustomSort(in);
        System.out.println(out1);
        System.out.println(out2);

    }
}
