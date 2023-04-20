#什么时候使用BFS

1. 图的遍历 traversal in graph

* 层级遍历 level order traversal
* 由点及面 connected component (灌水法）求连通性
* 拓扑排序 topological sorting

2. 最短路径 shortest path in simple graph

* 仅限简单图求最短路径 （不用DFS因为一开始的路径可能是错误的）
* 即 图中每天边长度均为1, 且无方向