/*
Medium
#Backtracking, #Recursion, #String
Amazon
FAQ
 */
package lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 426. Restore IP Addresses
 *
 * Given a string containing only digits, restore it by
 * returning all possible valid IP address combinations.
 *
 * (Your task is to add three dots to this string to make
 * it a valid IP address. Return all possible IP address.)
 *
 * Notice
 * - You can return all valid IP address in any order.
 *
 * Example 1:
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * Explanation: ["255.255.111.35", "255.255.11.135"] will be accepted as well.
 *
 * Example 2:
 * Input: "1116512311"
 * Output: ["11.165.123.11","111.65.123.11"]
 */
public class _0426_RestoreIPAddresses {

    /**
     * 解法1 - 3重循环 暴力解法?
     * 每次找到4组长度为1-3的子字符串, 组成ip地址的4个部分
     *
     * time:  O(3^3) = O(1) 每重循环找3次, 共3重. 共检查27种组合. 前3组字符串确定后, 第4组也能确定
     * space: O(1)
     */
    public List<String> restoreIpAddresses_Iterative(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12)
            return ans;

        int len = s.length();
        String s1, s2, s3, s4;

        for (int i = 1; i < 4 && i < len - 2; i++) {
            s1 = s.substring(0, i);
            if (!isValid_Iterative(s1)) continue;
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                s2 = s.substring(i, j);
                if (!isValid_Iterative(s2)) continue;
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    s3 = s.substring(j, k);
                    s4 = s.substring(k, len);
                    if (isValid_Iterative(s3) && isValid_Iterative(s4)) {
                        ans.add(s1 + "." + s2 + "." + s3 + "." +s4);
                    }
                }
            }
        }
        return ans;
    }


    private boolean isValid_Iterative(String str) {
        if (str.length() == 0 || str.length() > 3) // 针对最后一组substring s4
            return false;

        if (str.length() > 1 && str.charAt(0) == '0')
            return false;

        if (str.length() == 3 && Integer.parseInt(str) > 255)
            return false;

        return true;
    }


    /**
     * 解法2 - DFS Recursion
     * 有效ip地址: 4个数字部分, 每部分数字范围是[0,255], 可以是1位数/2位数/3位数
     * a) 如果是0开头, 且位数大于1 -> 无效
     * b) 如果位数是3, 且数值大于255 -> 无效
     *
     * 递归定义: 每找到一个有效ip地址(String), 将其加入答案列表(List<String>)
     * 递归出口: 如果已找到4个有效数字部分(int), 且ip地址长度 = s.length() + 3, 将地址加入答案中
     * 递归拆解: 每次从index下标(int)开始, 读取长度为1/2/3的数字, 如果数字有效, 将其写入当前ip地址,
     *          并将ip地址, 新index下标, 当前有效数字部分个数, 连同原字符串, 以及答案列表, 一起传入下一层递归
     */
    public List<String> restoreIpAddresses_Recursion(String s) {
        if (s == null || s.length() < 4 || s.length() > 12)
            return new ArrayList<>();

        List<String> ans = new ArrayList<>();
//        dfs(s, ans, 0, "", 0); // 使用String记录每组ip地址
        dfs(s, ans, 0, new ArrayList<String>()); // 使用List<String>记录每组ip地址
        return ans;
    }

    /* 使用List<String>记录每组ip地址 */
    private void dfs(String s, List<String> ans, int index, List<String> ipAddrParts) {
        if (ipAddrParts.size() == 4) {
            if (index != s.length())
                return;

            StringBuilder sb = new StringBuilder();
            for (String tmp : ipAddrParts) {
                sb.append(tmp);
                sb.append(".");
            }
            sb.deleteCharAt(sb.length() - 1); // 去掉多余的"."
            ans.add(sb.toString());
            return;
        }

        for (int endIndex = index + 1; endIndex < index + 4 && endIndex <= s.length(); endIndex++) { // i 为substring的末端 exclusive
            String sub = s.substring(index, endIndex);
            if (isValid_Recursion(sub)) {
                ipAddrParts.add(sub);
                dfs(s, ans, endIndex, ipAddrParts);
                ipAddrParts.remove(ipAddrParts.size() - 1); // 别忘了删掉
            }
        }
    }

    /* 使用String记录每组ip地址 */
    private void dfs(String s, List<String> ans, int index, String ipAddr, int validParts) {

        if (validParts == 4 && ipAddr.length() == s.length() + 3) {
            ans.add(ipAddr);
            return; // 别忘了退出
        }

        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) // 别忘了判断是否越界
                break;
            String sub = s.substring(index, index + i);
            if (isValid_Recursion(sub)) {
                dfs(s, ans, index+i,
                        validParts == 0 ? sub : ipAddr + "." + sub, validParts+1);
            }
        }
    }

    private boolean isValid_Recursion(String str) {
        if (str.length() > 1 && str.charAt(0) == '0')
            return false;

        if (str.length() == 3 && Integer.parseInt(str) > 255)
            return false;

        return true;
    }

}
