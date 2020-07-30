# 1、题目：部门工资前三高的所有员工
# Employee 表包含所有员工信息，每个员工有其对应的工号 Id，姓名 Name，工资 Salary 和部门编号 DepartmentId 。
#
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 85000  | 1            |
# | 2  | Henry | 80000  | 2            |
# | 3  | Sam   | 60000  | 2            |
# | 4  | Max   | 90000  | 1            |
# | 5  | Janet | 69000  | 1            |
# | 6  | Randy | 85000  | 1            |
# | 7  | Will  | 70000  | 1            |
# +----+-------+--------+--------------+
# Department 表包含公司所有部门的信息。
#
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+
# 编写一个 SQL 查询，找出每个部门获得前三高工资的所有员工。例如，根据上述给定的表，查询结果应返回：
#
# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | IT         | Randy    | 85000  |
# | IT         | Joe      | 85000  |
# | IT         | Will     | 70000  |
# | Sales      | Henry    | 80000  |
# | Sales      | Sam      | 60000  |
# +------------+----------+--------+

# 2、数据准备
drop table if exists Employee;
create table Employee
(
    Id           int(11),
    Name         varchar(20),
    Salary       decimal(20, 0),
    DepartmentId int(11)
);
drop table if exists Department;
create table Department
(
    Id         int(11),
    Name       varchar(20)
);

select *  from Employee ;
insert into employee (Id, Name, Salary, DepartmentId) values (1, 'Joe', 85000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (2, 'Henry', 80000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (3, 'Sam', 60000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (4, 'Max', 90000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (5, 'Janet', 69000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (6, 'Randy', 85000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (7, 'Will', 70000, 1);

select *  from Department ;
insert into department (Id, Name) values (1, 'IT');
insert into department (Id, Name) values (2, 'Sales');



# 3、答案
-- 解析：最重要的是后面的  < 3 注意里面的  DISTINCT（可能出现重复的人，所以用了）
select D.Name as Department, E.Name as Employee, E.Salary
from Employee E
         join Department D on D.Id = E.DepartmentId
WHERE (
          SELECT count(DISTINCT Salary)
          from Employee A
          where A.Salary > E.Salary
            and A.DepartmentId = E.DepartmentId)
          < 3
order by Department, Salary desc;


