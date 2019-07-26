package lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 1484. The Most Frequent word
public class _1484 {
	
    /**
     * @param s:            a string
     * @param excludewords: a dict
     * @return: the most frequent word
     */
    public String frequentWord(String s, Set<String> excludewords) {
        // Write your code here
        Map<String, Integer> ans = new HashMap<String, Integer>();
        String res = "";
        int now = -1;
        StringBuilder p = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z') == false)
                p.setCharAt(i, '#');
        s = p.toString();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                String tmp = "";
                while (i < s.length() && s.charAt(i) != '#') {
                    tmp += s.charAt(i);
                    i++;
                }
                if (tmp.length() > 0 && (excludewords.contains(tmp) == false)) {
                    int x;
                    if (ans.containsKey(tmp))
                        x = ans.get(tmp).intValue() + 1;
                    else
                        x = 1;
                    ans.put(tmp, Integer.valueOf(x));
                    if (x > now) {
                        now = x;
                        res = tmp;
                    } else if (x == now && tmp.compareTo(res) < 0) {
                        res = tmp;
                    }
                }
            }
        }
        return res;
    }
}