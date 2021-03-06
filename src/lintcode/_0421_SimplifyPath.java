/*
Medium
#String, #Stack
Facebook, Microsoft
FAQ
 */
package lintcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

/**
 * 421. Simplify Path
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * In a UNIX-style file system, a period . refers to the current directory.
 * Furthermore, a double period .. moves the directory up a level.
 *
 * The result must always begin with /, and there must be only a single /
 *  between two directory names. The last directory name (if it exists)
 *  must not end with a trailing /. Also, the result must be the shortest
 *  string representing the absolute path.
 *
 * - Did you consider the case where path is "/../"?
 *   In this case, you should return "/".
 * - Another corner case is the path might contain multiple slashes '/'
 *    together, such as "/home//foo/".
 *   In this case, you should ignore redundant slashes and return "/home/foo".
 *
 * Example 1:
 * Input: "/home/"
 * Output: "/home"
 *
 * Example 2:
 * Input: "/a/./../../c/"
 * Output: "/c"
 * Explanation: "/" has no parent directory, so "/../" equals "/".
 */
public class _0421_SimplifyPath {

    /**
     * 1. 使用split()分割folder, regex = "/+" 一个或多个 "/" or "//"
     * 2. 把有效的目录push进stack, 把无效的目录pop出来
     *
     * 不要移除空白 (path = path.replaceAll("\\s+", "")), 因为目录名可以包含空格, 例如 /my\ folder/
     * 注意不能使用StringBuilder的reverse(), 因为会把目录名也反转
     */
    public String simplifyPath(String path) {
        // write your code here
        if (path == null || path.length() == 0) {
            return "";
        }

        // divide into substrings divided by "/" or "//"
        String[] subs = path.split("/+");

        // 使用stack, FILO
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 1; i < subs.length; i++) {
            String s = subs[i];

            if (s.equals("") || s.equals(".")) { //遇到"/./"或者"//", 跳过
                continue;
            } else if (s.equals("..")) { //遇到/../, 去掉stack最上一层
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }

        String ans = "";

        while (!stack.isEmpty())
            ans = "/" + stack.pop() + ans; //从后往前加

        return ans.equals("") ? "/" : ans;
    }

    @Test
    public void test1() {
        String in = "/home/";
        String expected = "/home";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String in = "/a/./../../c/";
        String expected = "/c";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        String in = "/../";
        String expected = "/";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        String in = "/a//b/";
        String expected = "/a/b";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test5() {
        String in = "/a/./../";
        String expected = "/";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test6() {
        String in = "/a/b/c/../";
        String expected = "/a/b";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }

    @Test
    public void test7() {
        String in = "/a/    /b/../";
        String expected = "/a/b";
        String actual = new _0421_SimplifyPath().simplifyPath(in);
        //System.out.println("\"" + actual + "\"");
        assertEquals(expected, actual);
    }
}
