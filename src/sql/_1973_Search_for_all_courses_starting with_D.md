1973 · Search for all courses starting with the letter 'D'

Write a SQL statement to query the information of all courses whose name starts with the letter 'D'.

Table Definition: courses (Course List)


| Column Name   | Type    | Comment        |
|---------------|---------|----------------|
| id            | int     | primary key    |
| name          | varchar | course name    |
| student_court | int     | total students |
| created_at    | date    | start time     |
| teacher_id    | int     | teacher id     |

SQL

```
select * from courses where name like 'D%';
```