## ">>" 

十进制14 = 二进制1110
14 >> 1 表示其二进制值向右挪一位 = 111, 对应十进制7\
14 >> 2 表示其二进制值向右挪两位 = 11, 对应十进制3\
14 >> 3 表示其二进制值向右挪三位 = 1, 对应十进制1


## "&"
0 & 0 = 0 & 1 = 1 & 0 = 0\
1 & 1 = 1

14 & 1 = 1110 & 0001 = 0000 = 0\
7 & 1 = 111 & 001 = 001 = 1

## hamming distance
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