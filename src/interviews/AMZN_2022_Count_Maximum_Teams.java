package interviews;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Amazon OA - 2022 年 1月
 *
 * Given an skill array example - [3,4,3,1,6,5] and teamSize = 3 and maxDiff =2.
 *
 * Try to form maximum teams size 3 each, e.g., here 2
 * - team1 = [3,3,1] ( As max skill (3) differ from min. skill(1)=3-1 = 2) - so this is valid combination.
 * - team2 = [4,6,5] (as max skill(6) - min.skill(4)=2) - so this one is valid too.
 *
 * Maximum two combination or teams can be form
 * Output: 2
 */
public class AMZN_2022_Count_Maximum_Teams {

    /**
     * sort the array and then check for i and i+teamSize-1 candidate
     * - if diff is <= 2 then cnt++, and move i to i + teamSize
     * - else i++
     *
     * time:  O(nlogn) - sorting + loop
     * space: O(n)
     */
    public int countMaximumTeams(List<Integer> skill, int teamSize, int maxDiff) {
        if (skill == null || skill.size() < teamSize) {
            return 0;
        }

        int ans = 0;

        int[] people = sort(skill); // sort list and convert to array
        int n = people.length;

        int i = 0;
        while (i <= n - teamSize) {
            if (people[i + teamSize - 1] - people[i] <= maxDiff) { // 考察 teamSize 个队员中, 能力差的最大值 是否满足 maxDiff
                ans++;
                i = i + teamSize;
            } else {
                i++;
            }
        }

        return ans;
    }

    public int[] sort(List<Integer> skill) {
        int[] array = new int[skill.size()];
        int index = 0;
        for (int i : skill) {
            array[index++]  = i;
        }

        Arrays.sort(array);
        return array;
    }

    @Test
    public void test1() {
        List<Integer> skill = new ArrayList<>(Arrays.asList(3,4,3,1,6,5));
        org.junit.Assert.assertEquals(2, countMaximumTeams(skill, 3, 2));

        skill = new ArrayList<>(Arrays.asList(1,3));
        org.junit.Assert.assertEquals(0, countMaximumTeams(skill, 3, 2));

        skill = new ArrayList<>(Arrays.asList(3,4,3,1,5,5));
        org.junit.Assert.assertEquals(2, countMaximumTeams(skill, 3, 2));

        skill = new ArrayList<>(Arrays.asList(3,4,3,1,5,7));
        org.junit.Assert.assertEquals(1, countMaximumTeams(skill, 3, 2));
    }
}
