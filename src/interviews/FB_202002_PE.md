###coding interview
1. Given an array with integers 1 to n. Try split (in the middle) the array
into 2 sub arrays such that their sums equal to each other.
If such split is not possible, return null;

Example 1:\
input = can_partition({1,2,3,6])\
output = ({1,2,3}, {6})

Example 2:\
input = can_partition({1,1,3,2,5,5,6,4,7])\
output = ({1,1,3,2,5,5}, {6,4,7})

Example 3:\
input = can_partition({1,1,3])\
output = ()


2. Pipe the output of vmstat to a file.
Keep reading the file and monitor some column value.
If that value has go over max value for 5 times in a row,
throw a warning.
This process will be running for a long time. 

procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----\
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0      0 14971404 276244 3712064    0    0    48    72  756  246 14  5 81  0  0
