2032 · Advance all course creation dates by one day

Write a SQL statement to advance the course creation date by one day in the courses table, and finally
return the course id, the course name and the modified course creation date which named new_created.

Table definition : courses

| Column Name   | Type         | Explanation       |
|---------------|--------------|----------------|
| id            | int unsigned | primary key    |
| name          | varchar      | course name    |
| student_count | int          | total students |
| created_at    | date         | creation time  |
| teacher_id    | int          | teacher id     |

The result column name needs to be renamed
If the creation time is null, it can not be modified

SQL
```
SELECT id,
       name,
       date_add(created_at,interval -1 day) as new_created
FROM courses;

```