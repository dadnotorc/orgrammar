package interviews;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertTrue;

/**
 * Given string "-(a+b)+c-(d-e)"
 * Remove parenthesis, it becomes "-a-b+c-d+e"
 *
 * d-(a-(b-c)) => d-a+b-c
 *
 * Amazon phone interview
 */
public class RemoveParenthesis {

    public String convert(String in) {
        if (in == null || in.length() == 0) {
            return "";
        }

        Deque<Boolean> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        boolean isPlus = true;
        boolean lastOp = true; // true = +, false = -

        for (char c : in.toCharArray()) {
            if (!stack.isEmpty()) {
                isPlus = stack.peek();
            }

            if (c == '(') {
                stack.push(lastOp);
            } else if (c == ')') {
                stack.pop();
                isPlus = true; // 别忘了 reset
            } else if (c == '+') {

                if (isPlus) {
                    sb.append('+');
                    lastOp = true;
                } else {
                    sb.append('-');
                    lastOp = false;
                }

            } else if (c == '-') {

                if (isPlus) {
                    sb.append('-');
                    lastOp = false;
                } else {
                    sb.append('+');
                    lastOp = true;
                }

            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }


    @Test
    public void test1() {
        String in = "-(a+b)+c-(d-e)";
        String exp = "-a-b+c-d+e";
        String act = new RemoveParenthesis().convert(in);
        System.out.println(act);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test2() {
        String in = "a-(b-c+d-(e+f)-g";
        String exp = "a-b+c-d+e+f+g";
        String act = new RemoveParenthesis().convert(in);
        System.out.println(act);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test3() {
        String in = "d-(a-(b-c))";
        String exp = "d-a+b-c";
        String act = new RemoveParenthesis().convert(in);
        System.out.println(act);
        assertTrue(exp.equals(act));
    }

    @Test
    public void test4() {
        String in = "a-(b-(c+d)-(e+f)-g+(h-i-(j+k-l)))";
        String exp = "a-b+c+d+e+f+g-h+i+j+k-l";
        String act = new RemoveParenthesis().convert(in);
        System.out.println(act);
        assertTrue(exp.equals(act));
    }

}
