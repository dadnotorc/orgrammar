package interviews;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Promotion for purchasing a secret combination of fruits - fresh promotion
 *
 * Promotion changes daily, need to use a code list for easy combination change. The code list contains groups of fruits.
 * Both the order of the groups within the code list and the order of the fruits within the groups matter.
 * However, between the groups of fruits, any number, and type of fruit is allowable.
 * The term "anything" is used to allow for any type of fruit to appear in that location within the group.
 *
 * Consider the following secret code list: [[apple, apple], [banana, anything, banana]]
 * Based on the above secret code list, a customer who made either of the following purchases would win the prize:
 * orange, apple, apple, banana, orange, banana
 * apple, apple, orange, orange, banana, apple, banana, banana
 *
 * Write an algorithm to output 1 if the customer is a winner else output 0.
 *
 * The input to the function/method consists of two arguments:
 * 1. codeList, a list of lists of strings representing the order, and grouping of specific fruits that
 *    must be purchased in order to win the prize for the day.
 * 2. shoppingCart, a list of strings representing the order in which a customer purchases fruit.
 *
 * Output - Return an integer 1 if the customer is a winner else return 0.
 *
 * Note
 * 'anything' in the codeList represents that any fruit can be ordered in place of 'anything' in the group.
 * 'anything' has to be something, it cannot be "nothing."
 * 'anything' must represent one and only one fruit.
 * If secret code list is empty then it is assumed that the customer is a winner.
 *
 * Example 1:
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 *        shoppingCart = [orange, apple, apple, banana, orange, banana]
 * Output: 1
 * Explanation:
 * codeList contains two groups - [apple, apple] and [banana, anything, banana].
 * The second group contains 'anything' so any fruit can be ordered in place of 'anything' in the shoppingCart.
 * The customer is a winner as the customer has added fruits in the order of fruits in the groups and
 * the order of groups in the codeList is also maintained in the shoppingCart.
 *
 * Example 2:
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 *        shoppingCart = [banana, orange, banana, apple, apple]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the customer has added the fruits in order of groups but
 * group [banana, orange, banana] is not following the group [apple, apple] in the codeList.
 *
 * Example 3:
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 *        shoppingCart = [apple, banana, apple, banana, orange, banana]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the customer has added the fruits in an order
 * which is not following the order of fruit names in the first group.
 *
 * Example 4:
 *
 * Input: codeList = [[apple, apple], [apple, apple, banana]]
 *        shoppingCart = [apple, apple, apple, banana]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the first 2 fruits form group 1, all three fruits would form group 2,
 * but can't because it would contain all fruits of group 1.
 */
public class AMZN_2021_Fruit_Combination {

    /**
     * 参数是 array
     *
     * 2 个指针: 1 - 购物车指针, 2 - 指向某个group的指针
     *
     * 每次将一个 group 提出来对比当前 shopping cart + cartIdx.
     * - 如果满足, 继续下一个 group, 并将 cartIdx += group.length
     * - 如果不满足, 结束
     */
    public int isWinner_array(String[][] codeList, String[] shoppingCart) {
        if (codeList == null || codeList.length == 0) { return 1; }
        if (shoppingCart == null || shoppingCart.length == 0) { return 0; }

        int groupIdx = 0; // codeList 数组的 index, 表示第几个 group
        int cartIdx = 0; // shopping cart 的 index

        while (cartIdx < shoppingCart.length && groupIdx < codeList.length) {
            // 只有当一整个 group 被匹配后, 购物车这边才会一次跳过多个下标
            // 不然就得每次一步一步的跳

            if(checkGroup(shoppingCart, cartIdx, codeList[groupIdx])) {
                cartIdx += codeList[groupIdx].length;
                groupIdx++;
            } else {
                cartIdx++;
                // 未找到匹配, groupIdx不移动, 返回起点重新来
            }
        }

        return groupIdx == codeList.length ? 1 : 0;
    }

    private boolean checkGroup(String[] shoppingCart, int cartIdx, String[] group) {
        for (String code : group) {
            if (cartIdx < shoppingCart.length &&
                    (code.equals("anything") || shoppingCart[cartIdx].equals(code))) {
                cartIdx++;
            } else {
                return false;
            }
        }

        return true;
    }


