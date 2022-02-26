package lintcode;

import java.util.*;

/**
 * 560 · Friendship Service
 *
 * Support follow & unfollow, getFollowers, getFollowings.
 * Note: the results of getfollow() are sorted.
 *
 * Example 1
 * input:
 * follow(1, 3)
 * getFollowers(1)
 * getFollowings(3)
 * follow(2, 3)
 * getFollowings(3)
 * unfollow(1, 3)
 * getFollowings(3)
 * output:
 * [3]
 * [1]
 * [1,2]
 * [2]
 *
 * Example 2
 * input:
 * follow(1, 2)
 * unfollow(1, 2)
 * getFollowings(1)
 * unfollow(1, 2)
 * getFollowings(1)
 * unfollow(1, 2)
 * follow(1, 2)
 * getFollowings(1)
 * output:
 * []
 * []
 * []
 */
public class _0560_Friendship_Service {
    /*
    用 TreeSet, 而不用 PriorityQueue. 因为后者转成 list 时, 无法保证 sorted 顺序

    同时, TreeSet 可以避免重复加入的问题
     */

    HashMap<Integer, TreeSet<Integer>> followers;
    HashMap<Integer, TreeSet<Integer>> followings;

    public _0560_Friendship_Service() {
        followers = new HashMap<>();
        followings = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        if (followers.get(user_id) == null) {
            return new ArrayList<>();
        }
        TreeSet<Integer> set = followers.get(user_id);
        return new ArrayList<>(set);
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        if (followings.get(user_id) == null) {
            return new ArrayList<>();
        }
        TreeSet<Integer> set = followings.get(user_id);
        return new ArrayList<>(set);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        if (!followers.containsKey(to_user_id)) {
            followers.put(to_user_id, new TreeSet<>());
        }
        followers.get(to_user_id).add(from_user_id);

        if (!followings.containsKey(from_user_id)) {
            followings.put(from_user_id, new TreeSet<>());
        }
        followings.get(from_user_id).add(to_user_id);

    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        if (followers.containsKey(to_user_id)) {
            followers.get(to_user_id).remove(from_user_id);
        }

        if (followings.containsKey(from_user_id)) {
            followings.get(from_user_id).remove(to_user_id);
        }
    }
}
