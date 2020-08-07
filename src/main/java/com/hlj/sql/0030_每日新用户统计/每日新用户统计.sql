# 1、题目：每日新用户统计
# Traffic 表：
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | user_id       | int     |
# | activity      | enum    |
# | activity_date | date    |
# +---------------+---------+
# 该表没有主键，它可能有重复的行。
# activity 列是 ENUM 类型，可能取 ('login', 'logout', 'jobs', 'groups', 'homepage') 几个值之一。
#  
#
# 编写一个 SQL 查询，以查询从今天起最多 90 天内，每个日期该日期首次登录的用户数。假设今天是 2019-06-30.
#
# 查询结果格式如下例所示：
#
# Traffic 表：
# +---------+----------+---------------+
# | user_id | activity | activity_date |
# +---------+----------+---------------+
# | 1       | login    | 2019-05-01    |
# | 1       | homepage | 2019-05-01    |
# | 1       | logout   | 2019-05-01    |
# | 2       | login    | 2019-06-21    |
# | 2       | logout   | 2019-06-21    |
# | 3       | login    | 2019-01-01    |
# | 3       | jobs     | 2019-01-01    |
# | 3       | logout   | 2019-01-01    |
# | 4       | login    | 2019-06-21    |
# | 4       | groups   | 2019-06-21    |
# | 4       | logout   | 2019-06-21    |
# | 5       | login    | 2019-03-01    |
# | 5       | logout   | 2019-03-01    |
# | 5       | login    | 2019-06-21    |
# | 5       | logout   | 2019-06-21    |
# +---------+----------+---------------+
#
# Result 表：
# +------------+-------------+
# | login_date | user_count  |
# +------------+-------------+
# | 2019-05-01 | 1           |
# | 2019-06-21 | 2           |
# +------------+-------------+
# 请注意，我们只关心用户数非零的日期.
# ID 为 5 的用户第一次登陆于 2019-03-01，因此他不算在 2019-06-21 的的统计内。


# 2、数据准备

drop table if exists Traffic;
create table Traffic
(
    user_id       int,
    activity      varchar(32),
    activity_date date
);
INSERT INTO traffic (user_id, activity, activity_date) VALUES (1, 'login', '2019-05-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (1, 'homepage', '2019-05-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (1, 'logout', '2019-05-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (2, 'login', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (2, 'logout', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (3, 'login', '2019-01-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (3, 'jobs', '2019-01-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (3, 'logout', '2019-01-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (4, 'login', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (4, 'groups', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (4, 'logout', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (5, 'login', '2019-03-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (5, 'logout', '2019-03-01');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (5, 'login', '2019-06-21');
INSERT INTO traffic (user_id, activity, activity_date) VALUES (5, 'logout', '2019-06-21');
select * from Traffic;


# 3、答案
-- 1、先找到每个用户最近登录的最早日期
select a.user_id as user_id, min(a.activity_date) as activity_date
from Traffic a
where a.activity = 'login'
group by a.user_id
;

-- 2、 根据日期分组，得到日期组内的人数
select t.activity_date as login_date ,count(*) as user_count
from (
         select a.user_id as user_id,min(a.activity_date) as activity_date
         from Traffic a
         where a.activity ='login'
         group by a.user_id
     )t
where datediff('2019-06-30',t.activity_date) <=90
group by t.activity_date

