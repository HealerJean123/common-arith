# 1、题目、不同国家的天气类型
# 国家表：Countries
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | country_id    | int     |
# | country_name  | varchar |
# +---------------+---------+
# country_id 是这张表的主键。
# 该表的每行有 country_id 和 country_name 两列。
#  
#
# 天气表：Weather
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | country_id    | int     |
# | weather_state | varchar |
# | day           | date    |
# +---------------+---------+
# (country_id, day) 是该表的复合主键。
# 该表的每一行记录了某个国家某一天的天气情况。
#
# 写一段 SQL 来找到表中每个国家在 2019 年 11 月的天气类型。
# 天气类型的定义如下：当 weather_state 的平均值小于或等于15返回 Cold，当 weather_state 的平均值大于或等于 25 返回 Hot，否则返回 Warm。
# 你可以以任意顺序返回你的查询结果。
#
# 查询结果格式如下所示：
#
# Countries table:
# +------------+--------------+
# | country_id | country_name |
# +------------+--------------+
# | 2          | USA          |
# | 3          | Australia    |
# | 7          | Peru         |
# | 5          | China        |
# | 8          | Morocco      |
# | 9          | Spain        |
# +------------+--------------+
# Weather table:
# +------------+---------------+------------+
# | country_id | weather_state | day        |
# +------------+---------------+------------+
# | 2          | 15            | 2019-11-01 |
# | 2          | 12            | 2019-10-28 |
# | 2          | 12            | 2019-10-27 |
# | 3          | -2            | 2019-11-10 |
# | 3          | 0             | 2019-11-11 |
# | 3          | 3             | 2019-11-12 |
# | 5          | 16            | 2019-11-07 |
# | 5          | 18            | 2019-11-09 |
# | 5          | 21            | 2019-11-23 |
# | 7          | 25            | 2019-11-28 |
# | 7          | 22            | 2019-12-01 |
# | 7          | 20            | 2019-12-02 |
# | 8          | 25            | 2019-11-05 |
# | 8          | 27            | 2019-11-15 |
# | 8          | 31            | 2019-11-25 |
# | 9          | 7             | 2019-10-23 |
# | 9          | 3             | 2019-12-23 |
# +------------+---------------+------------+
# Result table:
# +--------------+--------------+
# | country_name | weather_type |
# +--------------+--------------+
# | USA          | Cold         |
# | Austraila    | Cold         |
# | Peru         | Hot          |
# | China        | Warm         |
# | Morocco      | Hot          |
# +--------------+--------------+
# USA 11 月的平均 weather_state 为 (15) / 1 = 15 所以天气类型为 Cold。
# Australia 11 月的平均 weather_state 为 (-2 + 0 + 3) / 3 = 0.333 所以天气类型为 Cold。
# Peru 11 月的平均 weather_state 为 (25) / 1 = 25 所以天气类型为 Hot。
# China 11 月的平均 weather_state 为 (16 + 18 + 21) / 3 = 18.333 所以天气类型为 Warm。
# Morocco 11 月的平均 weather_state 为 (25 + 27 + 31) / 3 = 27.667 所以天气类型为 Hot。
# 我们并不知道 Spain 在 11 月的 weather_state 情况所以无需将他包含在结果中。

# 2、数据准备

drop table if exists Countries;
create table Countries
(
    country_id   int,
    country_name varchar(32)
);

drop table if exists Weather;
create table Weather
(
    country_id    int,
    weather_state varchar(32),
    day           date

);

select * from Countries;;
INSERT INTO countries (country_id, country_name) VALUES (2, 'USA');
INSERT INTO countries (country_id, country_name) VALUES (3, 'Australia');
INSERT INTO countries (country_id, country_name) VALUES (7, 'Peru');
INSERT INTO countries (country_id, country_name) VALUES (5, 'China');
INSERT INTO countries (country_id, country_name) VALUES (8, 'Morocco');
INSERT INTO countries (country_id, country_name) VALUES (9, 'Spain');

INSERT INTO weather (country_id, weather_state, day) VALUES (2, '15', '2019-11-01');
INSERT INTO weather (country_id, weather_state, day) VALUES (2, '12', '2019-10-28');
INSERT INTO weather (country_id, weather_state, day) VALUES (2, '12', '2019-10-27');
INSERT INTO weather (country_id, weather_state, day) VALUES (3, '-2', '2019-11-10');
INSERT INTO weather (country_id, weather_state, day) VALUES (3, '0', '2019-11-11');
INSERT INTO weather (country_id, weather_state, day) VALUES (3, '3', '2019-11-12');
INSERT INTO weather (country_id, weather_state, day) VALUES (5, '16', '2019-11-07');
INSERT INTO weather (country_id, weather_state, day) VALUES (5, '18', '2019-11-09');
INSERT INTO weather (country_id, weather_state, day) VALUES (5, '21', '2019-11-23');
INSERT INTO weather (country_id, weather_state, day) VALUES (7, '25', '2019-11-28');
INSERT INTO weather (country_id, weather_state, day) VALUES (7, '22', '2019-12-01');
INSERT INTO weather (country_id, weather_state, day) VALUES (7, '20', '2019-12-02');
INSERT INTO weather (country_id, weather_state, day) VALUES (8, '25', '2019-11-05');
INSERT INTO weather (country_id, weather_state, day) VALUES (8, '27', '2019-11-15');
INSERT INTO weather (country_id, weather_state, day) VALUES (8, '31', '2019-11-25');
INSERT INTO weather (country_id, weather_state, day) VALUES (9, '7', '2019-10-23');
INSERT INTO weather (country_id, weather_state, day) VALUES (9, '3', '2019-12-23');
select * from Weather;;


# 3、答案

-- 1、普通条件筛选
select c.country_name  from Countries c ;

-- 2.1、条件判断，找出11月的数据
select w.* from Weather w where  date_format(w.day, '%Y-%m') = '2019-11' ;
-- 2.2、根据国家分组
select w.country_id, avg(weather_state) from Weather w where  date_format(w.day, '%Y-%m') = '2019-11'  group by w.country_id ;

-- 3、 join 连接
select c.country_name,
       case
           when avgState <= 15 then 'Cold'
           when avgState >= 25 then 'Hot'
           else 'Warm'
           end as weather_type
from Countries c
         join (
    select w.country_id, avg(weather_state) as avgState
    from Weather w
    where date_format(w.day, '%Y-%m') = '2019-11'
    group by w.country_id
) m on c.country_id = m.country_id;


