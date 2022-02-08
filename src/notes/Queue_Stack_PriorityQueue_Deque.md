

Priority Queue 不接受 null

Deque本身不接受null, workaround是使用Optional class
```
import java.util.Optional;

Deque<Optional<T>> deque = new ArrayDeque<>();

// Add non-null value
queue.add(Optional.of(value))

// Add nullable value
queue.add(Optional.ofNullable(value))

// Add null
queue.add(Optional.empty())

// Unbox
last = queue.pollLast().orElse(null)
```