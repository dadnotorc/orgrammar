## The Bitwise Operators

- &: bitwise and
- |: bitwise or
- &: bitwise XOR
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