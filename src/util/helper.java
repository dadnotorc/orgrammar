package util;

import lintcode._0165_MergeSortedLists;

import java.util.List;

public class helper {
	public static void main(String[] args) {
//        String str = "abc";
//
//        String[] subs = str.split("/");
//
//        for (String s : subs) {
//            System.out.print("-" + s + "-");
//        }
//        System.out.print("\n");

//        int[][] d = {{1,2}, {3,4}, {5,6}, {7,8}};
//        System.out.println(d[0][0]);
//        System.out.println(d[0][1]);
//        System.out.println(d[1][0]);
//        System.out.println(d[1][1]);
//
//        int[] d0 = d[0];
//        System.out.println(d0[0]);
//        System.out.println(d0[1]);

        int a = -13;
        System.out.println(a % 10);

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
}
