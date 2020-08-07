# # 1、题目：活动参与者
# 表: Friends
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | name          | varchar |
# | activity      | varchar |
# +---------------+---------+
# id 是朋友的 id 和该表的主键
# name 是朋友的名字
# activity 是朋友参加的活动的名字
# 表: Activities
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | name          | varchar |
# +---------------+---------+
# id 是该表的主键
# name 是活动的名字
#  
#
# 写一条 SQL 查询那些既没有最多，也没有最少参与者的活动的名字
#
# 可以以任何顺序返回结果，Activities 表的每项活动的参与者都来自 Friends 表
#
# 下面是查询结果格式的例子：
#
# Friends 表:
# +------+--------------+---------------+
# | id   | name         | activity      |
# +------+--------------+---------------+
# | 1    | Jonathan D.  | Eating        |
# | 2    | Jade W.      | Singing       |
# | 3    | Victor J.    | Singing       |
# | 4    | Elvis Q.     | Eating        |
# | 5    | Daniel A.    | Eating        |
# | 6    | Bob B.       | Horse Riding  |
# +------+--------------+---------------+
#
# Activities 表:
# +------+--------------+
# | id   | name         |
# +------+--------------+
# | 1    | Eating       |
# | 2    | Singing      |
# | 3    | Horse Riding |
# +------+--------------+
#
# Result 表:
# +--------------+
# | activity     |
# +--------------+
# | Singing      |
# +--------------+
#
# Eating 活动有三个人参加, 是最多人参加的活动 (Jonathan D. , Elvis Q. and Daniel A.)
# Horse Riding 活动有一个人参加, 是最少人参加的活动 (Bob B.)
# Singing 活动有两个人参加 (Victor J. and Jade W.)


# 2、准备数据
drop table if exists Friends;
create table Friends
(
    id       int,
    name     varchar(32),
    activity varchar(32)
);


drop table if exists Activities;
create table Activities
(
    id       int,
    name     varchar(32)
);

INSERT INTO friends (id, name, activity) VALUES (1, 'Jonathan D.', 'Eating');
INSERT INTO friends (id, name, activity) VALUES (2, 'Jade W.', 'Singing');
INSERT INTO friends (id, name, activity) VALUES (3, 'Victor J.', 'Singing');
INSERT INTO friends (id, name, activity) VALUES (4, 'Elvis Q.', 'Eating');
INSERT INTO friends (id, name, activity) VALUES (5, 'Daniel A.', 'Eating');
INSERT INTO friends (id, name, activity) VALUES (6, 'Bob B.', 'Horse Riding');
select * from Friends;

INSERT INTO activities (id, name) VALUES (1, 'Eating');
INSERT INTO activities (id, name) VALUES (2, 'Singing');
INSERT INTO activities (id, name) VALUES (3, 'Horse Riding');
select * from Activities;

# 3、答案 （没有用到 activities）
-- 1、选择参与人数最多和最少的个数
select max(m1.count_ac) from  ( select f1.activity, count(f1.activity) as count_ac  from friends f1 group by f1.activity) m1
union
select min(m2.count_ac) from  (select f2.activity, count(f2.activity) as count_ac  from friends f2 group by f2.activity) m2

-- 2、分组筛选
select f3.activity as activity from friends f3
group by f3.activity
having
count(f3.activity) < (select max(m1.count_ac) from  ( select f1.activity, count(f1.activity) as count_ac  from friends f1 group by f1.activity) m1)
and
count(f3.activity) > (select min(m2.count_ac) from  (select f2.activity, count(f2.activity) as count_ac  from friends f2 group by f2.activity) m2)



-- 2、学习了 any
# 定义两个变量记录最多和最少的，然后筛选
select activity as ACTIVITY,
    count(*)
from friends
group by activity
having count(*) > any ( select count(*) from friends  group by activity)
   and count(*) < any (  select count(*) from friends group by activity
);

