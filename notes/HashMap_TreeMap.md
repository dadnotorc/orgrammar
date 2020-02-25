## Difference between HashMap and TreeMap in Java

https://javatutorial.net/difference-between-hashmap-and-treemap-in-java

Although both implement the Map interface and offer mostly the same 
functionality, HashMap and TreeMap have different implementations. 
The most important difference is the order in which iteration through
the entries will happen.

Property | HashMap | TreeMap
---|---|---
ordering | not guaranteed | sorted, natural ordering
get/put/remove complexity | O(1) | O(log(n))
inherited interfaces | Map | Map, NavigableMap, SortedMap
NULL values/keys | allowed | only values

###Major Difference between HashMap and TreeMap
TreeMap is an example of a SortedMap and is implemented by Red-Black tree,
which means that the order of the keys is sorted. When iterating over the keys,
you can rely on the fact they will be in order. The order of the keys is
determined by either element’s compareTo() method or an externally supplied
Comparator.

HashMap on the other hand, makes no such guarantee. It is implemented by Hash
Table. Therefore, when iterating over the keys of a HashMap, you can’t be sure
what order they will be in.

###Implementation Complexity Differences
As the complexity of HashMap implementation is O(1), HashMap can be considered
more efficient in general, so use it whenever you don’t care about the order of
the keys. The complexity of get, put and remove operations in TreeMap at the other
hand is O(log(n))

###Allowed Keys and Values Differences
Another important difference is, that HashMap null values are allowed for keys
and values, where TreeMap allows null only to be used for it’s values.

###Synchronization (no Differences)
Note, that both implementations are not synchronized, meaning operating over
those maps is not thread-safe. If you need a thread-safe Map you may want to
choose the ConcurrentHashMap class from the java.util.concurrent package. This
is a thread-safe implementation of Map that offers far better concurrency than
Collections.synchronizedMap(Map<K,V> m)