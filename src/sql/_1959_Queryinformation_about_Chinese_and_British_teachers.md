1959 · Query information about Chinese and British teachers

Please write a SQL statement that use IN to query the information of all teachers
who are Chinese or British in the teacher table teachers.

Table definition : teachers 

| columns_name | type    | explaination |
| :----:       | :----:  | :----: |
| id           | int     | primary key |
| name         | varchar | teacher's name |
| email        | varchar | teacher's email |
| age          | int     | teacher's age |
| country      | varchar | teacher's nationality  |


Tip:

The question requires the use of IN, please do not use AND
If there is no query result, nothing will be returned

```
SELECT * FROM teachers WHERE country in ('UK', 'CN');
```