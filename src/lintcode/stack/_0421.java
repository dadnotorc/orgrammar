package lintcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 421. Simplify Path
 * Medium
 * Facebook, Microsoft
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
public class _0421 {

    public String simplifyPath(String path) {
        // write your code here
        if (path == null || path.length() == 0) {
            return "";
        }

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

        while (!stack.isEmpty()) {
            //System.out.println(stack);
            ans = "/" + stack.pop() + ans; //从后往前加
            //System.out.println(ans);
        }

        return ans.equals("") ? "/" : ans;
    }
}
