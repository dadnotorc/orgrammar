2030 · Query the hours of all course creation times

Write a SQL statement that queries the course name (name) and the hours of the course creation time (created_at)
,from the course table courses, and aliases the column name from which the hours are extracted to created_hour.

Table definition: courses（courses table）

| Column Name   | Type         | Comments       |
|---------------|--------------|----------------|
| id            | int unsigned | primary key    |
| name          | varchar      | course name    |
| student_count | int          | total students |
| created_at    | datetime     | creation time  |
| teacher_id    | int          | teacher id     |

The column names returned by the query need to be the same case as the sample output.
If there is a NULL value in the input data, NULL is returned.

SQL

```
SELECT name,
       extract(hour from created_at) as created_hour
FROM courses;

```