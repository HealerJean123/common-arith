# 编写一个 SQL 查询，查找所有至少连续出现三次的数字。
#
# +----+-----+
# | Id | Num |
# +----+-----+
# | 1  |  1  |
# | 2  |  1  |
# | 3  |  1  |
# | 4  |  2  |
# | 5  |  1  |
# | 6  |  2  |
# | 7  |  2  |
# +----+-----+
# 例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。
#
# +-----------------+
# | ConsecutiveNums |
# +-----------------+
# | 1               |
# +-----------------+

drop table  if exists  Logs  ;
create table Logs
(

    Id     int(11) ,
    Num   int(11)
);

truncate Logs ;
insert into logs (Id, Num) values (1, 1);
insert into logs (Id, Num) values (2, 1);
insert into logs (Id, Num) values (3, 1);
insert into logs (Id, Num) values (4, 2);
insert into logs (Id, Num) values (5, 1);
insert into logs (Id, Num) values (6, 2);
insert into logs (Id, Num) values (7, 2);



select * from Logs ;


# 防止出现4个的情况，num重复
select distinct (a.Num) as ConsecutiveNums
from logs a
         join logs b on a.Num = b.Num
         join logs c on a.Num = c.Num
where a.Id = b.Id - 1
  and b.Id = c.Id - 1;



