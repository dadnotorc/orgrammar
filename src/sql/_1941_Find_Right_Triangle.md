1941 · Find Right Triangle

Li Hua’s job is to determine whether three line segments can form a right triangle
Assuming that the table line_segments saves all groups consisting of three line segments 

with lengths a, b, c, please help Li Hua write a SQL statement to determine whether each group of line segments can form a right triangle

Table definition: line_segments

| Column Name | Type         | Explanation |
|-------------|--------------|-------------|
| id          | int unsigned | primary key |
| a           | int          | length of a |
| b           | int          | length of b |
| c           | int          | length of c |

Example 1:

Table content: line_segments

id	a	b	c
1	3	4	5
2	10	20	15
3	1	2	10
After running your SQL statement, the table should return:

id	a	b	c	right_triangle
1	3	4	5	Yes
2	10	20	15	No
3	1	2	10	No


SQL

```
SELECT id,a,b,c,
      if ((a*a + b*b = c*c or a*a + c*c = b*b or b*b + c*c = a*a), "Yes", "No") as right_triangle
FROM line_segments;

```