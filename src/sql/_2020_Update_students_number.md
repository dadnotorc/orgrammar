2020 · Update on the number of students choosing artificial intelligence

Write an SQL statement to update the number of students to 500 of the Artificial Intelligence course in the course table courses.

Table definition : courses

| Column Name   | Type    | Explanation        |
|---------------|---------|--------------------|
| id            | int     | primary key        |
| name          | varchar | course name        |
| student_count | int     | number of students |
| created_at    | date    | creation time      |
| teacher_id    | int     | teacher id         |

Please change the value directly, do not change the value by adding or subtracting
If the number of students is null, update the data directly to the set value
If there is no artificial intelligence course, then do not update any data


# SQL

```
UPDATE courses
SET student_count = 500
WHERE name = "Artificial Intelligence";
```


```
UPDATE `table_name`
SET `column1`=value1,`column2`=value2,...
WHERE `some_column`=some_value;
```