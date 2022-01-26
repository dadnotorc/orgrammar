1974 · Query teacher information by email

Write a SQL statement to query the name and email of all teachers who use qq email(mailboxes ending with "@qq.com" ) in the teacher tableteachers.

Table definition: teachers (teachers table)

| Column Name | Type    | Comments              |
|-------------|---------|-----------------------|
| id          | int     | primary key           |
| name        | varchar | teacher's name        |
| email       | int     | teacher's email       |
| age         | int     | teacher's age         |
| country     | varchar | teacher's nationality |

Note that the information returned is the teacher's name and email address
If there is no query results, nothing will be returned


QL

```
select  name,
        email,
from    teachers
WHERE email LIKE '%@qq.com';
```
