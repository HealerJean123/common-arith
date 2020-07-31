# 1、题目、行程和用户
#Trips 表中存所有出租车的行程信息。每段行程有唯一键 Id，Client_Id 和 Driver_Id 是 Users 表中 Users_Id 的外键。Status 是枚举类型，枚举成员为 (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’)。
#
#+----+-----------+-----------+---------+--------------------+----------+
#| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
#+----+-----------+-----------+---------+--------------------+----------+
#| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
#| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
#| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
#| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
#| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
#| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
#| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
#| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
#| 9  |     3     |    10     |    12   |     completed      |2013-10-03|
#| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
#+----+-----------+-----------+---------+--------------------+----------+
#Users 表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（‘client’, ‘driver’, ‘partner’）的枚举类型。
#
#+----------+--------+--------+
#| Users_Id | Banned |  Role  |
#+----------+--------+--------+
#|    1     |   No   | client |
#|    2     |   Yes  | client |
#|    3     |   No   | client |
#|    4     |   No   | client |
#|    10    |   No   | driver |
#|    11    |   No   | driver |
#|    12    |   No   | driver |
#|    13    |   No   | driver |
#+----------+--------+--------+
#写一段 SQL 语句查出 2013年10月1日 至 2013年10月3日 期间非禁止用户的取消率。基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。
#
#取消率的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)
#
#+------------+-------------------+
#|     Day    | Cancellation Rate |
#+------------+-------------------+
#| 2013-10-01 |       0.33        |
#| 2013-10-02 |       0.00        |
#| 2013-10-03 |       0.50        |
#+------------+-------------------+

# 2、数据准备
drop table if exists Trips;
create table Trips (
    Id int,
    Client_Id int,
    Driver_Id int,
    City_Id int,
    Status varchar(32),
    Request_at date
);

insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (1, 1, 10, 1, 'completed', '2013-10-01');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (2, 2, 11, 1, 'cancelled_by_driver', '2013-10-01');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (3, 3, 12, 6, 'completed', '2013-10-01');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (4, 4, 13, 6, 'cancelled_by_client', '2013-10-01');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (5, 1, 10, 1, 'completed', '2013-10-02');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (6, 2, 11, 6, 'completed', '2013-10-02');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (7, 3, 12, 6, 'completed', '2013-10-02');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (8, 2, 12, 12, 'completed', '2013-10-03');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (9, 3, 10, 12, 'completed', '2013-10-03');
insert into Trips (Id, Client_Id, Driver_Id, City_Id, Status, Request_at)values (10, 4, 13, 12, 'cancelled_by_driver', '2013-10-03');
select * from Trips ;


drop table if exists Users;
create table Users (
    Users_Id int,
    Banned varchar(8),
    Role varchar(8)
);
insert into Users(Users_Id, Banned, Role) VALUES (1     ,   'No'   ,'client');
insert into Users(Users_Id, Banned, Role) VALUES (2 ,   'Yes'  ,'client');
insert into Users(Users_Id, Banned, Role) VALUES (3     ,   'No'   ,'client');
insert into Users(Users_Id, Banned, Role) VALUES (4     ,   'No'   ,'client');
insert into Users(Users_Id, Banned, Role) VALUES (10    ,   'No'   ,'driver');
insert into Users(Users_Id, Banned, Role) VALUES (11    ,   'No'   ,'driver');
insert into Users(Users_Id, Banned, Role) VALUES (12    ,   'No'   ,'driver');
insert into Users(Users_Id, Banned, Role) VALUES (13    ,   'No'   ,'driver');
select * from Users;





-- 解析
-- 1，先找到内在条件
-- 某个日期 被司机或乘客取消的非禁止用户生成的订单数量
select count(*) from Trips a  join Users  u on  u.Users_Id = a.Client_Id   where   a.Request_at = '2013-10-03' and  u.Banned = 'No';
-- 某个日期 非禁止用户生成的订单总数
select count(*) from Trips a  join Users  u on  u.Users_Id = a.Client_Id   where a.Status in ('cancelled_by_driver',  'cancelled_by_client')   and a.Request_at = '2013-10-03' and  u.Banned = 'No';

-- 2、日期分组和条件筛选
select t.Request_at as Day
from Trips t
WHERE Request_at BETWEEN '2013-10-01' and '2013-10-03'
group by t.Request_at;


-- 3、select条件中传入日期得出结果
select t.Request_at as Day,
       (ROUND((select count(*)
               from Trips a
                        join Users u on u.Users_Id = a.Client_Id
               where a.Status in ('cancelled_by_driver', 'cancelled_by_client')
                 and a.Request_at = t.Request_at
                 and u.Banned = 'No') / (select count(*)
                                         from Trips a
                                                  join Users u on u.Users_Id = a.Client_Id
                                         where a.Request_at = t.Request_at
                                           and u.Banned = 'No'),
              2))   as 'Cancellation Rate'
from Trips t
WHERE Request_at BETWEEN '2013-10-01' and '2013-10-03'
group by t.Request_at;






# 答案2
# 解析：内连接中 进行and
SELECT t.request_at    as 'Day',
       ROUND(
                   SUM(
                       IF((t.Status != 'completed'), 1, 0))

                       / COUNT(t.Status),
           2)
           as 'Cancellation Rate'
FROM trips t
         JOIN users u1 ON (t.client_id = u1.users_id AND u1.banned = 'No')
         JOIN users u2 ON (t.driver_id = u2.users_id AND u2.banned = 'No')
WHERE t.request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY t.request_at ;
