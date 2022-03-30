package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给一堆 message id 和参数 k. 一个message id 至多只能 out of k place. 例如 [0,1,2,3,4...] 以及 k=1
 * 要求完成两个函数:
 * - find # of missing message ids
 * - find # of dup message ids
 *
 * out of k place 的意思是:
 * find # of missing ids in range [0, N - k] where N is the biggest id so far
 *
 * 离最大值 N 最远 k 步以内, missing / duplicate id ?
 *
 * message id 是否需要 sorted? 还是 FIFO?
 */
public class Nuro_2021_Find_out_of_k_place {

//    int max;
//    Queue<Integer> ids;
//
//    public Nuro_2021_Find_out_of_k_place() {
//        max = Integer.MIN_VALUE;
//        ids = new LinkedList<>();
//    }
//
//    public void add(int id) {
//        ids.add()
//    }

}
