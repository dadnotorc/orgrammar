# 2042 · Query all course names in the course schedule and the year and month of the creation date

Write a SQL statement to query the course name (name) and the year (alias: year ) and month (alias: month) of the creation date of all courses in the course table courses.

Table definition: courses (course table)

| columns_name  |     type      |       Comments       |
|:-------------:|:-------------:|:--------------------:|
|      id       | int unsigned  |     primary key      |
|     name      |    varchar    |    courses' name     |
| student_count |      int      |  number of students  |
|  created_at   |     date      | course creation time |
|  teacher_id   |      int      |     teacher's id     |

- The column names returned by the query need to match the case of the column names output by the sample
- If there is a null value in the SELECT value, null will be returned.

# SQL

```
SELECT `name`, YEAR(`created_at`) AS `year`, MONTH(`created_at`) AS `month`
FROM `courses`;

# 或者

SELECT 
`name`, EXTRACT(YEAR FROM `created_at`) AS `year`, EXTRACT(MONTH FROM `created_at`) AS `month`
FROM `courses`;
```