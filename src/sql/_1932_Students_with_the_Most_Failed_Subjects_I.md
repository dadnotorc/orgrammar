# 1932 · Students with the Most Failed Subjects I

The exams' records of students are stored in the `exams` table
Please use SQL statement to find the student_id corresponding to the student
with the largest number of failed subjects.

Table definition: exams

| Column Name | Type    | explanation                                   |
|-------------|---------|-----------------------------------------------|
| id          | int     | primary key                                   |
| student_id  | int     | student's id                                  |
| date        | date    | date of exam                                  |
| is_pass     | int     | grade status (0 means fail, others mean pass) |

Data guarantee that there is only one student with the largest number of failed subjects

## Solution

题目提示挂科数量最多的同学 有且只有一位
所以要根据 挂科数 对学生进行 降序排列, 第一行记录即是挂科数最多的

```
SELECT student_id FROM exams
WHERE is_pass = 0
GROUP BY student_id
ORDER BY count(id) desc limit 1;
```


https://www.lintcode.com/problem/1932/solution/22205

## 知识要点

### GROUP BY 语句
GROUP BY 语句将具有相同值的行分组为摘要行，例如“查找各个部门的人数”。

GROUP BY 语句通常与聚合函数 (COUNT, MAX, MIN, SUM, AVG) 将结果集按一列或多列分组。

#### 基本语法
-- [] 内的内容不一定必须存在 --
SELECT column_name(s)
FROM table_name
[WHERE condition]
GROUP BY column_name(s)
[HAVING condition]
[ORDER BY column_name(s)];



### ORDER BY 关键字
ORDER BY 关键字用于按升序或降序对结果集进行排序降序排列。

默认情况下，ORDER BY 关键字以升序对记录进行排序。要按降序对记录进行排序，请使用 DESC 关键字。

升序：ASC（默认，可以省略不写）
降序：DESC

#### 基本语法

SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC; 




### SQL COUNT() 函数
COUNT() 函数返回与指定条件匹配的行数。

#### 基本语法

SELECT COUNT(column_name)
FROM table_name
WHERE condition; 



### SQL LIMIT 子句
LIMIT 子句用于规定要返回的记录的数目。

LIMIT 子句对于拥有数千条记录的大型表来说，是非常有用的。

注意：

LIMIT 不通用，是 MySQL 特有的，其他数据库中没有。
Oracle 可以使用 ROWNUM 来实现 LIMIT 的功能。
SQL SERVER / MS Access 使用 TOP 实现 LIMIT 的功能。

#### 基本语法

SELECT column_name(s)
FROM table_name
LIMIT m, n
m ：指记录开始的index，m = 0 表示查询的第一条记录
n ：指记录从 m 开始，取n条
m 可以省略不写，eg: 查询前 5 条数据 LIMIT 5 == LIMIT 0, 5



### 常见错误
没有过滤掉通过的数据
SELECT student_id
FROM exams
GROUP BY student_id
ORDER BY COUNT(*) DESC
LIMIT 1;

没有使用降序排序
SELECT student_id
FROM exams
WHERE is_pass = 0
GROUP BY student_id
ORDER BY COUNT(*)
LIMIT 1;
