/*
Medium
#Hash Table
Amazon, Facebook, Google, Uber
 */
package lintcode;

import java.util.*;

/**
 * 1186. Encode and Decode TinyURL
 *
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://lintcode.com/problems/design-tinyurl and it returns a short
 * URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and
 * the tiny URL can be decoded to the original URL.
 *
 * Example
 * Example 1:
 *
 * Input："https://lintcode.com/problems/design-tinyurl"
 * Output：http://tinyurl.com/4e9iAk
 *
 * Example 2:
 * Input："https://lintcode.com/problems/solution"
 * Output：http://tinyurl.com/5d7fiu
 */
public class _1186_EncodeAndDecodeTinyURL {

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.decode(codec.encode(url));

    /**
     * 解法3 - 使用randomize
     */
    Map<String, String> map = new HashMap<>();
    Map<String, String> revMap = new HashMap<>();

    int ENCODE_LENGTH = 6;
    String BASE_HOST = "http://tinyurl.com/";
    String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int charSetLen = charSet.length();

    public String encode(String longUrl) {
        if (revMap.containsKey(longUrl))
            return BASE_HOST + revMap.get(longUrl);

        String encode = null;
        Random r = new Random();

        do { // 用while循环保证随机出来的encode没有被用过
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < ENCODE_LENGTH; i++) {
                sb.append(charSet.charAt(r.nextInt(charSetLen)));
            }

            encode = sb.toString();

        } while (map.containsKey(encode));

        map.put(encode, longUrl);
        revMap.put(longUrl, encode);

        return BASE_HOST + encode;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace(BASE_HOST, ""));
    }

    /**
     * 解法2 - 使用hash code
     *
     * 缺点:
     * 1. 仍是只有digits, 限制了可存储网站的数量
     */
    Map<Integer, String> map_2 = new HashMap<>();
    Map<String, Integer> revMap_2 = new HashMap<>();

    public String encode_2(String longUrl) {
        if (revMap_2.containsKey(longUrl))
            return BASE_HOST + revMap_2.get(longUrl);

        int hash = longUrl.hashCode();
        map_2.put(hash, longUrl);
        revMap_2.put(longUrl, hash);
        return BASE_HOST + hash;
    }

    public String decode_2(String shortUrl) {
        return map_2.get(Integer.parseInt(shortUrl.replace(BASE_HOST, "")));
    }


    /**
     * 解法1 - 使用counter
     *
     * 此解法缺点:
     * 1. 反复加入同一个longUrl时, 会产生多个不同的shortUrl
     * 2. 使用digits记录大量网站后, 数字会非常大. 假设shortUrl最长只能6位:
     *    - 只使用digits ->　只能存储一百万个网站 [0,999999]
     *    - 使用digits/小写字母/大写字母 -> shortUrl中每个字符有10+26*2个选择 ->　共能存储(10+26*2)^6=56,800,235,584个网站
     */
    List<String> urls = new ArrayList<>();

    public String encode_counter(String longUrl) {
        urls.add(longUrl);
        return BASE_HOST + (urls.size() - 1); // 新加入的url所在的位置
    }

    public String decode_counter(String shortUrl) {
        int index = Integer.parseInt(shortUrl.replace(BASE_HOST, ""));
        return index < urls.size() ? urls.get(index) : "";
    }
}
