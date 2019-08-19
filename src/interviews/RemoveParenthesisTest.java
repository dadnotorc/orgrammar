package interviews;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

class RemoveParenthesisTest {

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