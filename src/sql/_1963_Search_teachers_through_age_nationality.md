# 1963 · Search for teachers aged 20~25 whose nationality is not Chinese or British

Write an SQL statement to query the teachers between the ages of 20 (including) and 25 (including)
who are not Chinese or British in the teacher table teachers,
and finally return all the information of the queried teachers.

The teachers table is defined as follows：

| columns_name |  type   |     explaination      |
|:------------:|:-------:|:---------------------:|
|      id      |   int   |      primary key      |
|     name     | varchar |    teacher's name     |
|    email     | varchar |    teacher's email    |
|     age      |   int   |     teacher's age     |
|   country    | varchar | teacher's nationality |

- Between the ages of 20 and 25, including 20 and 25
- Nationality is neither Chinese nor British
- If there is no query result, nothing will be returned

## SQL

```
SELECT * FROM teachers
WHERE age BETWEEN 20 AND 25
AND country NOT IN ("CN", "UK");
```