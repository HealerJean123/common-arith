# 1、题目：部门工资最高的员工
# Employee 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
#
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 70000  | 1            |
# | 2  | Henry | 80000  | 2            |
# | 3  | Sam   | 60000  | 2            |
# | 4  | Max   | 90000  | 1            |
# +----+-------+--------+--------------+
# Department 表包含公司所有部门的信息。
#
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+
# 编写一个 SQL 查询，找出每个部门工资最高的员工。例如，根据上述给定的表格，Max 在 IT 部门有最高工资，Henry 在 Sales 部门有最高工资。
#
# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | Sales      | Henry    | 80000  |
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
insert into employee (Id, Name, Salary, DepartmentId) values (1, 'Joe', 70000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (2, 'Henry', 80000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (3, 'Sam', 60000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (4, 'Max', 90000, 1);

select *  from Department ;
insert into department (Id, Name) values (1, 'IT');
insert into department (Id, Name) values (2, 'Sales');


# 答案 1
# 解析：使用Join连接，保证有部门
select D.Name as Department, A.Name as Employee, A.Salary
from Employee A
         join Department D on A.DepartmentId = D.Id
         join (select max(Salary) as MaxSalary, B.DepartmentId from Employee B group by DepartmentId) C
                   on C.DepartmentId = A.DepartmentId
where A.Salary = C.MaxSalary;


# 答案2
SELECT D.Name AS 'Department',
       E.Name AS 'Employee',
       E.Salary
FROM Employee E
         JOIN
     Department D ON E.DepartmentId = D.Id
WHERE (SELECT COUNT(DISTINCT A.Salary)
       FROM Employee A
       WHERE A.Salary > E.Salary
         AND A.DepartmentId = E.DepartmentId
      ) < 1;














