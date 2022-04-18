# 1928 · Analysis of Online Class I

The online_class_situations table shows the behavioral activities of some students in online classes.
Each row of data records the number of courses (may be 0) that a student has listened to
after logging in to the course with the same device on the same day before quitting the online course.
Write a SQL statement to query the date each student first logged into the platform to attend a class.

Table definition: online_class_situations

| Column Name   | Type | Explanation              |
|---------------|------|--------------------------|
| student_id    | int  | student's id             |
| device_id     | int  | device's id              |
| date          | date | Class date of the course |
| course_number | int  | course number            |

**Note**
- The primary key of the table is (student_id, date) combined primary key
- Please note that the returned result column name is: student_id, earliest_course_date

## SQL
```
SELECT student_id, MIN(date) AS earliest_course_date
FROM online_class_situations
WHERE course_number > 0
GROUP BY student_id;
```