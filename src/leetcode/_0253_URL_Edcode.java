package leetcode;

import java.util.List;
import java.util.TreeMap;

/**
 * 253 · URL Edcode
 * Easy
 * #String
 *
 * Given a string representing the URL host base_url, and an array representing query parameters query_params,
 * you need to return the complete URL with query parameters.
 * The query parameters are composed of arrays containing two elements. The first element represents the parameter,
 * and the second element represents the value of the parameter.
 * Now you need to concatenate the two parts to get the complete URL. ? is used for splicing between base_url
 * and query parameter string, = is used for splicing between query parameter parameters and values, and & is used for
 * splicing between query parameters. The query parameters need to be sorted lexicographically.
 *
 * The length of the array query_params representing the query parameters is within 100.
 * The data will not contain special characters that need to be escaped.
 *
 * Example 1:
 * Input: "https://www.lintcode.com/problem", [["typeId","2"]]
 * Output: "https://www.lintcode.com/problem?typeId=2"
 *
 * Example 2:
 * Input: "https://translate.google.cn/", [["sl","en"],["tl","zh-CN"],["text","Hello"],["op","translate"]]
 * Output: "https://translate.google.cn/?op=translate&sl=en&text=Hello&tl=zh-CN"
 * Explanation:
 *
 * The parameters need to be concatenated in lexicographic order, so you need to concatenate the op part first,
 * then the sl part, then the text part, and finally the tl part.
 */
public class _0253_URL_Edcode {

    /**
     * 使用 TreeMap 记录所有的query params
     *
     * 假设 query_params 里每组 query 都有两个值 (param name : param value)
     */
    public String urlencode(String base_url, List<List<String>> query_params) {
        if (base_url == null || base_url.length() < 1) {
            return base_url;
        }

        TreeMap<String, String> map = new TreeMap<>();

        for(List<String> query : query_params) {
            if (query.size() != 2) {
                System.out.println("invalid query");
            } else {
                map.put(query.get(0), query.get(1));
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(base_url).append("?");

        for (String key : map.keySet()) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
