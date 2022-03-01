1923 · Increasing Number of Infections

Write an SQL query to find the IDs of all dates with a higher number of new cases
than the previous day's date.

Table definition: new_cases


| Column Name     | Type          | Explanation                  |
|-----------------|---------------|------------------------------|
| id              | int unsigned  | primary key                  |
| date            | date          | date                         |
| increased_count | int           | The number of new infections |


题目要求找到, 相邻两天, 后者 increased_count 更大的项

```
select t2.id
from new_cases as t1
join new_cases as t2
where datediff(t2.date, t1.date) = 1 and t2.increased_count > t1.increased_count
```

或者

```
select t2.id
from new_cases as t1
join new_cases as t2
on t2.date = date_add(t1.date, interval 1 day)
where t2.increased_count > t1.increased_count
```