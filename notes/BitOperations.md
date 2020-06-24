## The Bitwise Operators

- &: bitwise and
- |: bitwise or
- ^: bitwise XOR
- ~: bitwise compliment
- <<: left shift
- \>>: right shift
- \>>>: zero fill right shift

a   = 0011 1100\
b   = 0000 1101
-----------------
a&b = 0000 1100

a|b = 0011 1101

a^b = 0011 0001

~a  = 1100 0011

a << 2 表示其二进制值向左挪两位 = 1111 0000

a >> 2 表示其二进制值向右挪两位 = 1111

a >>> 2 表示向右挪两位, 挪动的位置用0填上 = 0000 1111


Decimal中 除以2 = Binary >> 1


### 小技巧

- 将数字A的第k位设置为1:
    - A = A | (1 << (k - 1))

- 将数字A的第k位设置为0:
    - A = A & ~(1 << (k - 1))

- 检测数字A的第k位是否为0:
    - A & (1 << (k - 1)) == 0
    
- get the last set bit (lowest set bit / rightmost set bit) 获得数字A的最低位. 例如6(110)的最低位是2(10), 5(101)的最低位是1(1)
    - A & -A
    - A & ~(A - 1)
    - 两者相同, 解释看 https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C++Java-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations/70714
    
- 得到1111..111:
    - ~0
    
### XOR ^ 异或运算小技巧

Use ^ to remove even exactly same numbers and save the odds, or save the distinct bits and remove the same
去除出现偶数次的数字, 保留出现奇数次的数字

例如LC 122, 371

### OR | 或运算小技巧

Keep as many 1-bits as possible
尽可能多的保留1

例如 LC 190

### AND & 与运算小技巧

Just select certain bits
选择特定的位

例如 LC 191, 477
    
### 奇数偶数
注意: 等号左边必须用括号包裹
```
if ((num & 1) == 0) // 偶数个

if ((num & 1) == 1) // 奇数个
```

### hamming distance
hamming distance 等于当前位 '0'的个数 乘以 '1'的个数

```
System.out.println((14 >> 0) + " : " + ((14 >> 0) & 1));
System.out.println((14 >> 1) + " : " + ((14 >> 1) & 1));
System.out.println((14 >> 2) + " : " + ((14 >> 2) & 1));
System.out.println((14 >> 3) + " : " + ((14 >> 3) & 1));
        
14 : 0
7 : 1
3 : 1
1 : 1
```