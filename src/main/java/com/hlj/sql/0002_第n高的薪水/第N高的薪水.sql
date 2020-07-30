
drop table if exists employee;
create table employee
(

    id     int(11),
    salary int(11)
)
select * from employee;
insert into employee (id, salary) values (1, 100);
insert into employee (id, salary) values (2, 200);
insert into employee (id, salary) values (3, 300);

# 编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
#
# +----+--------+
# | Id | Salary |
# +----+--------+
# | 1  | 100    |
# | 2  | 200    |
# | 3  | 300    |
# +----+--------+
# 例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
#
# +------------------------+
# | getNthHighestSalary(2) |
# +------------------------+
# | 200                    |
# +------------------------+


#
# select distinct(a.salary) from employee a where (select count(distinct b.salary) from employee b where b.salary >= a.salary) = n
drop function if exists getnthhighestsalary;
create function getnthhighestsalary(n int) returns int
begin
    return (
        select distinct(a.salary)
        from employee a
        where (select count(distinct b.salary) from employee b where b.salary >= a.salary) = n
    );
end
select getnthhighestsalary(1);
