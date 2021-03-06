/*
Hard
#Union Find, #Directed Graph, #Minimum Spanning Tree, #Greedy
Amazon
Ladder
 */
package lintcode;

/**
 * 629. Minimum Spanning Tree
 *
 * Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find edges that can connect all the cities and spend the least amount.
 * Return the connects if can connect all the cities, otherwise return empty list.
 *
 * Example
 * Example 1:
 *
 * Input:
 * ["Acity","Bcity",1]
 * ["Acity","Ccity",2]
 * ["Bcity","Ccity",3]
 * Output:
 * ["Acity","Bcity",1]
 * ["Acity","Ccity",2]
 * Example 2:
 *
 * Input:
 * ["Acity","Bcity",2]
 * ["Bcity","Dcity",5]
 * ["Acity","Dcity",4]
 * ["Ccity","Ecity",1]
 * Output:
 * []
 *
 * Explanation:
 * No way
 *
 * Notice
 * Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.
 */
public class _0629_MinimumSpanningTree {

    //todo ladder vip

//    public List<Connection> lowestCost(Listt<Connection> connections) {
//        // Write your code here
//    }
}

class Connection {
    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }
 }
