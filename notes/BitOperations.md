# The Bitwise Operators

`&` - bitwise and

`|` - bitwise or

`^` - bitwise XOR

`~` - bitwise compliment

`<<` - left shift

`>>` - right shift

`>>>` -  zero fill right shift

## desc
- Set union `A | B`
- Set intersection `A & B`
- Set subtraction `A & ~B`
- Set negation ALL_BITS `^ A` or `~A`
- Set bit `A |= 1 << bit`
- Clear bit `A &= ~(1 << bit)`
- Test bit `(A & 1 << bit) != 0`
- Extract last bit `A&-A` or `A&~(A-1)` or `x^(x&(x-1))`
- Remove last bit `A&(A-1)`
- Get all 1-bits `~0`

## Examples

a   = 0011 1100\
b   = 0000 1101
-----------------
`a & b` = 0000 1100

`a | b` = 0011 1101

`a ^ b` = 0011 0001

`~a`  = 1100 0011

`a << 2` 表示其二进制值向左挪两位 = 1111 0000

`a >> 2` 表示其二进制值向右挪两位 = 1111

`a >>> 2` 表示向右挪两位, 挪动的位置用0填上 = 0000 1111


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



https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary%3A-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently


### XOR ^ 异或运算小技巧

Use ^ to remove even exactly same numbers and save the odds, or save the distinct bits and remove the same
去除出现偶数次的数字, 保留出现奇数次的数字

例如LC 122, 371

#### Sum of 2 Integers
```
# use ^ and & to add 2 integers

int getSum(int a, int b) {
    return b==0? a:getSum(a^b, (a&b)<<1); //be careful about the terminating condition;
}
```

#### missing number
```
# find the missing number from the array of n distinct number from 0, 1, 2, ..., n

int missingNumber(vector<int>& nums) {
    int ret = 0;
    for(int i = 0; i < nums.size(); ++i) {
        ret ^= i;
        ret ^= nums[i];
    }
    return ret ^= nums.size();
}
```

### OR | 或运算小技巧

Keep as many 1-bits as possible
尽可能多的保留1

例如 LC 190

#### keep as many 1-bit as possible
```
# find the largest power of 2 (most significant bit in binary form), which is <= N

long largest_power(long N) {
    //changing all right side bits to 1.
    N = N | (N>>1);
    N = N | (N>>2);
    N = N | (N>>4);
    N = N | (N>>8);
    N = N | (N>>16);
    return (N+1)>>1;
}
```

#### reverse bits
```
# reverse bits of a given 32-bit unsigned integer

uint32_t reverseBits(uint32_t n) {
    unsigned int mask = 1<<31, res = 0;
    for(int i = 0; i < 32; ++i) {
        if(n & 1) res |= mask;
        mask >>= 1;
        n >>= 1;
    }
    return res;
}
```

```
uint32_t reverseBits(uint32_t n) {
	uint32_t mask = 1, ret = 0;
	for(int i = 0; i < 32; ++i){
		ret <<= 1;
		if(mask & n) ret |= 1;
		mask <<= 1;
	}
	return ret;
}
```


### AND & 与运算小技巧

Just select certain bits
选择特定的位

例如 LC 191, 477

#### Reversing the bits in integer
```
x = ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1);
x = ((x & 0xcccccccc) >> 2) | ((x & 0x33333333) << 2);
x = ((x & 0xf0f0f0f0) >> 4) | ((x & 0x0f0f0f0f) << 4);
x = ((x & 0xff00ff00) >> 8) | ((x & 0x00ff00ff) << 8);
x = ((x & 0xffff0000) >> 16) | ((x & 0x0000ffff) << 16);
```


    
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

### count the # of 1s - 二进制中 1 的数量
```
int count_one(int n) {
    while(n) {
        n = n&(n-1);
        count++;
    }
    return count;
}
```


### power of 4
```
bool isPowerOfFour(int n) {
    return !(n&(n-1)) && (n&0x55555555);
    //check the 1-bit location;
}
```