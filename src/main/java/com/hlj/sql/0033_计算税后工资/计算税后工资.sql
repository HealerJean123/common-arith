# 1、题目：计算税后工资
# Salaries 表：
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | company_id    | int     |
# | employee_id   | int     |
# | employee_name | varchar |
# | salary        | int     |
# +---------------+---------+
# (company_id, employee_id) 是这个表的主键
# 这个表包括员工的company id, id, name 和 salary
#  
#
# 写一条查询 SQL 来查找每个员工的税后工资
#
# 每个公司的税率计算依照以下规则
#
# 如果这个公司员工最高工资不到 1000 ，税率为 0%
# 如果这个公司员工最高工资在 1000 到 10000 之间，税率为 24%
# 如果这个公司员工最高工资大于 10000 ，税率为 49%
# 按任意顺序返回结果，税后工资结果取整
#
#  
#
# 结果表格式如下例所示：
#
# Salaries 表：
# +------------+-------------+---------------+--------+
# | company_id | employee_id | employee_name | salary |
# +------------+-------------+---------------+--------+
# | 1          | 1           | Tony          | 2000   |
# | 1          | 2           | Pronub        | 21300  |
# | 1          | 3           | Tyrrox        | 10800  |
# | 2          | 1           | Pam           | 300    |
# | 2          | 7           | Bassem        | 450    |
# | 2          | 9           | Hermione      | 700    |
# | 3          | 7           | Bocaben       | 100    |
# | 3          | 2           | Ognjen        | 2200   |
# | 3          | 13          | Nyancat       | 3300   |
# | 3          | 15          | Morninngcat   | 1866   |
# +------------+-------------+---------------+--------+
#
# Result 表：
# +------------+-------------+---------------+--------+
# | company_id | employee_id | employee_name | salary |
# +------------+-------------+---------------+--------+
# | 1          | 1           | Tony          | 1020   |
# | 1          | 2           | Pronub        | 10863  |
# | 1          | 3           | Tyrrox        | 5508   |
# | 2          | 1           | Pam           | 300    |
# | 2          | 7           | Bassem        | 450    |
# | 2          | 9           | Hermione      | 700    |
# | 3          | 7           | Bocaben       | 76     |
# | 3          | 2           | Ognjen        | 1672   |
# | 3          | 13          | Nyancat       | 2508   |
# | 3          | 15          | Morninngcat   | 5911   |
# +------------+-------------+---------------+--------+
# 对于公司 1 ，最高工资是 21300 ，其每个员工的税率为 49%
# 对于公司 2 ，最高工资是 700 ，其每个员工税率为 0%
# 对于公司 3 ，最高工资是 7777 ，其每个员工税率是 24%
# 税后工资计算 = 工资 - ( 税率 / 100）*工资
# 对于上述案例，Morninngcat 的税后工资 = 7777 - 7777 * ( 24 / 100) = 7777 - 1866.48 = 5910.52 ，取整为 5911


# 2、准备数据

drop table if exists Salaries;
create table Salaries
(
    company_id    int,
    employee_id   int,
    employee_name varchar(32),
    salary        int
);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (1, 1, 'Tony', 2000);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (1, 2, 'Pronub', 21300);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (1, 3, 'Tyrrox', 10800);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (2, 1, 'Pam', 300);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (2, 7, 'Bassem', 450);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (2, 9, 'Hermione', 700);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (3, 7, 'Bocaben', 100);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (3, 2, 'Ognjen', 2200);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (3, 13, 'Nyancat', 3300);
INSERT INTO salaries (company_id, employee_id, employee_name, salary) VALUES (3, 15, 'Morninngcat', 7777);
select * from Salaries;


# 3、答案

-- 1、查看每个公司的税率
select s1.company_id, case when max(s1.salary) < 1000 then 0 when max(s1.salary) > 10000 then 0.49 else 0.24 end as max_salary
from Salaries s1
group by s1.company_id;

-- 2、关联查询
select s2.company_id,
       s2.employee_id,
       s2.employee_name,
       round(s2.salary * (1 - s.max_salary)) as salary
from Salaries s2 join (
    select s1.company_id, case
                            when max(s1.salary) < 1000  then 0
                            when max(s1.salary) > 10000 then 0.49
                            else 0.24 end as max_salary
    from Salaries s1
    group by s1.company_id
    ) s
on s.company_id = s2.company_id;



