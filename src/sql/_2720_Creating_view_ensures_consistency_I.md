# 2720 · Creating a view that ensures consistency (I)

Now you are asked to create an updatable view v_teachers that
only allows viewing and inserting information about teachers
who are less than 30 years old, write SQL statement to achieve it.

Table Definition : teachers (Teachers table)

| columns_name |     type     |      explanation      |
|:------------:|:------------:|:---------------------:|
|      id      | int unsigned |      primary key      |
|     name     |   varchar    |    teacher's name     |
|    email     |   varchar    |    teacher's email    |
|     age      |     int      |     teacher's age     |
|   country    |   varchar    | teacher's nationality |

View Definition: v_teachers(View)


| columns_name |     type     |      explanation      |
|:------------:|:------------:|:---------------------:|
|      id      | int unsigned |      primary key      |
|     name     |   varchar    |    teacher's name     |
|    email     |   varchar    |    teacher's email    |
|     age      |     int      |     teacher's age     |
|   country    |   varchar    | teacher's nationality |


# SQL 
```
CREATE VIEW v_teachers
AS
SELECT * FROM teachers where age < 30;
```