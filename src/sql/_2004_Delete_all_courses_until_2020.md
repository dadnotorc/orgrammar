# 2004 · Delete all courses until 2020

Write an SQL statement to delete all courses in the course table courses
with a course creation date created_at before 2020.

Table Definition: courses (course table)

| Column Name   | Type    | Comment        |
|---------------|---------|----------------|
| id            | int     | primary key    |
| name          | varchar | course name    |
| student_court | int     | total students |
| created_at    | date    | start time     |
| teacher_id    | int     | teacher id     |


# SQl

```
DELETE FROM courses WHERE YEAR(created_at) < '2020';
```

或者

```
DELETE FROM courses WHERE created_at < '2020-01-01';
```