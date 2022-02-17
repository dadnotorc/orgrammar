1913 · Query Student Enrollment Information

Write a SQL query, the condition: no matter whether the student has a student enrollment information, student needs to provide the following information based on the above two tables:

student_name,
phone,
hometown,
address
Table definition 1: students

| Column Name  | Type         | Explanation     |
|--------------|--------------|-----------------|
| id           | int unsigned | primary key     |
| student_name | varchar      | student's name  |
| phone        | varchar      | student's phone |


Table definition 2: enrollments

| Column Name | Type         | Explanation        |
|-------------|--------------|--------------------|
| id          | int unsigned | primary key        | 
| student_id  | int unsigned | student's id       | 
| hometown    | varchar      | student's hometown |
| address     | varchar      | student's address  |


#SQL

```
select t1.student_name, t1.phone, t2.hometown, t2.address
from students as t1 left join enrollments as t2
on t1.id = t2.student_id;

```