1952 · Query teachers over 20 years old

Please write a SQL statement to query the information of all teachers who is older than 20 in the teacher table teachers.

Table definition: teachers

| Column Name | Type    | Comments              |
|-------------|---------|-----------------------|
| id          | int     | primary key           |
| name        | varchar | teacher's name        |
| email       | int     | teacher's email       |
| age         | int     | teacher's age         |
| country     | varchar | teacher's nationality |


```
SELECT * FROM teachers where age > 20;

```