num_str = input("num?\n")

if not isinstance(int(num_str), int):
    raise TypeError("not a int")
    
len = len(num_str)
ans = 0
for i in range(len):
    ans += int(num_str[i])

print(ans)
