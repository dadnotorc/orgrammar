# 2080 · Search for the name of the instructor and the total number of students in all the instructor's courses with less than 3000 students

Write an SQL statement that join the `courses` and `teachers` tables to
count the total number of students of courses offered by same teacher,
and count the total number of students as 0 for teachers teach nothing.

Finally, query the names of teachers and the total number of students (alias student_count)
with total students fewer than 3000. Sort the results in ascending order by the total number of students or,
if the total number of students were the same, by the teacher's name.

Table definition 1: courses

| Column Name   | Type    | Comment        |
|---------------|---------|----------------|
| id            | int     | primary key    |
| name          | varchar | course name    |
| student_court | int     | total students |
| created_at    | date    | start time     |
| teacher_id    | int     | teacher id     |

Table Definition 2: teachers

| Column Name | Type    | Comments              |
|-------------|---------|-----------------------|
| id          | int     | primary key           |
| name        | varchar | teacher's name        |
| email       | varchar | teacher's email       |
| age         | int     | teacher's age         |
| country     | varchar | teacher's nationality |


- The column name returned by the query needs to be the same as
   the case of the column name output by the sample.

- If the teacher taught nothing, or if there is a NULL value in the input data,
   you need to set the total number of students to 0.


# SQL

```
SELECT t.name, 
       IFNULL(SUM(c.student_count), 0) AS student_count
FROM teachers t
LEFT JOIN courses c
ON t.id = c.teacher_id
GROUP BY t.id
HAVING student_count < 3000
ORDER BY student_count, t.name;
```