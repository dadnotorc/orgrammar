1964 · Query for course information about the number of students within the specified range

Please write a SQL statement to query the information of all courses where
the number of students is between 50 and 55 in the course table Courses.

Table definition: courses

| columns_name  |  type   |     explaination     |
|:-------------:|:-------:|:--------------------:|
|      id       |   int   |     primary key      |
|     name      | varchar |    courses's name    |
| student_count |   int   |  number of students  |
|  created_at   |  date   | course creation time |
|  teacher_id   |   int   |     teacher's id     |

If there is no query result, nothing will be returned

# SQL

```
SELECT * FROM courses where student_count BETWEEN 50 AND 55;
```