2023 · Calculate the number of days from 01/13/2018 to the time of course creation

Write a SQL statement to calculate the number of days difference from 01/13/2018 to
the course creation time (created at) from the courses table, with the result column
named date diff.

Table definition: courses (course table)

| Column Name   | Type          | Comments             |
|---------------|---------------|----------------------|
| id            | int unsigned  | primary key          |
| name          | varchar       | course name          |
| student_count | int           | number of students   |
| created_at    | date          | course creation time |
| teacher_id    | int           | teacher id           |

- If the course creation time is earlier than 13/01/2018,
the number of days returned by the calculation is negative
- If the creation time is empty, NULL is returned


SQL

分析题目要求：
1. 从 courses 表
2. 从 2018 年 01 月 13 日到创建课程时间（created_at）相差天数
3. 结果列名请以 date_diff 显示

根据题目要求，以及三个条件推出：
1. FROM courses
2. DATEDIFF('2018-01-13', created_at)
3. AS date_diff


```
SELECT DATEDIFF(`created_at`, '2018-01-13') AS `date_diff` FROM `courses`;
```
