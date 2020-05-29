create table Employee
(

    Id     int(11),
    Salary int(11)
)


select ifnull(Salary, null) as SecondHighestSalary  from Employee order by Salary limit 1,1 ;

