drop table if exists Employee;
create table Employee
(

    Id        int(11),
    Name      varchar(20),
    Salary    decimal(16, 2),
    ManagerId int(11)
)


select e.Name as Employee
from Employee e  join Employee m on e.ManagerId = m.Id
where e.Salary > m.Salary;




select email from Person group by email  having count(id) > 1;
