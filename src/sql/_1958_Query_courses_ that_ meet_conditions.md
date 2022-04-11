# 1958 · Query the courses that meet the conditions taught by the specified teacher

Please write a SQL statement to query the information of all courses whose
- teacher ID is 4 and
- the number of students is more than 500 (excluding 500) in the course table courses .

Table definition : courses

| columns_name  |  type   |     explaination     |
|:-------------:|:-------:|:--------------------:|
|      id       |   int   |     primary key      |
|     name      | varchar |    courses's name    |
| student_count |   int   |  number of students  |
|  created_at   |  date   | course creation time |
|  teacher_id   |   int   |     teacher's id     |

Tip:
- The number of students is more than 500, excluding 500
- If there is no query result, nothing will be returned

## SQL

```
SELECT * FROM courses WHERE
teacher_id = 4 AND student_count > 500;
```