    /**
     * 参数是 list
     *
     * 也是 2个指针: 1 - 购物车指针, 2 - 指向当前 group 内具体物品的指针
     *
     * 每次将当前的 group 提出来逐个比对
     * - 遇到物品是 anything 或者与 购物车内物品相同时, 两个指针均 ++
     * - 否则 有两种情况
     *    - 之前已有部分匹配 - 将 group 内指针 reset 到 0, 从新匹配.  注意 这时, 购物车指针不动, 因为它还没跟 group 的首位比对过
     *    - 从未有任何匹配 - 购物车指针后移 (group 内指针不用理会, 因为它还停留在 0 处)
     */
    public int isWinner_list(List<List<String>> codeList, List<String> shoppingCart) {
        if (codeList == null || codeList.size() == 0) { return 1; }
        if (shoppingCart == null || shoppingCart.size() == 0) { return 0; }

        int cartIndex = 0;

        for (List<String> group : codeList) {
            int toBeMatched = 0;
            while (toBeMatched < group.size()) {
                if (cartIndex == shoppingCart.size()) { // 没买够
                    return 0;
                }

                String curCode = group.get(toBeMatched);
                if (curCode.equals("anything") || curCode.equals(shoppingCart.get(cartIndex))) {
                    toBeMatched++;
                    cartIndex++;
                } else {
                    if (toBeMatched != 0) { // group = [a,b]; cart = [a,a,b]; group 指到 b, cart 指到第二个 a
                        toBeMatched = 0; // group 指针 reset, 但是 cart 指针保持不动, 因为要将其与 group 首位再比较
                    } else {
                        cartIndex++; // 未找到任何匹配时, cart 指针跳过
                    }
                }
            }

            // while 循环结束, 说明当前 group 全被匹配, 进入下一个 group 继续
        }

        return 1;
    }





    @Test
    public void test1() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "apple"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "banana"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana"));
        Assert.assertEquals(1, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "apple"},
                {"banana", "anything", "banana"}
        };
        String[] cart_arr = new String[] {"orange", "apple", "apple", "banana", "orange", "banana"};
        Assert.assertEquals(1, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test2() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "apple"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "banana"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("banana", "orange", "banana", "apple", "apple"));
        Assert.assertEquals(0, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "apple"},
                {"banana", "anything", "banana"}
        };
        String[] cart_arr = new String[] {"banana", "orange", "banana", "apple", "apple"};
        Assert.assertEquals(0, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test3() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "apple"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "banana"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "banana", "orange", "banana"));
        Assert.assertEquals(0, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "apple"},
                {"banana", "anything", "banana"}
        };
        String[] cart_arr = new String[] {"apple", "banana", "apple", "banana", "orange", "banana"};
        Assert.assertEquals(0, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test4() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "apple"));
        List<String> g1 = new ArrayList<>(Arrays.asList("apple", "apple", "banana"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("apple", "apple", "apple", "banana"));
        Assert.assertEquals(0, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "apple"},
                {"apple", "apple", "banana"}
        };
        String[] cart_arr = new String[] {"apple", "apple", "apple", "banana"};
        Assert.assertEquals(0, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test5() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "banana"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "apple"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("banana", "banana", "apple", "apple", "banana", "banana", "banana", "apple"));
        Assert.assertEquals(1, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "banana"},
                {"banana", "anything", "apple"}
        };
        String[] cart_arr = new String[] {"banana", "banana", "apple", "apple", "banana", "banana", "banana", "apple"};
        Assert.assertEquals(1, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test6() {
        List<String> g0 = new ArrayList<>(Arrays.asList("anything", "anything"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
        }};
        List<String> shoppingCart = new ArrayList<>(List.of("banana"));
        Assert.assertEquals(0, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"anything", "anything"},
        };
        String[] cart_arr = new String[] {"banana"};
        Assert.assertEquals(0, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test7() {
        List<String> g0 = new ArrayList<>(Arrays.asList("anything", "anything"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
        }};
        List<String> shoppingCart = new ArrayList<>(List.of("banana", "banana", "banana"));
        Assert.assertEquals(1, isWinner_list(codeList, shoppingCart));
        /* ---- */

        String[][] code_arr = new String[][] {
                {"anything", "anything"},
        };
        String[] cart_arr = new String[] {"banana", "banana", "banana"};
        Assert.assertEquals(1, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test8() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "banana"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "apple"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "apple", "banana", "banana", "apple"));
        Assert.assertEquals(0, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "banana"},
                {"banana", "anything", "apple"}
        };
        String[] cart_arr = new String[] {"banana", "apple", "orange", "apple", "banana", "banana", "apple"};

        Assert.assertEquals(0, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test9() {
        List<String> g0 = new ArrayList<>(Arrays.asList("apple", "banana"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "apple"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "apple", "banana", "banana", "apple", "apple"));
        Assert.assertEquals(1, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"apple", "banana"},
                {"banana", "anything", "apple"}
        };
        String[] cart_arr = new String[] {"banana", "apple", "orange", "apple", "banana", "banana", "apple", "apple"};

        Assert.assertEquals(1, isWinner_array(code_arr, cart_arr));
    }

    @Test
    public void test10() {
        List<String> g0 = new ArrayList<>(Arrays.asList("anything", "apple"));
        List<String> g1 = new ArrayList<>(Arrays.asList("banana", "anything", "banana"));
        List<List<String>> codeList = new ArrayList<>() {{
            add(g0);
            add(g1);
        }};
        List<String> shoppingCart = new ArrayList<>(Arrays.asList("orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"));
        Assert.assertEquals(1, isWinner_list(codeList, shoppingCart));

        /* ---- */

        String[][] code_arr = new String[][] {
                {"anything", "apple"},
                {"banana", "anything", "banana"}
        };
        String[] cart_arr = new String[] {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};

        Assert.assertEquals(1, isWinner_array(code_arr, cart_arr));
    }
}
