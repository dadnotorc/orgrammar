package lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 1380. Log Sorting
 *
 * Given a list of string logs, in which each element representing a log.
 * Each log can be separated into two parts by the first space.
 * The first part is the ID of the log, while the second part is the content of the log.
 *  (The content may contain spaces as well.)
 * The content is composed of only letters and spaces, or only numbers and spaces.
 *
 * Now you need to sort logs by following rules:
 *
 * 1. Logs whose content is letter should be ahead of logs whose content is number.
 * 2. Logs whose content is letter should be sorted by their content in lexicographic order.
 *    And when two logs have same content, sort them by ID in lexicographic order.
 * 3. Logs whose content is number should be in their input order.
 *
 * Assumption:
 *    The total number of logs will not exceed 5000
 *    The length of each log will now exceed 100
 *    Each log's ID is unique.
 */
public class _1380 {
	public String[] logSort(String[] logs) {
        // Write your code here
        if (logs == null || logs.length == 0) {
            return new String[0];
        }
        
        String[] ans = new String[logs.length];
        List<String> list = new ArrayList<String>();
        int ansIndex = logs.length - 1;
        
        for (int i = logs.length - 1; i >= 0; i--) {
            int index = logs[i].indexOf(' ') + 1;
            if (logs[i].charAt(index) >= '0' && logs[i].charAt(index) <= '9') {
                ans[ansIndex--] = logs[i];
            } else {
                list.add(logs[i]);
            }
        }

        /*
        Collections.sort(list, new MyComparator());
         */

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int index1 = o1.indexOf(' ');
                int index2 = o2.indexOf(' ');

                String id1 = o1.substring(0, index1);
                String content1 = o1.substring(index1 + 1);
                String id2 = o2.substring(0, index2);
                String content2 = o2.substring(index2 + 1);

                if (content1.equals(content2)) {
                    return id1.compareTo(id2);
                } else {
                    return content1.compareTo(content2);
                }
            }
        });
        
        ansIndex = 0;
        for (String logEntry : list) {
            ans[ansIndex++] = logEntry;
        }
        
        return ans;
    }
	
	/*
	class MyComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            String str1 = (String)o1;
            String str2 = (String)o2;

            int index1 = str1.indexOf(' ');
            int index2 = str2.indexOf(' ');

            String id1 = str1.substring(0, index1);
            String content1 = str1.substring(index1 + 1);
            String id2 = str2.substring(0, index2);
            String content2 = str2.substring(index2 + 1);

            if (content1.equals(content2)) {
                return id1.compareTo(id2);
            } else {
                return content1.compareTo(content2);
            }
        }
    }
    */
}
