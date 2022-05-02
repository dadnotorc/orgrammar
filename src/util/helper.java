package util;

import java.util.*;

public class helper {
	public static void main(String[] args) {

        String[] ans = getAllSubstrings("1234");
        for (String s: ans) {
            System.out.println("sub:" + s + ".");
        }

//        char a = '9';
//        int k = 18;
//        k %= 10;
//        char b = (char) ('0' + (a - '0' + k) % 9);
//        System.out.println("-0-:"  + b);

//        boolean x = 3 > 2;

//	    int[] a = {1,2,3,4,5};
//	    int[] b = a;
//        b[0] = 6;
//        System.out.println(arrayToString(a));
//
//        int[] c = new int[a.length];
//        System.arraycopy(a, 0, c, 0, a.length);
//        c[1] = 7;
//        System.out.println(arrayToString(a));

//	    char[] chars = new char[5];
//	    Arrays.fill(chars, ' ');
//        System.out.println("[" + String.valueOf(chars) + "]");

//        String str = "//a/b/c/    /d";
//        String newStr = str.replaceAll("\\s+", "");
//        String[] subs = newStr.split("/");
//        for (String s : subs) {
//            System.out.println("-" + s + "-");
//        }
        // 输出 = -abc-


//        System.out.println(-13 % 10);
        // 输出 = -3

//        System.out.println(14 & 13);
        // 输出 = 12

//        System.out.println(7& 2);
        // 输出 = 2

        // 14 = 1110;
//        System.out.println((14 >> 0) + " : " + ((14 >> 0) & 1));
//        System.out.println((14 >> 1) + " : " + ((14 >> 1) & 1)); // 111 = 7
//        System.out.println((14 >> 2) + " : " + ((14 >> 2) & 1)); // 11 = 3
//        System.out.println((14 >> 3) + " : " + ((14 >> 3) & 1)); // 1 = 1
        // 输出 = 14 : 0  7 : 1   3 : 1    1 : 1

       /* String[] names = {"Alice", "Bob", "Charlie", "Bob", "Alice"};
        Set<String> set = new HashSet<String>(Arrays.asList(names));
//        for (String s : set) { System.out.println(s); }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) { System.out.println(it.next()); }*/
        // 输出 = Bob Alice Charlie


//        String s = "abc".substring(3);
//        System.out.println("\"" + s + "\"");
//        System.out.println("len=" + s.length());

//        Set<String> set = new HashSet<>();
//        if (set.isEmpty())
//            System.out.println("empty");

//        int[] array = {5, 10};
//        swapXOR(array);
//        System.out.println(arrayToString(array));
        // 输出 = [10, 5]

//        String s = "ab";
//        String[] values = s.split(",");
//        System.out.println(values.length + ", val = " + values[0] + ".");
    }

    public boolean isOdd(int n) {
        // &: bitwise operation. 0 & 0 = 0, 1 & 0 = 0, 0 & 1 = 0, 1 & 1 = 1
        // n & 1 == 0 means zero all the bits but leave the least significant bit unchanged
        //  and check if the result is 0. For example:
        // n = 4 -> 0100 & 0001 = 0000
        // n = 5 -> 0101 & 0001 = 0001
        if ((n & 1) == 0) {
            // even
            return false;
        } else {
            // odd
            return true;
        }
    }

    // input不能是int, 因为传递的是值, 而不是地址
    public static void swapXOR(int[] value) {
	    int x = value[0];
	    int y = value[1];

	    /*
	    假设 x = 5 -> 0101; y = 10 -> 1010
	    x ^ y = 1111
	    1111 ^ x = 1111 ^ 0101 = 1010
	    1111 ^ y = 1111 ^ 1010 = 0101
	     */

	    int xor = x ^ y;
	    x = xor ^ x;
	    y = xor ^ y;

        value[0] = x;
        value[1] = y;
    }


    public static boolean compareStringArrays(String[] s1, String[] s2) {
        if (s1 == s2)
            return true;

        if (s1 == null || s2 == null)
            return false;

        if (s1.length != s2.length)
            return false;

        for (int i = 0; i < s1.length; i++) {
            if (!s1[i].equals(s2[i])) {
                return false;
            }
        }

        return true;
    }

    public static class Node<T> {
	    T data;
	    Node<T> next;

	    Node(T data, Node<T> next) {
	        this.data = data;
	        this.next = next;
        }

        Node(T data) {
	        this.data = data;
	        this.next = null;
        }
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i + 1 < arr.length)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String listToString(ListNode n) {
	    StringBuilder sb = new StringBuilder();
	    while (n != null) {
	        sb.append(n.val);
	        if (n.next != null)
	            sb.append(" -> ");
	        else
	            sb.append(" -> NULL");
	        n= n.next;
        }
	    return sb.toString();
    }

    public static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));

            if (i + 1 < size)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        // 如果 n = 2 ^ x, n 的二进制值为 10000, n-1 为 01111
        return  ((n & (n-1)) == 0);
    }


    /**
     * s = "abc". 所以 substring 个数 = 3 + 2 + 1 + 1
     * a, ab, abc | b, bc | c | ""
     * "" - 注意 "" 也是一个substring
     *
     * 时间 O(n ^ 2) - 两层循环
     *
     * 如果用的是 substring(), 而不是 SB, substring()本身要 O(n), 所以整体是 O(n ^ 3)
     */
    public static String[] getAllSubstrings(String s) {

        int n = s.length();

        ArrayList<String> list = new ArrayList<>();

        list.add(""); // "" 也是一个substring

        StringBuilder sb = new StringBuilder();

        // 正向来 -  - 从长到短
        /*
        for (int i = 0; i < n; i++) {
            sb.setLength(0); // reset the SB

            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                list.add(sb.toString());
            }
        }
        */

        // 反向来 - 从短到长
        for (int i = n - 1; i >= 0; i--) {
            sb.setLength(0);

            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                list.add(sb.toString());
            }

        }

        System.out.println("string size = " + n);
        System.out.println("# of substrings = " + list.size()); // (1 + n) x n / 2 + 1

        return list.toArray(new String[0]);
    }

}
