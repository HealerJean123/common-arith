# 1、题目： 活跃用户
#
# 表 Accounts:
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | name          | varchar |
# +---------------+---------+
# id 是该表主键.
# 该表包含账户 id 和账户的用户名.
#  
#
# 表 Logins:
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | login_date    | date    |
# +---------------+---------+
# 该表无主键, 可能包含重复项.
# 该表包含登录用户的账户 id 和登录日期. 用户也许一天内登录多次.
#
# 写一个 SQL 查询,  找到活跃用户的 id 和 name.
#
# 活跃用户是指那些至少连续 5 天登录账户的用户.
#
# 返回的结果表按照 id 排序.
#
# 结果表格式如下例所示:
#
# Accounts 表:
# +----+----------+
# | id | name     |
# +----+----------+
# | 1  | Winston  |
# | 7  | Jonathan |
# +----+----------+
#
# Logins 表:
# +----+------------+
# | id | login_date |
# +----+------------+
# | 7  | 2020-05-30 |
# | 1  | 2020-05-30 |
# | 7  | 2020-05-31 |
# | 7  | 2020-06-01 |
# | 7  | 2020-06-02 |
# | 7  | 2020-06-02 |
# | 7  | 2020-06-03 |
# | 1  | 2020-06-07 |
# | 7  | 2020-06-10 |
# +----+------------+
#
# Result 表:
# +----+----------+
# | id | name     |
# +----+----------+
# | 7  | Jonathan |
# +----+----------+
# id = 1 的用户 Winston 仅仅在不同的 2 天内登录了 2 次, 所以, Winston 不是活跃用户.
# id = 7 的用户 Jonathon 在不同的 6 天内登录了 7 次, , 6 天中有 5 天是连续的, 所以, Jonathan 是活跃用户.

# 2、数据准备
drop table if exists Accounts;
create table Accounts
(
    id   int,
    name varchar(32)
);


drop table if exists Logins;
create table Logins
(
    id   int,
    login_date date
);


select * from Accounts;
INSERT INTO accounts (id, name) VALUES (1, 'Winston');
INSERT INTO accounts (id, name) VALUES (7, 'Jonathan');

INSERT INTO logins (id, login_date) VALUES (7, '2020-05-30');
INSERT INTO logins (id, login_date) VALUES (1, '2020-05-30');
INSERT INTO logins (id, login_date) VALUES (7, '2020-05-31');
INSERT INTO logins (id, login_date) VALUES (7, '2020-06-01');
INSERT INTO logins (id, login_date) VALUES (7, '2020-06-02');
INSERT INTO logins (id, login_date) VALUES (7, '2020-06-02');
INSERT INTO logins (id, login_date) VALUES (7, '2020-06-03');
INSERT INTO logins (id, login_date) VALUES (1, '2020-06-07');
INSERT INTO logins (id, login_date) VALUES (7, '2020-06-10');
select * from Logins;


# 3、答案
select distinct g1.id, a.name
from Logins g1
         join logins g2 on g1.id = g2.id
         join logins g3 on g1.id = g3.id
         join logins g4 on g1.id = g4.id
         join logins g5 on g1.id = g5.id
         join Accounts a on g1.id = a.id
where g2.login_date = date_add(g1.login_date, INTERVAL 1 day)
  and g3.login_date = date_add(g1.login_date, INTERVAL 2 day)
  and g4.login_date = date_add(g1.login_date, INTERVAL 3 day)
  and g5.login_date = date_add(g1.login_date, INTERVAL 4 day)
order by g1.id
;




























