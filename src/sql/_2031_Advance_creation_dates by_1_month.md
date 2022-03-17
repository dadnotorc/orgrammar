2031 · Advance all course creation dates by one month

Write a SQL statement to advance the course creation date by one month in the courses table,
and finally return the course id, the course name name and the modified creation date which named new_created.

Table definition : courses

| Column Name   | Type         | Explanation        |
|---------------|--------------|--------------------|
| id            | int unsigned | primary key        |
| name          | varchar      | course name        |
| student_count | int          | number of students |
| created_at    | datetime     | creation time      |
| teacher_id    | int          | teacher id         |

The result column name needs to be renamed
If the course was created at January, the creation time will be December of last year after advance one month

# SQL

```
SELECT id, name, date_add(created_at, interval - 1 month) as new_created FROM courses;
```
