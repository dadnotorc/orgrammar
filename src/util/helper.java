package util;

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

        int[][] d = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println(d[0][0]);
        System.out.println(d[0][1]);
        System.out.println(d[1][0]);
        System.out.println(d[1][1]);

        int[] d0 = d[0];
        System.out.println(d0[0]);
        System.out.println(d0[1]);
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

    public static void log(int[] arr) {
	    System.out.print("array=");
	    for (int a : arr) {
            System.out.print(a + ", ");
        }
        System.out.print("\n");
    }
}
