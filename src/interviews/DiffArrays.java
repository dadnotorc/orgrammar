package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DiffArrays {

    /**
     * Find elements which are present in first array and not in second
     *
     * Input:
     * a[] = {1, 2, 3, 4, 5, 10};
     * b[] = {2, 3, 1, 0, 5};
     * Output : 4 10
     * because 4 and 10 are present in first array, but not in second array.
     *
     * @param a first array
     * @param b second array
     * @return the elements that are present in a, but not in b
     */
    public int[] diffIntArrays(int[] a, int[] b) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();

        for (int j = 0; j < b.length; j++) {
            set.add(b[j]);
        }

        for (int i = 0; i < a.length; i++) {
            if (!set.contains(a[i])) {
                ans.add(a[i]);

            }
        }

        int[] result = new int[ans.size()];
        int i = 0;
        for (int num : ans) {
            result[i++] = num;
        }

        return result;
    }


    // Test
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 10};
        int[] b = {2, 3, 1, 0, 5};

        DiffArrays diff = new DiffArrays();
        int[] ans = diff.diffIntArrays(a, b);

        System.out.print("diff is:\n");
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + ", ");
        }
    }

}
