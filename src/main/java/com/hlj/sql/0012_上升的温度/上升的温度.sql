drop table if exists Weather;
create table Weather
(
    Id          int,
    RecordDate  date,
    Temperature int
);
select * from Weather;
INSERT INTO weather (Id, RecordDate, Temperature) VALUES (1, '2015-01-01', 10);
INSERT INTO weather (Id, RecordDate, Temperature) VALUES (2, '2015-01-02', 25);
INSERT INTO weather (Id, RecordDate, Temperature) VALUES (3, '2015-01-03', 20);
INSERT INTO weather (Id, RecordDate, Temperature) VALUES (4, '2015-01-04', 30);
select * from Weather;

#    给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
#
#    +---------+------------------+------------------+
#    | Id(INT) | RecordDate(DATE) | Temperature(INT) |
#    +---------+------------------+------------------+
#    |       1 |       2015-01-01 |               10 |
#    |       2 |       2015-01-02 |               25 |
#    |       3 |       2015-01-03 |               20 |
#    |       4 |       2015-01-04 |               30 |
#    +---------+------------------+------------------+
#    例如，根据上述给定的 Weather 表格，返回如下 Id:
#
#    +----+
#    | Id |
#    +----+
#    |  2 |
#    |  4 |
#    +----+


# 答案
# 1、子查询 慢
select a.Id
from weather a
where (select b.Temperature from weather b where b.RecordDate = date_sub(a.RecordDate, INTERVAL 1 DAY))
          < a.Temperature;

# 2、关联查询 快
select a.Id
from weather a
         join weather b on b.RecordDate = date_sub(a.RecordDate, INTERVAL 1 DAY)
where a.Temperature > b.Temperature;


