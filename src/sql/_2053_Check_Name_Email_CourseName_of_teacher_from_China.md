2053 · Check the name, email and course name of the teacher from China

Write a SQL statement to right join the courses table and the teachers table,
and query the names, emails and course names of teachers whose country = 'CN',
with the result columns named course name course_name, teacher name teacher_name
and teacher email teacher_email respectively.

Table Definition 1: courses (course table)

| Column Name   | Type         | Comments             |
|---------------|--------------|----------------------|
| id            | int unsigned | primary key          |
| name          | varchar      | course name          |
| student_count | int          | number of students   |
| created_at    | date         | course creation time |
| teacher_id    | int          | teacher id           |


Table Definition 2 : teachers (teachers table)

| Column Name | Type         | Comments              |
|-------------|--------------|-----------------------|
| id          | int unsigned | primary key           |
| name        | varchar      | teacher's name        |
| email       | varchar      | teacher's email       |
| age         | int          | teacher's age         |
| country     | varchar      | teacher's nationality |

- The query is a right join, with courses as the left table
  and teachers as the right table.
- If the query of both tables are empty, nothing will be returned.
- The column names returned by the query need to be the same as 
  the case of the column names in the sample output.


SQL

分析题目要求：
1. teachers 表 与 courses 表 通过 foreign key teacher_id 关联
2. 从 teachers 表中 选出 country = 'CN'
3. 从 courses 表 与 teachers 表中
4. 打印出 courses name, teacher name, teacher name
5. as courses_name, teacher_name, teacher_email

根据题目要求，以及三个条件推出：
1. FROM [courses as t1] right join [teachers as t2] on [t1.teacher_id = t2.id] 
2. WHERE t2.country = 'CN'
3. as course_name, as teacher_name, as teacher_email


```
select  t1.name as course_name,
        t2.name as teacher_name,
        t2.email as teacher_email
from    courses as t1 right join teachers as t2 on t1.teacher_id = t2.id
where   t2.country = 'CN';
```
