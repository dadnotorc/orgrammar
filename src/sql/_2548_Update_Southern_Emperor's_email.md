# 2548 · Update Southern Emperor's email

We want to update the mailbox of Southern Emperor in the teachers table to
southern.emperor@outlook.com, however, the courses table is under a write lock,
so write an SQL statement to update the mailbox of Southern Emperor in the 
teachers table.

Table Definition 1: teachers (Teachers table)

| columns_name |     type     |      explanation      |
|:------------:|:------------:|:---------------------:|
|      id      | int unsigned |      primary key      |
|     name     |   varchar    |    teacher's name     |
|    email     |   varchar    |    teacher's email    |
|     age      |     int      |     teacher's age     |
|   country    |   varchar    | teacher's nationality |

Table Definition 2: courses (Course List)

| Column Name   | Type         | Comment        |
|---------------|--------------|----------------|
| id            | int unsigned | primary key    |
| name          | varchar      | course name    |
| student_court | int          | total students |
| created_at    | date         | start time     |
| teacher_id    | int unsigned | teacher id     |

Please note that the teachers table is write-locked

分析题目要求：
条件一：从 teachers 表
条件二：邮箱为 southern.emperor@outlook.com
条件三：teachers 表被上了写锁

根据题目要求，如果想要更新 Southern Emperor 的邮箱，必须先解锁，根据三个条件推出：
条件一：UPDATE teachers
条件二：SET email = 'southern.emperor@outlook.com'
条件三：UNLOCK TABLES;
从而解出题目

# SQL
```
-- 对 courses 表上写锁，不要删除该代码 --
LOCK TABLES courses WRITE;

-- Write your SQL Query here --

-- 解锁 --
UNLOCK TABLES;

-- 更新邮箱 --
UPDATE teachers 
SET email = 'southern.emperor@outlook.com'
WHERE name = 'Southern Emperor';

```


