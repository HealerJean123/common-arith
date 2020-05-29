
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
insert into employee (Id, Name, Salary, DepartmentId) values (1, 'Joe', 70000, 1);
insert into employee (Id, Name, Salary, DepartmentId) values (2, 'Henry', 80000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (3, 'Sam', 60000, 2);
insert into employee (Id, Name, Salary, DepartmentId) values (4, 'Max', 90000, 1);

select *  from Department ;
insert into department (Id, Name) values (1, 'IT');
insert into department (Id, Name) values (2, 'Sales');



# 最重要的是后面的  < 3
SELECT D.Name  AS 'Department',
       E.Name AS 'Employee',
       E.Salary
FROM Employee E
         JOIN
     Department D ON E.DepartmentId = D.Id
WHERE  (SELECT COUNT(DISTINCT A.Salary)
           FROM Employee A
           WHERE A.Salary > E.Salary
             AND A.DepartmentId = E.DepartmentId
) < 3;
