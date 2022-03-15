2008 · Query the course information of two courses

Please write a SQL statement to select the course information of the course named
'System Design' or 'Django' from the courses table.
If both of them are exist, please return all the information of the two courses.

Table definition : courses

| Column Name   | Type            | Explanation       |
|---------------|-----------------|-------------------|
| id            | int unsigned    | primary key       |
| name          | varchar         | course name       |
| student_count | int             | student count     |
| created_at    | date            | course start time |
| teacher_id    | int             | teacher id        |


If there is no query result, nothing will be returned


#SQL

```
SELECT * FROM courses WHERE name in ('System Design', 'Django');
```