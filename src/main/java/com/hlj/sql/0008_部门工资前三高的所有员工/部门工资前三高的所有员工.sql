
drop table if exists Employee;
drop table if exists Department;
create table Employee
(
    Id           int(11),
    Name         varchar(20),
    Salary       decimal(20, 0),
    DepartmentId int(11)
);

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


# 最重要的是后面的  < 3 注意里面的  DISTINCT（可能出现重复的人，所以用了）
select D.Name as Department, E.Name as Employee, E.Salary
from Employee E
         join Department D on D.Id = E.DepartmentId
WHERE (
          SELECT count(DISTINCT Salary)
          from Employee A where
                                A.Salary > E.Salary and
                                A.DepartmentId = E.DepartmentId)
          < 3 order by Department, Salary desc ;


